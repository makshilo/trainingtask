package com.makshilo.trainingtask.service.database

import org.springframework.context.annotation.Configuration
import java.sql.Connection
import javax.sql.DataSource

@Configuration
class DataSourceService(
  private val dataSource: DataSource
) {

  fun getConnection(): Connection = dataSource.connection
}
