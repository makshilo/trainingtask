package com.makshilo.trainingtask.repository

import com.makshilo.trainingtask.model.Employee

interface EmployeeRepository {
  fun add(employee: Employee): Employee
  fun update(employee: Employee): Employee
  fun remove(id: Long)
  fun findById(id: Long): Employee?
  fun findAll(): List<Employee>
}
