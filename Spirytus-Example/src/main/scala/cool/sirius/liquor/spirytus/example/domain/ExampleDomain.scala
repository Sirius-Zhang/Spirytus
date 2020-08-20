package cool.sirius.liquor.spirytus.example.domain

import javax.persistence.{Column, Entity, Id, Table}

@Entity
@Table(name = "t_domain")
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

  override def toString: String = s"id: $id, name: $name, city: $city, age: $age, alive: $alive"
}
