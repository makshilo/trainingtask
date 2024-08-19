package com.makshilo.trainingtask_kotlin_spring.repository

import com.makshilo.trainingtask_kotlin_spring.model.Entity

abstract class AbstractEntityRepository<T: Entity> {

  abstract fun create(entity: T): Boolean

  abstract fun findAll(): List<T>

  abstract fun findById(id: Long): T?

  abstract fun update(entity: T): Boolean

  protected fun getSelectQuery(tableName: String) =
    "SELECT * FROM $tableName"

  protected fun getSelectByIdQuery(tableName: String) =
    "SELECT * FROM $tableName WHERE id=?"

  protected fun getInsertQuery(tableName: String, columns: List<String>) =
    "INSERT INTO $tableName VALUES(${List(columns.size + 1) { "?" }.joinToString(", ")})"

  protected fun getUpdateQuery(tableName: String, columns: List<String>) =
    "UPDATE $tableName SET $columns WHERE id=?"

  protected fun getDeleteQuery(tableName: String) =
    "DELETE FROM $tableName WHERE id=?"
}