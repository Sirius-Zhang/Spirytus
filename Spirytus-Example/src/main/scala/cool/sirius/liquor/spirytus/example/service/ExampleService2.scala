package cool.sirius.liquor.spirytus.example.service

import cool.sirius.liquor.spirytus.example.repo.ExampleRepo
import cool.sirius.liquor.spirytus.util.{JavaCollectionMapper, ObjectAnnotation, ObjectAutoConfig}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@ObjectAnnotation(ExampleService2)
object ExampleService2 extends ObjectAutoConfig with JavaCollectionMapper{

  var exampleRepo: ExampleRepo = _

  @Autowired
  def setRepo(r: ExampleRepo): Unit ={
    exampleRepo = r
  }

  def chartByCity(): Map[String, Int] = {
    exampleRepo.findAll().toScala.groupBy(_.city).map(z => (z._1, z._2.length))
  }

  def existsByName(name: String): Boolean = {
    exampleRepo.findByName(name).toScala.fold(false)(_ => true)
  }

  def fetchCitiesByIds(ids: List[String]): List[String] = {
    exampleRepo.findAllById(ids.toJava).toScala.map(_.city).distinct
  }
}

class ExampleService2
