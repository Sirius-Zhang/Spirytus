package cool.sirius.liquor.spirytus.util

import org.slf4j.{Logger, LoggerFactory}

trait ObjectAutoConfig {
  def instance: ObjectAutoConfig = this

  def logger: Logger = LoggerFactory.getLogger(instance.getClass)
}
