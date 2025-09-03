package com.makshilo.trainingtask.service

import com.makshilo.trainingtask.model.Task

interface TaskRepositoryService {

  fun add(task: Task): Task

  fun findAll(): List<Task>

  fun findById(id: Long): Task?

  fun update(task: Task): Task

  fun remove(id: Long)
}
