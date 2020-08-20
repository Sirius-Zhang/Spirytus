package cool.sirius.liquor.spirytus.example.service

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import cool.sirius.liquor.spirytus.example.domain.ExampleDomain
import cool.sirius.liquor.spirytus.util.{JDBCHelper, ObjectAnnotation, ObjectAutoConfig}
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

@Service
@ObjectAnnotation(ExampleJDBCService2)
object ExampleJDBCService2 extends JDBCHelper with ObjectAutoConfig {

  var url: String = _
  var username: String = _
  var password: String = _
  var driverClass: String = _

  def dataSource: DataSource = exampleDataSource

  @Autowired
  def setConnectionInfo(@Value(value = "${example.jdbc.jdbc-url}") u: String,
                        @Value(value = "${example.jdbc.username}") uname: String,
                        @Value(value = "${example.jdbc.password}") pwd: String,
                       @Value(value = "${example.jdbc.driver-class-name}") dc: String): Unit = {
    url = u
    username = uname
    password = pwd
    driverClass = dc
  }

  def exampleDataSource: DataSource = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(url)
    hikariConfig.setUsername(username)
    hikariConfig.setPassword(password)
    hikariConfig.setDriverClassName(driverClass)
    val hikariDataSource = new HikariDataSource(hikariConfig)
    hikariDataSource.setConnectionTimeout(1000L * 60 * 10)
    hikariDataSource
  }

  def countExampleDomains(): Long = {
    "select count(1) from t_domain".queryForObject(classOf[Long])
  }

  def allExampleNames(): List[String] = {
    "select distinct name from t_domain".queryForListWithType(classOf[String])
  }

  def exampleDomainUnder20(): List[ExampleDomain] = {
    "select * from t_domain where age < 20".queryForList(classOf[ExampleDomain])
  }

}

class ExampleJDBCService2
