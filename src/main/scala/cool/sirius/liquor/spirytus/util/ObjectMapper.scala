package cool.sirius.liquor.spirytus.util

trait ObjectMapper {

  implicit class CC2Map(in: Product) {
    def toMap: Map[String, Any] = {
      (in.productElementNames zip in.productIterator).map {
        case (k: String, null) =>
          (k, "Null")

        case (k: String, v: List[_]) =>
          (k, v)

        case (k: String, v: Option[Product]) =>
          v.fold[(String, Any)]((k, None))(z => (k, z.toMap))

        case (k: String, v: Product) =>
          (k, v.toMap)

        case (k: String, v: Any) =>
          (k, v)
      }.toMap
    }

    def fields: List[String] = {
      in.productElementNames.toList
    }
  }

}
