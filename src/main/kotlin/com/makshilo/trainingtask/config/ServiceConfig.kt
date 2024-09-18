package com.makshilo.trainingtask.config

import com.makshilo.trainingtask.service.EmployeeRepositoryService
import com.makshilo.trainingtask.service.ProjectRepositoryService
import com.makshilo.trainingtask.service.TaskRepositoryService
import com.makshilo.trainingtask.service.impl.DefaultEmployeeRepositoryService
import com.makshilo.trainingtask.service.impl.DefaultProjectRepositoryService
import com.makshilo.trainingtask.service.impl.DefaultTaskRepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig(
  @Autowired private val repositoryConfig: RepositoryConfig
) {

  @Bean
  fun employeeService(): EmployeeRepositoryService =
    DefaultEmployeeRepositoryService(repositoryConfig.employeeRepository())

  @Bean
  fun projectService(): ProjectRepositoryService =
    DefaultProjectRepositoryService(repositoryConfig.projectRepository())

  @Bean
  fun taskService(): TaskRepositoryService =
    DefaultTaskRepositoryService(repositoryConfig.taskRepository())
}
