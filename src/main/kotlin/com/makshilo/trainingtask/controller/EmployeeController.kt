package com.makshilo.trainingtask.controller

import com.makshilo.trainingtask.model.Employee
import com.makshilo.trainingtask.service.EmployeeRepositoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeController(
  private val employeeService: EmployeeRepositoryService
) {

  @GetMapping
  fun getAllEmployees(): ResponseEntity<List<Employee>> = ResponseEntity.ok(employeeService.findAll())

  @GetMapping("/{id}")
  fun getEmployee(@PathVariable id: String): ResponseEntity<Employee> {
    val employee = employeeService.findById(id.toLong())
    return if (employee != null) {
      ResponseEntity.ok(employee)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @PostMapping
  fun createEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> =
    ResponseEntity.ok(employeeService.add(employee))

  @PutMapping
  fun updateEmployee(@RequestBody employee: Employee): ResponseEntity<Employee> =
    ResponseEntity.ok(employeeService.update(employee))

  @DeleteMapping("/{id}")
  fun deleteEmployee(@PathVariable id: String): ResponseEntity<Unit> =
    ResponseEntity.ok(employeeService.remove(id.toLong()))
}

