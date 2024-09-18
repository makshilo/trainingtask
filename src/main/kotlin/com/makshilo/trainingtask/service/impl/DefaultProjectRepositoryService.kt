package com.makshilo.trainingtask.service.impl

import com.makshilo.trainingtask.model.Project
import com.makshilo.trainingtask.repository.AbstractEntityRepository
import com.makshilo.trainingtask.service.ProjectRepositoryService
import org.springframework.stereotype.Service

@Service
class DefaultProjectRepositoryService(
  private val projectRepository: AbstractEntityRepository<Project>
): ProjectRepositoryService {

  override fun create(project: Project): Boolean = projectRepository.create(project)

  override fun findAll(): List<Project> = projectRepository.findAll()

  override fun findById(id: Long): Project? = projectRepository.findById(id)

  override fun update(project: Project): Boolean = projectRepository.update(project)

  override fun deleteById(id: Long): Boolean = projectRepository.deleteById(id)
}
