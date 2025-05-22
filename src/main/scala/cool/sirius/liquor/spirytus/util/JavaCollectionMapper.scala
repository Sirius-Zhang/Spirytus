package cool.sirius.liquor.spirytus.util

import java.util
import java.util.Optional
import scala.annotation.targetName

trait JavaCollectionMapper {

  import scala.jdk.CollectionConverters._

  extension [T <: Any](in: java.util.List[T])
    @targetName("listToScala") 
    def toScala: List[T] = in.asScala.toList

  extension [T <: Any](in: Optional[java.util.List[T]])
    @targetName("optionalListToScala")
    def toScala: Option[List[T]] = if (in.isPresent) Some(in.get().asScala.toList) else None

  extension [T <: Any](in: java.lang.Iterable[T])
    @targetName("iterableToScala")
    def toScala: List[T] = in.asScala.toList

  extension [T <: Any](in: Optional[T])
    @targetName("optionalToScala")
    def toScala: Option[T] = if (in.isPresent) Some(in.get()) else None

  extension [K,V <: Any](in: java.util.Map[K, V])
    @targetName("mapToScala")
    def toScala: Map[K, V] = in.asScala.toMap

  extension [T <: Any](in: Optional[java.util.List[T]])
    @targetName("optionalListFoldToScala") // This might not be strictly necessary if the name `foldToScala` is unique enough
    def foldToScala: List[T] = if (in.isPresent) in.get().asScala.toList else List()

  extension [T <: Any](in: List[T])
    def toJava: java.util.List[T] = in.asJava

  extension [K, V <: Any](in: Map[K, V])
    def toJava: java.util.HashMap[K, V] = new util.HashMap[K, V](in.asJava)

  extension [T <: Any](in: java.util.Iterator[T])
    @targetName("iteratorToScala")
    def toScala: Iterator[T] = in.asScala

  extension [T <: Any](in: Set[T])
    def toJava: java.util.Set[T] = in.asJava

}