package cool.sirius.spirytus.example.repo

import cool.sirius.spirytus.example.domain.ExampleDomain
import cool.sirius.spirytus.util.{ObjectAnnotation, ObjectAutoConfig, RepoHelper}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
@ObjectAnnotation(ExampleRepoSingleton)
object ExampleRepoSingleton extends RepoHelper[ExampleDomain, String, ExampleRepo] with ObjectAutoConfig{

  //With ObjectAnnotation, autowired can help you with Spring Framework managed beans.
  @Autowired
  val repository: ExampleRepo = null

  override def repo: ExampleRepo = repository

  //Still needs to write some boilerplate codes though, it doesn't come in that handy for now.
  //All default methods come with JPARepository had been implemented in RepoHelper.
  //So, say we don't need customised methods, the above two lines are all you need.
  def listByCity(city: String): List[ExampleDomain] = repo.findByCity(city).toScala

  def listAlive(alive: Boolean): List[ExampleDomain] = repo.findByAlive(alive).toScala

  def listInAgeRange(min: Int, max: Int): List[ExampleDomain] = repo.findByAgeBetween(min, max).toScala

  def fetchByName(name: String): Option[ExampleDomain] = repo.findByName(name).toScala

  def listAllNames(): List[String] = findAll.map(_.name)

  def listAllCities(): List[String] = findAll.map(_.city).distinct
}

class ExampleRepoSingleton
