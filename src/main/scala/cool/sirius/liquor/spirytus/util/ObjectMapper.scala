package cool.sirius.liquor.spirytus.util

import scala.reflect.ClassTag

trait ObjectMapper {

  implicit class CC2Map(in: Product) {
    def toMap: Map[String, Any] = {
      (in.productElementNames zip in.productIterator).map{
        case (k: String, null) =>
          (k, "Null")

        case (k: String, v: Option[Product]) =>
          v.fold[(String, Any)]((k,None))(z => (k, z.toMap))

        case (k: String, v: Product) =>
          (k, v.toMap)

        case (k: String, v: Any) =>
          (k, v)
      }.toMap
    }
  }

}
