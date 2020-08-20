package cool.sirius.liquor.spirytus.example.config

import javax.sql.DataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ExampleDataSourceConf {

  @Bean(name = Array("Example_DS"))
  @ConfigurationProperties(value = "example.jdbc")
  def dataSource: DataSource = {
    DataSourceBuilder.create().build().asInstanceOf[DataSource]
  }

}
