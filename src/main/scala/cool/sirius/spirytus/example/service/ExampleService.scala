package cool.sirius.spirytus.example.service

import cool.sirius.spirytus.example.domain.ExampleDomain
import cool.sirius.spirytus.example.repo.ExampleRepoSingleton
import cool.sirius.spirytus.util.{ObjectAnnotation, ObjectAutoConfig}
import org.springframework.stereotype.Service

@Service
@ObjectAnnotation(ExampleService)
object ExampleService extends ObjectAutoConfig{

  def chartByCity(): Map[String, Int] = {
    ExampleRepoSingleton.findAll.groupBy(_.city).map(z => (z._1, z._2.length))
  }

  def existsByName(name: String): Boolean = {
    ExampleRepoSingleton.fetchByName(name).fold(false)(_ => true)
  }

  def fetchCitiesByIds(ids: List[String]): List[String] = {
    ExampleRepoSingleton.findAllById(ids).map(_.city).distinct
  }

}

class ExampleService
