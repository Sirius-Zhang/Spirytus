package cool.sirius.liquor.spirytus.util

import java.sql.ResultSet

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper => JacksonObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import javax.sql.DataSource
import org.apache.commons.text.CaseUtils
import org.springframework.jdbc.core.{JdbcTemplate, ResultSetExtractor, RowCallbackHandler}

import scala.jdk.CollectionConverters.*


trait JDBCHelper extends JavaCollectionMapper {

  lazy val jdbcTemplate: JdbcTemplate = new JdbcTemplate(dataSource)

  val objectMapper: JacksonObjectMapper = new JacksonObjectMapper().registerModule(DefaultScalaModule)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

  def dataSource: DataSource

  extension (sql: String) {
    def queryForListPojoCamel[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql).asScala.map(element => {
        objectMapper.convertValue(element.asScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false, '_'), v)}, clazz)
      }).toList
    }

    def queryForListPojo[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql).asScala.map(element => {
        objectMapper.convertValue(element, clazz)
      }).toList
    }

    def queryForListPrimitiveType[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql, clazz).asScala.toList
    }

    def queryForPojoCamel[T](clazz: Class[T]): T = {
      objectMapper.convertValue(jdbcTemplate.queryForMap(sql).asScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false, '_'), v)}, clazz)
    }

    def queryForPojo[T](clazz: Class[T]): T = {
      objectMapper.convertValue(jdbcTemplate.queryForMap(sql), clazz)
    }

    def queryForPrimitiveObject[T](clazz: Class[T]): T = {
      jdbcTemplate.queryForObject(sql, clazz)
    }

    def queryForMapIgnoreType(): List[Map[String, String]] = {
      jdbcTemplate.queryForList(sql).asScala.map(entry => {
        entry.asScala.map{ case (k, v) => (k, Option(v).fold("")(_.toString))}.toMap
      }).toList
    }

    def queryForRawMap(): List[Map[String, Object]] = {
      jdbcTemplate.queryForList(sql).asScala.map(_.asScala.toMap).toList
    }

    def queryForMap(): List[Map[String, Any]] = {
      jdbcTemplate.queryForList(sql).asScala.map(entry => {
        entry.asScala.map{case (k, v) =>
          v match {
            case i: Int =>
              (k, i)

            case d: Double =>
              (k, d)

            case f: Float =>
              (k, f)

            case l: Long =>
              (k, l)

            case _ =>
              (k, v.toString)
          }
        }.toMap
      }).toList
    }

    def execute(): Unit = {
      jdbcTemplate.execute(sql)
    }
  }

}
