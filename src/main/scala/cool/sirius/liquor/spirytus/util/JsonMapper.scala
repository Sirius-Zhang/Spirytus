package cool.sirius.liquor.spirytus.util

import net.liftweb.json.Serialization.{read, write}
import net.liftweb.json.{DefaultFormats, Formats}

object JsonMapper {

  implicit val formats: Formats = DefaultFormats

  def from[T](jsonString: String)(implicit mf: Manifest[T]): T = read[T](jsonString)

  def to(in: Any): String = write(in)

}
