package com.makshilo.trainingtask.config

import com.makshilo.trainingtask.repository.EmployeeRepository
import com.makshilo.trainingtask.repository.ProjectRepository
import com.makshilo.trainingtask.repository.TaskRepository
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
) {

  @Bean
  fun dataSourceService() = DataSourceService(dataSource)

  @Bean
  fun projectRepository(): ProjectRepository =
    DefaultProjectRepository(
      dataSourceService()
    )

  @Bean
  fun employeeRepository(): EmployeeRepository =
    DefaultEmployeeRepository(
      dataSourceService()
    )

  @Bean
  fun taskRepository(): TaskRepository =
    DefaultTaskRepository(
      dataSourceService()
    )
}
