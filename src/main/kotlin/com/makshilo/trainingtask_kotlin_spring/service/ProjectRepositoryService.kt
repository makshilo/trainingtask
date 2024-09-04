package com.makshilo.trainingtask_kotlin_spring.service

import com.makshilo.trainingtask_kotlin_spring.model.Project

interface ProjectRepositoryService {

  fun create(project: Project): Boolean

  fun findAll(): List<Project>

  fun findById(id: Long): Project?

  fun update(project: Project): Boolean

  fun deleteById(id: Long): Boolean
}