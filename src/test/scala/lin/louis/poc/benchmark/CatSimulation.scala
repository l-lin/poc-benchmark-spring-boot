package lin.louis.poc.benchmark

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class CatSimulation extends Simulation {
  var path = "/cats"
  val httpConf = http
    .baseUrl(BenchmarkUtils.baseUrl)
    .acceptHeader(BenchmarkUtils.mediaTypeJson)
    .contentTypeHeader(BenchmarkUtils.mediaTypeJson)
    .userAgentHeader(BenchmarkUtils.userAgent)

  // Scenarios
  val getCat =
    repeat(BenchmarkUtils.iterations) {
      feed(BenchmarkUtils.randomIds)
        .exec(
          http("Get cat")
            .get(path + "/${randomId}")
            .check(status.is(200))
        ).pause(1 seconds)
    }
  val saveCat =
    repeat(BenchmarkUtils.iterations) {
      feed(BenchmarkUtils.catsFeeder)
      .exec(
        http("Save a random cat")
          .post(path)
          .body(StringBody("""{"name": "${name}", "type": "${type}"}"""))
          .check(status.is(201))
      ).pause(1 seconds)
    }
  val getCats =
    exec(
      http("Get cats")
        .get(path + "?page=1&size=2")
        .check(status.is(200))
        .check(jsonPath("$.page.number").saveAs("pageNumber"))
        .check(jsonPath("$.page.size").saveAs("pageSize"))
        .check(jsonPath("$.page.totalPages").saveAs("totalPages"))
    ).pause(1 seconds)
    .exec(
      http("Get next cats")
        .get(path + "?page=${totalPages}&size=${pageSize}")
        .check(status.is(200))
        .check(jsonPath("$.page.number").saveAs("pageNumber"))
        .check(jsonPath("$.page.size").saveAs("pageSize"))
        .check(jsonPath("$.page.totalPages").saveAs("totalPages"))
    ).pause(1 seconds)
  val finalScenario = scenario("Final scenario")
    .exec(getCat, saveCat, getCats)

  setUp(
    finalScenario.inject(
      atOnceUsers(1)
    )
  ).protocols(httpConf)
}
