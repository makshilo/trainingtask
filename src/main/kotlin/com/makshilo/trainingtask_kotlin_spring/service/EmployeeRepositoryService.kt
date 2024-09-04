package com.makshilo.trainingtask_kotlin_spring.service

import com.makshilo.trainingtask_kotlin_spring.model.Employee

interface EmployeeRepositoryService {

  fun create(employee: Employee): Boolean

  fun findAll(): List<Employee>

  fun findById(id: Long): Employee?

  fun update(employee: Employee): Boolean

  fun deleteById(id: Long): Boolean
}