package com.makshilo.trainingtask_kotlin_spring.model

data class Employee(
    val id: Long? = null,
    val surname: String,
    val name: String,
    val patronymic: String,
    val position: String
): Entity
