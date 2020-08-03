package cool.sirius.liquor.spirytus.util

import java.util.Optional

trait JavaCollectionMapper {

  import scala.jdk.CollectionConverters._
  import scala.compat.java8.OptionConverters._


  implicit class toScala[T <: Any](in: java.util.List[T]) {
    def toScala: List[T] = in.asScala.toList
  }

  implicit class toScala2[T <: Any](in: Optional[java.util.List[T]]) {
    def toScala: Option[List[T]] = in.asScala.fold[Option[List[T]]](None)(z => Some(z.asScala.toList))
  }

  implicit class toScala3[T <: Any](in: java.lang.Iterable[T]) {
    def toScala: List[T] = in.asScala.toList
  }

  implicit class toScala4[T <: Any](in: Optional[T]) {
    def toScala: Option[T] = in.asScala
  }

  implicit class toScala5[K,V <: Any](in: java.util.Map[K, V]) {
    def toScala: Map[K, V] = in.asScala.toMap
  }

  implicit class foldToScala[T <: Any](in: Optional[java.util.List[T]]) {
    def foldToScala: List[T] = in.asScala.fold[List[T]](List())(z => z.asScala.toList)
  }

  implicit class toJava[T <: Any](in: List[T]) {
    def toJava: java.util.List[T] = in.asJava
  }

}