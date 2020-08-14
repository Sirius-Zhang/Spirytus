package cool.sirius.liquor.spirytus.util

import java.lang.reflect.{ParameterizedType, Type}
import java.sql.{PreparedStatement, ResultSet, Types}
import java.util.Properties

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.{DynamicParameterizedType, UserType}

object JsonType extends UserType with DynamicParameterizedType with Serializable{
  val TYPE: String = "cool.sirius.liquor.spirytus.util.JsonType"
  val CLASS_NAME: String = "class"
  var sqlType: Int = Types.VARCHAR
  var t: Type = classOf[Object]

  override def sqlTypes(): Array[Int] = {
    Array(sqlType)
  }

  override def returnedClass(): Class[_] = {
    t match {
      case ParameterizedType =>
        t.asInstanceOf[ParameterizedType].getRawType.asInstanceOf[Class]

    }
  }

  override def equals(o: Any, o1: Any): Boolean = ???

  override def hashCode(o: Any): Int = ???

  override def nullSafeGet(resultSet: ResultSet, strings: Array[String], sharedSessionContractImplementor: SharedSessionContractImplementor, o: Any): AnyRef = ???

  override def nullSafeSet(preparedStatement: PreparedStatement, o: Any, i: Int, sharedSessionContractImplementor: SharedSessionContractImplementor): Unit = ???

  override def deepCopy(o: Any): AnyRef = ???

  override def isMutable: Boolean = ???

  override def disassemble(o: Any): Serializable = ???

  override def assemble(serializable: Serializable, o: Any): AnyRef = ???

  override def replace(o: Any, o1: Any, o2: Any): AnyRef = ???

  override def setParameterValues(properties: Properties): Unit = ???
}
