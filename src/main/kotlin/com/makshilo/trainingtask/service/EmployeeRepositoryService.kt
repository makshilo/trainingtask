package com.makshilo.trainingtask.service

import com.makshilo.trainingtask.model.Employee

interface EmployeeRepositoryService {

  fun create(employee: Employee): Boolean

  fun findAll(): List<Employee>

  fun findById(id: Long): Employee?

  fun update(employee: Employee): Boolean

  fun deleteById(id: Long): Boolean
}
