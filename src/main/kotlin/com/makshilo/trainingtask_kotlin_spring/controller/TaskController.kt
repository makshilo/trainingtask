package com.makshilo.trainingtask_kotlin_spring.controller

import com.makshilo.trainingtask_kotlin_spring.model.Task
import com.makshilo.trainingtask_kotlin_spring.service.TaskRepositoryService
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
@RequestMapping("/tasks")
class TaskController(
  private val taskService: TaskRepositoryService
) {

  @GetMapping
  fun getAllTasks(): ResponseEntity<List<Task>> = ResponseEntity.ok(taskService.findAll())

  @GetMapping("/{id}")
  fun getTask(@PathVariable id: String): ResponseEntity<Task> {
    val task = taskService.findById(id.toLong())
    return if (task != null) {
      ResponseEntity.ok(task)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @PostMapping
  fun createTask(@RequestBody employee: Task): ResponseEntity<Boolean> =
    ResponseEntity.ok(taskService.create(employee))

  @PutMapping
  fun updateTask(@RequestBody employee: Task): ResponseEntity<Boolean> =
    ResponseEntity.ok(taskService.update(employee))

  @DeleteMapping("/{id}")
  fun deleteTask(@PathVariable id: String): ResponseEntity<Boolean> =
    ResponseEntity.ok(taskService.deleteById(id.toLong()))
}