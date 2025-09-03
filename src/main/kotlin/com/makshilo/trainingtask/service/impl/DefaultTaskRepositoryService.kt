package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Task
import com.makshilo.trainingtask.repository.TaskRepository
import com.makshilo.trainingtask.service.TaskRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultTaskRepositoryService(
  private val taskRepository: TaskRepository,
): TaskRepositoryService {

  override fun add(task: Task): Task = taskRepository.add(task)

  override fun findAll(): List<Task> = taskRepository.findAll()

  override fun findById(id: Long): Task? = taskRepository.findById(id)

  override fun update(task: Task): Task = taskRepository.update(task)

  override fun remove(id: Long) = taskRepository.remove(id)
}
