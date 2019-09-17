import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

/**
  * Main class to execute Gatling to perform the test scenarios
  */
object Engine extends App {
  val props = new GatlingPropertiesBuilder()
    .resourcesDirectory(IDEPathHelper.resourcesDirectory.toString)
    .resultsDirectory(IDEPathHelper.resultsDirectory.toString)
    .binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString)

  Gatling.fromMap(props.build)
}
