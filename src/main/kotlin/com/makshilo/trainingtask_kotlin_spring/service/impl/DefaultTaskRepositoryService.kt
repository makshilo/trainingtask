package com.makshilo.trainingtask_kotlin_spring.service.impl

import com.makshilo.trainingtask_kotlin_spring.model.Task
import com.makshilo.trainingtask_kotlin_spring.repository.AbstractEntityRepository
import com.makshilo.trainingtask_kotlin_spring.service.TaskRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultTaskRepositoryService(
  private val taskRepository: AbstractEntityRepository<Task>,
): TaskRepositoryService {

  override fun create(task: Task): Boolean = taskRepository.create(task)

  override fun findAll(): List<Task> = taskRepository.findAll()

  override fun findById(id: Long): Task? = taskRepository.findById(id)

  override fun update(task: Task): Boolean = taskRepository.update(task)
}