# Spirytus
-----------------------------
Spirytus is merely a helper package for those who hates the verbosity of Java and wants the simplicity Spring Framework brought for web servers.

Example usage can be found under package `cool.sirius.spirytus.example`.

Sadly, for repositories, I haven't yet found a simpler way to implement it. If one wishes to utilize Spring implemented repository methods 
without having to explicitly convert parameter type from Scala to Java, you need to either wrap it in an Object or use implicit convert methods from 
`JavaCollectionMapper`.

Example domain:

```scala 3
@Entity
class ExampleDomain {

  @Id
  var id: String = ""

  //Let's assume name is unique.
  @Column
  var name: String = ""

  @Column
  var city: String = ""

  @Column
  var age: Int = -1

  @Column
  var alive: Boolean = false
}
```

Example repository:

```scala 3
@Repository
trait ExampleRepo extends JpaRepository[ExampleDomain, String]{

  //You might still mark the return type as Java list as jpa does not recognise Scala list.
  def findByAlive(alive: Boolean): java.util.List[ExampleDomain]

  def findByCity(city: String): java.util.List[ExampleDomain]

  def findByAgeBetween(ageStart: Int, ageStop: Int): java.util.List[ExampleDomain]

  def findByName(name: String): java.util.Optional[ExampleDomain]

}
```

Example repository singleton:

```scala 3
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
```

Example service:

```scala 3
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
```

Example service with autowired repository:

```scala 3
@Service
@ObjectAnnotation(ExampleService2)
object ExampleService2 extends ObjectAutoConfig with JavaCollectionMapper{

  @Autowired
  val exampleRepo: ExampleRepo = null

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
```

