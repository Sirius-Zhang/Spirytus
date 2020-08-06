package cool.sirius.liquor.spirytus.util

import java.util.UUID

trait EntityHelper {

  def uuid: String = UUID.randomUUID().toString.replaceAll("-", "")

  implicit class HandyOp[T](in: T) {
    def persist[R <: RepoHelper[T, _, _]](implicit service: R): T = {
      service.save(in)
    }
  }

  implicit class HandyOp2[T](in: T) {
    def delete[R <: RepoHelper[T, _, _]](implicit service: R): Unit = {
      service.delete(in)
    }
  }

}
