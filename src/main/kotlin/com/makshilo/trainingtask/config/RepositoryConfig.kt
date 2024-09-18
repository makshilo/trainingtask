package com.makshilo.trainingtask.config

import com.makshilo.trainingtask.config.properties.database.EmployeeDatabaseProperties
import com.makshilo.trainingtask.config.properties.database.ProjectDatabaseProperties
import com.makshilo.trainingtask.config.properties.database.TaskDatabaseProperties
import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.model.Project
import com.makshilo.trainingtask.model.Task
import com.makshilo.trainingtask.repository.AbstractEntityRepository
import com.makshilo.trainingtask.repository.impl.DefaultEmployeeRepository
import com.makshilo.trainingtask.repository.impl.DefaultProjectRepository
import com.makshilo.trainingtask.repository.impl.DefaultTaskRepository
import com.makshilo.trainingtask.service.database.DataSourceService
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
