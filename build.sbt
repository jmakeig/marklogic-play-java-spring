name := """play-java-spring"""

version := "1.0-SNAPSHOT"

resolvers += "MarkLogic Repository" at "http://developer.marklogic.com/maven2"

libraryDependencies ++= Seq(
  javaCore,
  "com.h2database" % "h2" % "1.3.168",
  "org.springframework" % "spring-context" % "3.2.1.RELEASE",
  "org.springframework" % "spring-orm" % "3.2.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "3.2.1.RELEASE",
  "org.springframework" % "spring-tx" % "3.2.1.RELEASE",
  "org.springframework" % "spring-expression" % "3.2.1.RELEASE",
  "org.springframework" % "spring-aop" % "3.2.1.RELEASE",
  "org.springframework" % "spring-test" % "3.2.1.RELEASE" % "test",
  "org.hibernate" % "hibernate-entitymanager" % "4.1.9.Final",
  "cglib" % "cglib" % "2.2.2",
  "com.marklogic" % "client-api-java" % "2.0-1"
)

play.Project.playJavaSettings
