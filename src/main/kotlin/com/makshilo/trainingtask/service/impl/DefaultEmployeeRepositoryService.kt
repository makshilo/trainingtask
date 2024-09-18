package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.repository.AbstractEntityRepository
import com.makshilo.trainingtask.service.EmployeeRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultEmployeeRepositoryService(
  private val employeeRepository: AbstractEntityRepository<Employee>
): EmployeeRepositoryService {

  override fun create(employee: Employee): Boolean = employeeRepository.create(employee)

  override fun findAll(): List<Employee> = employeeRepository.findAll()

  override fun findById(id: Long): Employee? = employeeRepository.findById(id)

  override fun update(employee: Employee): Boolean = employeeRepository.update(employee)

  override fun deleteById(id: Long): Boolean  = employeeRepository.deleteById(id)
}
