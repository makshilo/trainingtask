package com.makshilo.trainingtask_kotlin_spring.config.properties.database

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "database.entity.task")
class TaskDatabaseProperties(
  var tableName: String = "",
  var columns: List<String> = listOf()
)