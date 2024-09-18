package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Task
import com.makshilo.trainingtask.repository.AbstractEntityRepository
import com.makshilo.trainingtask.service.TaskRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultTaskRepositoryService(
  private val taskRepository: AbstractEntityRepository<Task>,
): TaskRepositoryService {

  override fun create(task: Task): Boolean = taskRepository.create(task)

  override fun findAll(): List<Task> = taskRepository.findAll()

  override fun findById(id: Long): Task? = taskRepository.findById(id)

  override fun update(task: Task): Boolean = taskRepository.update(task)

  override fun deleteById(id: Long): Boolean = taskRepository.deleteById(id)
}
