package cool.sirius.spirytus.util

import org.springframework.context.annotation.{Bean, Configuration}

case class ObjectAnnotation(obj: ObjectAutoConfig) extends annotation.StaticAnnotation {
  @Configuration
  class InnerConfiguration {
    @Bean
    def bean: ObjectAutoConfig = obj.instance
  }
}
