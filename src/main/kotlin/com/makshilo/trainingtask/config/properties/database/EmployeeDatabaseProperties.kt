package com.makshilo.trainingtask.config.properties.database

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "database.entity.employee")
class EmployeeDatabaseProperties(
  var tableName: String = "",
  var columns: List<String> = listOf()
)
