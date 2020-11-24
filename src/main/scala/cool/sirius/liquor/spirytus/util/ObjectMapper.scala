package cool.sirius.liquor.spirytus.util

import scala.reflect.ClassTag

trait ObjectMapper {

  implicit class CC2Map(in: Product) {
    def toMap: Map[String, Any] = {
      (in.productElementNames zip in.productIterator).toMap
    }
  }

}
