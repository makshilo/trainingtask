package com.makshilo.trainingtask_kotlin_spring.model

data class Project(
    val id: Long? = null,
    val name: String,
    val description: String,
): Entity
