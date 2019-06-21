package lin.louis.poc.benchmark

import io.gatling.core.Predef._

import scala.util.Random

object BenchmarkUtils {
    val baseUrl = "http://localhost:8080"
    val mediaTypeJson = "application/json"
    val userAgent = "curl/7.58.0"

    val max = 3
    val min = 1
    // Define an infinite feeder which calculates random numbers
    val randomInt = () => (Random.nextInt((max - min) + 1) + min)
    val randomIds = Iterator.continually(
        Map("randomId" -> randomInt())
    )

    val catsFeeder = csv("data/random_cats.csv").random

    val iterations = 2
}
