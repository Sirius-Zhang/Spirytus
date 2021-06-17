package cool.sirius.liquor.spirytus.util

import java.util
import java.util.Optional

trait JavaCollectionMapper {

  import scala.jdk.CollectionConverters._


  implicit class toScala[T <: Any](in: java.util.List[T]) {
    def toScala: List[T] = in.asScala.toList
  }

  implicit class toScala2[T <: Any](in: Optional[java.util.List[T]]) {
    def toScala: Option[List[T]] = if (in.isPresent) Some(in.get().toScala) else None
  }

  implicit class toScala3[T <: Any](in: java.lang.Iterable[T]) {
    def toScala: List[T] = in.asScala.toList
  }

  implicit class toScala4[T <: Any](in: Optional[T]) {
    def toScala: Option[T] = if (in.isPresent) Some(in.get()) else None
  }

  implicit class toScala5[K,V <: Any](in: java.util.Map[K, V]) {
    def toScala: Map[K, V] = in.asScala.toMap
  }

  implicit class foldToScala[T <: Any](in: Optional[java.util.List[T]]) {
    def foldToScala: List[T] = if (in.isPresent) in.get().toScala else List()
  }

  implicit class toJava[T <: Any](in: List[T]) {
    def toJava: java.util.List[T] = in.asJava
  }

  implicit class toJava2[K, V <: Any](in: Map[K, V]) {
    def toJava: java.util.HashMap[K, V] = new util.HashMap[K, V](in.asJava)
  }

  implicit class toScala6[T <: Any](in: java.util.Iterator[T]) {
    def toScala: Iterator[T] = in.asScala
  }

}