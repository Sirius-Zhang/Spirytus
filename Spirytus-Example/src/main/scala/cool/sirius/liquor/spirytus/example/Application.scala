package cool.sirius.liquor.spirytus.example

import cool.sirius.liquor.spirytus.example.service.{ExampleJDBCService, ExampleJDBCService2, ExampleService, ExampleService2}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

object Application extends App {
  SpringApplication.run(classOf[Application])

  println("All example domains ---")
  println(ExampleJDBCService.allExampleNames().sorted.mkString(","))
  println("separator ---")
  println(ExampleJDBCService2.allExampleNames().sorted.mkString(","))

  println("\nExample domains under 20 ---")
  ExampleJDBCService.exampleDomainUnder20().foreach(println)
  println("separator ---")
  ExampleJDBCService2.exampleDomainUnder20().foreach(println)

  println("\nChart by city ---")
  println(ExampleService.chartByCity())
  println("separator ---")
  println(ExampleService2.chartByCity())

}
