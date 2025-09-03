package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.repository.EmployeeRepository
import com.makshilo.trainingtask.service.EmployeeRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultEmployeeRepositoryService(
  private val employeeRepository: EmployeeRepository
): EmployeeRepositoryService {

  override fun add(employee: Employee): Employee = employeeRepository.add(employee)

  override fun findAll(): List<Employee> = employeeRepository.findAll()

  override fun findById(id: Long): Employee? = employeeRepository.findById(id)

  override fun update(employee: Employee): Employee = employeeRepository.update(employee)

  override fun remove(id: Long)  = employeeRepository.remove(id)
}
