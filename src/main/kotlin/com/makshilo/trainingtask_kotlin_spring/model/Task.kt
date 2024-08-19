package com.makshilo.trainingtask_kotlin_spring.model

import java.time.LocalDate

data class Task(
    val id: Long? = null,
    val name: String,
    val project: Project,
    val estimate: Short,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val status: TaskStatus,
    val employee: Employee
): Entity
