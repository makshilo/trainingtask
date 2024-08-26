package com.makshilo.trainingtask_kotlin_spring.service.impl

import com.makshilo.trainingtask_kotlin_spring.model.Employee
import com.makshilo.trainingtask_kotlin_spring.repository.AbstractEntityRepository
import com.makshilo.trainingtask_kotlin_spring.service.EmployeeRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultEmployeeRepositoryService(
  private val employeeRepository: AbstractEntityRepository<Employee>
): EmployeeRepositoryService {

  override fun create(employee: Employee): Boolean = employeeRepository.create(employee)

  override fun findAll(): List<Employee> = employeeRepository.findAll()

  override fun findById(id: Long): Employee? = employeeRepository.findById(id)

  override fun update(employee: Employee): Boolean = employeeRepository.update(employee)
}