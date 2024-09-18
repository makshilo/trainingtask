package com.makshilo.trainingtask.repository.impl

import com.makshilo.trainingtask.config.properties.database.EmployeeDatabaseProperties
import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.repository.AbstractEntityRepository
import com.makshilo.trainingtask.service.database.DataSourceService
import org.springframework.stereotype.Repository

@Repository
class DefaultEmployeeRepository(
  private val dataSourceService: DataSourceService,
  private val employeeDatabaseProperties: EmployeeDatabaseProperties
): AbstractEntityRepository<Employee>() {

  override fun create(entity: Employee): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getInsertQuery(employeeDatabaseProperties.tableName, employeeDatabaseProperties.columns).trim()
        )
          .use { statement ->
            statement.setLong(1, 0)
            statement.setString(2, entity.surname)
            statement.setString(3, entity.name)
            statement.setString(4, entity.patronymic)
            statement.setString(5, entity.position)
            statement.executeUpdate() > 0
          }
      }
  }

  override fun findAll(): List<Employee> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getSelectQuery(employeeDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.executeQuery()
              .use { resultSet ->
                val employees = mutableListOf<Employee>()
                while (resultSet.next()) {
                  employees.add(
                    Employee(
                      id = resultSet.getLong("id"),
                      surname = resultSet.getString("surname"),
                      name = resultSet.getString("name"),
                      patronymic = resultSet.getString("patronymic"),
                      position = resultSet.getString("position")
                    )
                  )

                }
                employees
              }
          }
      }
  }

  override fun findById(id: Long): Employee? {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getSelectByIdQuery(employeeDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeQuery()
              .use { resultSet ->
                if (resultSet.next()) {
                  Employee(
                    id = resultSet.getLong("id"),
                    surname = resultSet.getString("surname"),
                    name = resultSet.getString("name"),
                    patronymic = resultSet.getString("patronymic"),
                    position = resultSet.getString("position")
                  )
                } else {
                  null
                }
              }
          }

      }
  }

  override fun update(entity: Employee): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          getUpdateQuery(employeeDatabaseProperties.tableName, employeeDatabaseProperties.columns).trim()
        )
          .use { statement ->
            entity.id?.let { statement.setLong(1, it) }
            statement.setString(2, entity.surname)
            statement.setString(3, entity.name)
            statement.setString(4, entity.patronymic)
            statement.setString(5, entity.position)
            statement.executeUpdate() > 0
          }
      }
  }

  override fun deleteById(id: Long): Boolean {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(getDeleteQuery(employeeDatabaseProperties.tableName).trim())
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate() > 0
          }
      }
  }
}
