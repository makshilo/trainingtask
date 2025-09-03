package com.makshilo.trainingtask.repository

import com.makshilo.trainingtask.model.Task

interface TaskRepository {
  fun add(task: Task): Task
  fun update(task: Task): Task
  fun remove(id: Long)
  fun findById(id: Long): Task?
  fun findAll(): List<Task>
}
