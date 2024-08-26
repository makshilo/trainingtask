package com.makshilo.trainingtask_kotlin_spring.service

import com.makshilo.trainingtask_kotlin_spring.model.Task

interface TaskRepositoryService {

  fun create(task: Task): Boolean

  fun findAll(): List<Task>

  fun findById(id: Long): Task?

  fun update(task: Task): Boolean
}
