package cool.sirius.liquor.spirytus.example.repo

import cool.sirius.liquor.spirytus.example.domain.ExampleDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
trait ExampleRepo extends JpaRepository[ExampleDomain, String]{

  //You might still mark the return type as Java list as jpa does not recognise Scala list.
  def findByAlive(alive: Boolean): java.util.List[ExampleDomain]

  def findByCity(city: String): java.util.List[ExampleDomain]

  def findByAgeBetween(ageStart: Int, ageStop: Int): java.util.List[ExampleDomain]

  def findByName(name: String): java.util.Optional[ExampleDomain]

}
