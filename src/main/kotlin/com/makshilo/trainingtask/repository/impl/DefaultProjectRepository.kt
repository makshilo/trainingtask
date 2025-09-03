package com.makshilo.trainingtask.repository.impl

import com.makshilo.trainingtask.model.Project
import com.makshilo.trainingtask.repository.ProjectRepository
import com.makshilo.trainingtask.service.database.DataSourceService
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement

@Repository
class DefaultProjectRepository(
  private val dataSourceService: DataSourceService,
) : ProjectRepository {

  override fun add(project: Project): Project {
    dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "INSERT INTO trainingtask.project (name, description) VALUES (?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS
        )
          .use { statement ->
            statement.setString(1, project.name)
            statement.setString(2, project.description)
            statement.executeUpdate()
            val generatedKeys = statement.generatedKeys
            return if (generatedKeys.next()) {
              project.copy(id = generatedKeys.getLong(1))
            } else {
              project
            }
          }
      }
  }

  override fun findAll(): List<Project> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement("SELECT * FROM trainingtask.project")
          .use { statement ->
            statement.executeQuery()
              .use { resultSet ->
                val projects = mutableListOf<Project>()
                while (resultSet.next()) {
                  projects.add(
                    Project(
                      id = resultSet.getLong("id"),
                      name = resultSet.getString("name"),
                      description = resultSet.getString("description")
                    )
                  )
                }
                projects
              }
          }
      }
  }

  override fun findById(id: Long): Project? {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement("SELECT * FROM trainingtask.project WHERE id = ?")
          .use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
              .use { resultSet ->
                if (resultSet.next()) {
                  Project(
                    id = resultSet.getLong("id"),
                    name = resultSet.getString("name"),
                    description = resultSet.getString("description")
                  )
                } else {
                  null
                }
              }
          }

      }
  }

  override fun update(project: Project): Project {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "UPDATE trainingtask.project SET name = ?, description = ? WHERE id = ?"
        )
          .use { statement ->
            statement.setString(1, project.name)
            statement.setString(2, project.description)
            project.id?.let { id -> statement.setLong(3, id) }
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Project with id ${project.id} does not exist and cannot be updated" }
    return project
  }

  override fun remove(id: Long) {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "DELETE FROM trainingtask.project WHERE id = ?"
        )
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Project with id $id does not exist and cannot be deleted" }
  }
}
