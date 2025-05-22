package cool.sirius.liquor.spirytus.util

import java.util.UUID

trait EntityHelper {

  def uuid: String = UUID.randomUUID().toString.replaceAll("-", "")

  extension [T](in: T)
    def persist[R <: RepoHelper[T, _, _]](using service: R): T =
      service.saveAndFlush(in)

    def delete[R <: RepoHelper[T, _, _]](using service: R): Unit =
      service.delete(in)

}
