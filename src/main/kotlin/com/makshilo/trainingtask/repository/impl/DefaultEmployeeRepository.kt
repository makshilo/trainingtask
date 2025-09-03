package com.makshilo.trainingtask.repository.impl

import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.repository.EmployeeRepository
import com.makshilo.trainingtask.service.database.DataSourceService
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement

@Repository
class DefaultEmployeeRepository(
  private val dataSourceService: DataSourceService,
): EmployeeRepository {

  override fun add(employee: Employee): Employee {
    dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "INSERT INTO trainingtask.employee (surname, name, patronymic, position) VALUES (?, ?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS
        )
          .use { statement ->
            statement.setString(1, employee.surname)
            statement.setString(2, employee.name)
            statement.setString(3, employee.patronymic)
            statement.setString(4, employee.position)
            statement.executeUpdate()
            val generatedKeys = statement.generatedKeys
            return if (generatedKeys.next()) {
              employee.copy(id = generatedKeys.getLong(1))
            } else {
              employee
            }
          }
      }
  }

  override fun findAll(): List<Employee> {
    return dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement("SELECT * FROM trainingtask.employee")
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
        connection.prepareStatement("SELECT * FROM trainingtask.employee WHERE id = ?")
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

  override fun update(employee: Employee): Employee {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement(
          "UPDATE trainingtask.employee SET surname = ?, name = ?, patronymic = ?, position = ? WHERE id = ?",
        )
          .use { statement ->
            statement.setString(1, employee.surname)
            statement.setString(2, employee.name)
            statement.setString(3, employee.patronymic)
            statement.setString(4, employee.position)
            employee.id?.let { id -> statement.setLong(5, id) }
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Employee with id ${employee.id} does not exist and cannot be updated" }
    return employee
  }

  override fun remove(id: Long) {
    val result = dataSourceService.getConnection()
      .use { connection ->
        connection.prepareStatement("DELETE FROM trainingtask.employee WHERE id = ?")
          .use { statement ->
            statement.setLong(1, id)
            statement.executeUpdate()
          }
      }
    require(result != 0) { "Employee with id $id does not exist and cannot be deleted" }
  }
}
