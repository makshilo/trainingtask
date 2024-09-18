package com.makshilo.trainingtask.model

data class Employee(
    val id: Long? = null,
    val surname: String,
    val name: String,
    val patronymic: String,
    val position: String
): Entity
