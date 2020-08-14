package cool.sirius.liquor.spirytus.util

import net.liftweb.json.Serialization.{read, write}
import net.liftweb.json.{FieldSerializer, Formats, Serialization, ShortTypeHints}

object JsonMapper {

  implicit val formats: Formats = Serialization.formats(ShortTypeHints(List())) + FieldSerializer[String]()

  def from[T](jsonString: String)(implicit mf: Manifest[T]): T = read[T](jsonString)

  def to(in: Any): String = write(in)

}
