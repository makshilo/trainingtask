package com.makshilo.trainingtask.service

import com.makshilo.trainingtask.model.Task

interface TaskRepositoryService {

  fun create(task: Task): Boolean

  fun findAll(): List<Task>

  fun findById(id: Long): Task?

  fun update(task: Task): Boolean

  fun deleteById(id: Long): Boolean
}
