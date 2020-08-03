package cool.sirius.liquor.spirytus.util

import org.slf4j.{Logger, LoggerFactory}

trait ObjectAutoConfig extends JavaCollectionMapper {
  def instance: ObjectAutoConfig = this

  def logger: Logger = LoggerFactory.getLogger(instance.getClass)
}
