package cool.sirius.liquor.spirytus.util

import java.sql.ResultSet

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.{DefaultScalaModule, ScalaObjectMapper}
import javax.sql.DataSource
import org.apache.commons.text.CaseUtils
import org.springframework.jdbc.core.{JdbcTemplate, ResultSetExtractor, RowCallbackHandler}


trait JDBCHelper extends JavaCollectionMapper {

  lazy val jdbcTemplate: JdbcTemplate = new JdbcTemplate(dataSource)

  val objectMapper: ObjectMapper = (new ObjectMapper() with ScalaObjectMapper).registerModule(DefaultScalaModule)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

  def dataSource: DataSource

  implicit class QueryHelper(sql: String) {
    def queryForList[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql).toScala.map(element => {
        objectMapper.convertValue(element.toScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false, '_'), v)}, clazz)
      })
    }

    def queryForListWithType[T](clazz: Class[T]): List[T] = {
      jdbcTemplate.queryForList(sql, clazz).toScala
    }

    def queryForObject[T](clazz: Class[T]): T = {
      objectMapper.convertValue(jdbcTemplate.queryForMap(sql).toScala.map{case (k, v) => (CaseUtils.toCamelCase(k, false, '_'), v)}, clazz)
    }

    def queryForPrimitiveObject[T](clazz: Class[T]): T = {
      jdbcTemplate.queryForObject(sql, clazz)
    }

    def queryForMapIgnoreType(): List[Map[String, String]] = {
      jdbcTemplate.queryForList(sql).toScala.map(entry => {
        entry.toScala.map{ case (k, v) => (k, Option(v).fold("")(_.toString))}
      })
    }

    def queryForRawMap(): List[Map[String, Object]] = {
      jdbcTemplate.queryForList(sql).toScala.map(_.toScala)
    }

    def queryForMap(): List[Map[String, Any]] = {
      jdbcTemplate.queryForList(sql).toScala.map(entry => {
        entry.toScala.map{case (k, v) =>
          v match {
            case i@Int =>
              (k, i)

            case d@Double =>
              (k, d)

            case f@Float =>
              (k, f)

            case l@Long =>
              (k, l)

            case _ =>
              (k, v.toString)
          }
        }
      })
    }

    def execute(): Unit = {
      jdbcTemplate.execute(sql)
    }
  }

}
