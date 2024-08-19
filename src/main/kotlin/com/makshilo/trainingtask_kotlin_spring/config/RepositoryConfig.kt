package com.makshilo.trainingtask_kotlin_spring.config

import com.makshilo.trainingtask_kotlin_spring.config.properties.database.EmployeeDatabaseProperties
import com.makshilo.trainingtask_kotlin_spring.config.properties.database.ProjectDatabaseProperties
import com.makshilo.trainingtask_kotlin_spring.config.properties.database.TaskDatabaseProperties
import com.makshilo.trainingtask_kotlin_spring.model.Employee
import com.makshilo.trainingtask_kotlin_spring.model.Project
import com.makshilo.trainingtask_kotlin_spring.model.Task
import com.makshilo.trainingtask_kotlin_spring.repository.AbstractEntityRepository
import com.makshilo.trainingtask_kotlin_spring.repository.impl.DefaultEmployeeRepository
import com.makshilo.trainingtask_kotlin_spring.repository.impl.DefaultProjectRepository
import com.makshilo.trainingtask_kotlin_spring.repository.impl.DefaultTaskRepository
import com.makshilo.trainingtask_kotlin_spring.service.database.DataSourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class RepositoryConfig(
  @Autowired val dataSource: DataSource,
  @Autowired val projectDatabaseProperties: ProjectDatabaseProperties,
  @Autowired val employeeDatabaseProperties: EmployeeDatabaseProperties,
  @Autowired val taskDatabaseProperties: TaskDatabaseProperties
) {

  @Bean
  fun dataSourceService() = DataSourceService(dataSource)

  @Bean
  fun projectRepository(): AbstractEntityRepository<Project> =
    DefaultProjectRepository(dataSourceService(), projectDatabaseProperties)

  @Bean
  fun employeeRepository(): AbstractEntityRepository<Employee> =
    DefaultEmployeeRepository(dataSourceService(), employeeDatabaseProperties)

  @Bean
  fun taskRepository(): AbstractEntityRepository<Task> =
    DefaultTaskRepository(dataSourceService(), taskDatabaseProperties, projectRepository(), employeeRepository())
}