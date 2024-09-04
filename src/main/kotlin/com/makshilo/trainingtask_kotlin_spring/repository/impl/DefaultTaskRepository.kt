package com.makshilo.trainingtask_kotlin_spring.repository.impl

import com.makshilo.trainingtask_kotlin_spring.config.properties.database.TaskDatabaseProperties
import com.makshilo.trainingtask_kotlin_spring.model.Employee
import com.makshilo.trainingtask_kotlin_spring.model.Project
import com.makshilo.trainingtask_kotlin_spring.model.Task
import com.makshilo.trainingtask_kotlin_spring.model.TaskStatus
import com.makshilo.trainingtask_kotlin_spring.repository.AbstractEntityRepository
import com.makshilo.trainingtask_kotlin_spring.service.database.DataSourceService
import org.springframework.stereotype.Repository
import java.sql.Date

@Suppress("DuplicatedCode")
@Repository
class DefaultTaskRepository(
  private val dataSourceService: DataSourceService,
  private val taskDatabaseProperties: TaskDatabaseProperties,
  private val projectRepository: AbstractEntityRepository<Project>,
  private val employeeRepository: AbstractEntityRepository<Employee>
): AbstractEntityRepository<Task>() {

  override fun create(entity: Task): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getInsertQuery(taskDatabaseProperties.tableName, taskDatabaseProperties.columns).trim()
        )
          .use { statement ->
            statement.setLong(1, 0)
            statement.setString(2, entity.name)
            entity.project.id?.let { statement.setLong(3, it) }
            statement.setShort(4, entity.estimate)
            statement.setDate(5, Date.valueOf(entity.startDate))
            statement.setDate(6, Date.valueOf(entity.endDate))
            statement.setString(7, entity.status.name)
            entity.employee.id?.let { statement.setLong(8, it) }
            statement.executeUpdate() > 0
          }
      }
  }

  override fun findAll(): List<Task> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getSelectQuery(taskDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.executeQuery()
              .use { resultSet ->
                val tasks = mutableListOf<Task>()
                while (resultSet.next()) {
                  projectRepository.findById(resultSet.getLong("project_id"))?.let { project ->
                    employeeRepository.findById(resultSet.getLong("employee_id"))?.let { employee ->
                      Task(
                        id = resultSet.getLong("id"),
                        name = resultSet.getString("name"),
                        project = project,
                        estimate = resultSet.getShort("estimate"),
                        startDate = resultSet.getDate("start_date").toLocalDate(),
                        endDate = resultSet.getDate("end_date").toLocalDate(),
                        status = TaskStatus.valueOf(resultSet.getString("status")),
                        employee = employee
                      )
                    }
                  }?.let { tasks.add(it) }
                }
                tasks
              }
          }
      }
  }

  override fun findById(id: Long): Task? {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getSelectByIdQuery(taskDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
              .use { resultSet ->
                if (resultSet.next()) {
                  projectRepository.findById(resultSet.getLong("project_id"))?.let { project ->
                    employeeRepository.findById(resultSet.getLong("employee_id")
                    )?.let { employee ->
                      Task(
                        id = resultSet.getLong("id"),
                        name = resultSet.getString("name"),
                        project = project,
                        estimate = resultSet.getShort("estimate"),
                        startDate = resultSet.getDate("start_date").toLocalDate(),
                        endDate = resultSet.getDate("end_date").toLocalDate(),
                        status = TaskStatus.valueOf(resultSet.getString("status")),
                        employee = employee
                      )
                    }
                  }
                } else {
                  null
                }
              }
          }

      }
  }

  override fun update(entity: Task): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getUpdateQuery(taskDatabaseProperties.tableName, taskDatabaseProperties.columns).trim()
        )
          .use { statement ->
            entity.id?.let { statement.setLong(1, it) }
            statement.setString(2, entity.name)
            entity.project.id?.let { statement.setLong(3, it) }
            statement.setShort(4, entity.estimate)
            statement.setDate(5, Date.valueOf(entity.startDate))
            statement.setDate(6, Date.valueOf(entity.endDate))
            statement.setString(7, entity.status.name)
            entity.employee.id?.let { statement.setLong(8, it) }
            statement.executeUpdate() > 0
          }
      }
  }

  override fun deleteById(id: Long): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getDeleteQuery(taskDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate() > 0
          }
      }
  }
}