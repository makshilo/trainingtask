package com.makshilo.trainingtask_kotlin_spring.service.database

import org.springframework.context.annotation.Configuration
import java.sql.Connection
import javax.sql.DataSource

@Configuration
class DataSourceService(
  private val dataSource: DataSource
) {

  fun getConnection(): Connection = dataSource.connection
}