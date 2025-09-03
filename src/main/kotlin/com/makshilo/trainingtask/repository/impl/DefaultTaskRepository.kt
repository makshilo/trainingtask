package com.makshilo.trainingtask.repository.impl

import com.makshilo.trainingtask.model.Task
import com.makshilo.trainingtask.model.TaskStatus
import com.makshilo.trainingtask.repository.TaskRepository
import com.makshilo.trainingtask.service.database.DataSourceService
import org.springframework.stereotype.Repository
import java.sql.Date
import java.sql.PreparedStatement

@Repository
class DefaultTaskRepository(
  private val dataSourceService: DataSourceService,
): TaskRepository {

  override fun add(task: Task): Task {
    dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "INSERT " +
            "INTO trainingtask.task (name, project_id, estimate, start_date, end_date, status, employee_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS
        )
          .use { statement ->
            statement.setString(1, task.name)
            statement.setLong(2, task.projectId)
            statement.setShort(3, task.estimate)
            statement.setDate(4, Date.valueOf(task.startDate))
            statement.setDate(5, Date.valueOf(task.endDate))
            statement.setString(6, task.status.name)
            statement.setLong(7, task.employeeId)
            statement.executeUpdate()
            val generatedKeys = statement.generatedKeys
            return if (generatedKeys.next()) {
              task.copy(id = generatedKeys.getLong(1))
            } else {
              task
            }
          }
      }
  }

  override fun findAll(): List<Task> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "SELECT * FROM trainingtask.task"
        )
          .use { statement ->
            statement.executeQuery()
              .use { resultSet ->
                val tasks = mutableListOf<Task>()
                while (resultSet.next()) {
                  tasks.add(
                    Task(
                      id = resultSet.getLong("id"),
                      name = resultSet.getString("name"),
                      projectId = resultSet.getLong("project_id"),
                      estimate = resultSet.getShort("estimate"),
                      startDate = resultSet.getDate("start_date").toLocalDate(),
                      endDate = resultSet.getDate("end_date").toLocalDate(),
                      status = TaskStatus.valueOf(resultSet.getString("status")),
                      employeeId = resultSet.getLong("employee_id")
                    )
                  )
                }
                tasks
              }
          }
      }
  }

  override fun findById(id: Long): Task? {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "SELECT * FROM trainingtask.task WHERE id = ?"
        )
          .use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
              .use { resultSet ->
                if (resultSet.next()) {
                  Task(
                    id = resultSet.getLong("id"),
                    name = resultSet.getString("name"),
                    projectId = resultSet.getLong("project_id"),
                    estimate = resultSet.getShort("estimate"),
                    startDate = resultSet.getDate("start_date").toLocalDate(),
                    endDate = resultSet.getDate("end_date").toLocalDate(),
                    status = TaskStatus.valueOf(resultSet.getString("status")),
                    employeeId = resultSet.getLong("employee_id")
                  )
                } else {
                  null
                }
              }
          }
      }
  }

  override fun update(task: Task): Task {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "UPDATE trainingtask.task " +
            "SET name = ?, project_id = ?, estimate = ?, start_date = ?, end_date = ?, status = ?, employee_id = ? " +
            "WHERE id = ?"
        )
          .use { statement ->
            statement.setString(1, task.name)
            statement.setLong(2, task.projectId)
            statement.setShort(3, task.estimate)
            statement.setDate(4, Date.valueOf(task.startDate))
            statement.setDate(5, Date.valueOf(task.endDate))
            statement.setString(6, task.status.name)
            statement.setLong(7, task.employeeId)
            task.id?.let { id -> statement.setLong(8, id) }
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Task with id ${task.id} does not exist and cannot be updated" }
    return task
  }

  override fun remove(id: Long) {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "DELETE FROM trainingtask.task WHERE id = ?"
        )
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Task with id $id does not exist and cannot be deleted" }
  }
}
