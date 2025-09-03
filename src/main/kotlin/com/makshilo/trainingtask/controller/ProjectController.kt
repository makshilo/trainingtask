package com.makshilo.trainingtask.controller

import com.makshilo.trainingtask.model.Project
import com.makshilo.trainingtask.service.ProjectRepositoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectController(
  private val projectService: ProjectRepositoryService
) {
  @GetMapping
  fun getAllProjects(): ResponseEntity<List<Project>> = ResponseEntity.ok(projectService.findAll())

  @GetMapping("/{id}")
  fun getProject(@PathVariable id: String): ResponseEntity<Project> {
    val project = projectService.findById(id.toLong())
    return if (project != null) {
      ResponseEntity.ok(project)
    } else {
      ResponseEntity.notFound().build()
    }
  }

  @PostMapping
  fun createProject(@RequestBody project: Project): ResponseEntity<Project> =
    ResponseEntity.ok(projectService.add(project))

  @PutMapping
  fun updateProject(@RequestBody project: Project): ResponseEntity<Project> =
    ResponseEntity.ok(projectService.update(project))

  @DeleteMapping("/{id}")
  fun deleteProject(@PathVariable id: String): ResponseEntity<Unit> =
    ResponseEntity.ok(projectService.remove(id.toLong()))
}
