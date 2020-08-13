package cool.sirius.liquor.spirytus.util

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import javax.sql.DataSource
import org.apache.commons.text.CaseUtils
import org.springframework.jdbc.core.JdbcTemplate


trait JDBCHelper extends JavaCollectionMapper {

  val jdbcTemplate: JdbcTemplate = new JdbcTemplate(dataSource)

  val objectMapper: ObjectMapper = (new ObjectMapper() with ScalaObjectMapper).registerModule(DefaultScalaModule)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

  def dataSource: DataSource

  implicit class QueryHelper(sql: String) {
    def queryForList[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql).toScala.map(element => {
        objectMapper.convertValue(element.toScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false), v)}, clazz)
      })
    }

    def queryForObject[T](clazz: Class[T]): T = {
      objectMapper.convertValue(jdbcTemplate.queryForMap(sql).toScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false), v)}, clazz)
    }

    def execute(): Unit = {
      jdbcTemplate.execute(sql)
    }
  }

}
