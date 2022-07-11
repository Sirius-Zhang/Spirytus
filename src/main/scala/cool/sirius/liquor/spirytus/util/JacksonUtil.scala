package cool.sirius.liquor.spirytus.util

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object JacksonUtil extends ObjectAutoConfig {

  val objectMapper: ObjectMapper = JsonMapper.builder()
    .addModule(DefaultScalaModule)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .build()

  def to[T](in: T): String = {
    objectMapper.writeValueAsString(in)
  }

  def from[T](in: String, clazz: Class[T]): T = {
    objectMapper.readValue(in, clazz)
  }

  def from[T](in: String, typeReference: TypeReference[T]): T = {
    objectMapper.readValue(in, typeReference)
  }

  def from[T](in: String, clazz: Class[T], onErrorFallback: T): T = {
    try {
      objectMapper.readValue(in, clazz)
    } catch {
      case e: Exception => {
        logger.error(s"Failed to write JSON to object. reason = ${e.getMessage}")
        onErrorFallback
      }
    }
  }

  def from[T](in: String, typeReference: TypeReference[T], onErrorFallback: T): T = {
    try {
      objectMapper.readValue(in, typeReference)
    } catch {
      case e: Exception => {
        logger.error(s"Failed to write JSON to object. reason = ${e.getMessage}")
        onErrorFallback
      }
    }
  }

}
