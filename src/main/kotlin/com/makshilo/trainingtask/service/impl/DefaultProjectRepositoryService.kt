package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Project
import com.makshilo.trainingtask.repository.ProjectRepository
import com.makshilo.trainingtask.service.ProjectRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultProjectRepositoryService(
  private val projectRepository: ProjectRepository
): ProjectRepositoryService {

  override fun add(project: Project): Project = projectRepository.add(project)

  override fun findAll(): List<Project> = projectRepository.findAll()

  override fun findById(id: Long): Project? = projectRepository.findById(id)

  override fun update(project: Project): Project = projectRepository.update(project)

  override fun remove(id: Long) = projectRepository.remove(id)
}
