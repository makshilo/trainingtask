package com.makshilo.trainingtask_kotlin_spring.repository.impl

import com.makshilo.trainingtask_kotlin_spring.config.properties.database.ProjectDatabaseProperties
import com.makshilo.trainingtask_kotlin_spring.model.Project
import com.makshilo.trainingtask_kotlin_spring.repository.AbstractEntityRepository
import com.makshilo.trainingtask_kotlin_spring.service.database.DataSourceService
import org.springframework.stereotype.Repository

@Repository
class DefaultProjectRepository(
  private val dataSourceService: DataSourceService,
  private val projectDatabaseProperties: ProjectDatabaseProperties
) : AbstractEntityRepository<Project>() {

  override fun create(entity: Project): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getInsertQuery(projectDatabaseProperties.tableName, projectDatabaseProperties.columns).trim()
        )
          .use { statement ->
            statement.setLong(1, 0)
            statement.setString(2, entity.name)
            statement.setString(3, entity.description)
            statement.executeUpdate() > 0
          }
      }
  }

  override fun findAll(): List<Project> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getSelectQuery(projectDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.executeQuery()
              .use { resultSet ->
                val projects = mutableListOf<Project>()
                while (resultSet.next()) {
                  projects.add(
                    Project(
                      id = resultSet.getLong("id"),
                      name = resultSet.getString("project_name"),
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
        connection.prepareStatement(getSelectByIdQuery(projectDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
              .use { resultSet ->
                if (resultSet.next()) {
                  Project(
                    id = resultSet.getLong("id"),
                    name = resultSet.getString("project_name"),
                    description = resultSet.getString("description")
                  )
                } else {
                  null
                }
              }
          }

      }
  }

  override fun update(entity: Project): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getUpdateQuery(projectDatabaseProperties.tableName, projectDatabaseProperties.columns).trim()
        )
          .use { statement ->
            entity.id?.let { statement.setLong(1, it) }
            statement.setString(2, entity.name)
            statement.setString(3, entity.description)
            statement.executeUpdate() > 0
          }
      }
  }

  override fun deleteById(id: Long): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getDeleteQuery(projectDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate() > 0
          }
      }
  }
}