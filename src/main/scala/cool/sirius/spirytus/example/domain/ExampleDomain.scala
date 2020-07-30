package cool.sirius.spirytus.example.domain

import javax.persistence.{Column, Entity, Id}

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
