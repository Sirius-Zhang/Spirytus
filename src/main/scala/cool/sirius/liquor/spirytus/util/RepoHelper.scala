package cool.sirius.liquor.spirytus.util

import org.springframework.data.domain.{Page, Pageable, Sort}
import org.springframework.data.jpa.repository.JpaRepository

trait RepoHelper[T, U, V <: JpaRepository[T, U]] extends JavaCollectionMapper {

  var repository: V = _

  def setRepo(r: V): Unit ={
    repository = r
  }

  def repo: V = repository

  def findAll: List[T] = repo.findAll().toScala

  def findAll(sort: Sort): List[T] = repo.findAll(sort).toScala

  def findAllById(ids: List[U]): List[T] = repo.findAllById(ids.toJava).toScala

  def saveAll(entities: List[T]): List[T] = repo.saveAll(entities.toJava).toScala

  def flush(): Unit = repo.flush()

  def saveAndFlush(entity: T): T = repo.saveAndFlush(entity)

  def deleteInBatch(entities: List[T]): Unit = repo.deleteInBatch(entities.toJava)

  def deleteAllInBatch(): Unit = repo.deleteAllInBatch()

  def getOne(id: U): T = repo.getOne(id)

  def findAll(pageable: Pageable): Page[T] = repo.findAll(pageable)

  def save(entity: T): T = repo.save(entity)

  def findById(id: U): Option[T] = repo.findById(id).toScala

  def existsById(id: U): Boolean = repo.existsById(id)

  def count(): Long = repo.count()

  def deleteById(id: U): Unit = repo.deleteById(id)

  def delete(entity: T): Unit = repo.delete(entity)

  def deleteAll(): Unit = repo.deleteAll()

  implicit class HandyOp(in: T) {
    def persist: T = {
      saveAndFlush(in)
    }
  }


}
