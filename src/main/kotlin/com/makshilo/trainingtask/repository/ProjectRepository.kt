package com.makshilo.trainingtask.repository

import com.makshilo.trainingtask.model.Project

interface ProjectRepository {
  fun add(project: Project): Project
  fun update(project: Project): Project
  fun remove(id: Long)
  fun findById(id: Long): Project?
  fun findAll(): List<Project>
}
