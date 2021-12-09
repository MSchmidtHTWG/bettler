val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "bettler",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    //libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3",
    libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1",

    jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )
.enablePlugins(JacocoCoverallsPlugin)
parallelExecution in Test := false
