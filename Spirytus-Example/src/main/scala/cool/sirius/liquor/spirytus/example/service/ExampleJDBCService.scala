package cool.sirius.liquor.spirytus.example.service

import cool.sirius.liquor.spirytus.example.domain.ExampleDomain
import cool.sirius.liquor.spirytus.util.{JDBCHelper, ObjectAnnotation, ObjectAutoConfig}
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Service

@Service
@ObjectAnnotation(ExampleJDBCService)
object ExampleJDBCService extends JDBCHelper with ObjectAutoConfig {

  var ds: DataSource = _

  @Autowired
  def setDataSource(@Qualifier(value = "Example_DS") dataSource: DataSource): Unit = {
    ds = dataSource
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

  override def dataSource: DataSource = ds
}

class ExampleJDBCService
