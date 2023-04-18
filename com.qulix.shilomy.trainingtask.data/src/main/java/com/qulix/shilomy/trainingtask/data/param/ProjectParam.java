package com.qulix.shilomy.trainingtask.data.param;

/**
 * Константы проекта
 */
public enum ProjectParam {

    /**
     * идентификатор
     */
    ID("id"),

    /**
     * проект
     */
    PROJECT("project"),

    /**
     * проекты
     */
    PROJECTS("projects"),

    /**
     * имя
     */
    NAME("projectName"),

    /**
     * описание
     */
    DESCRIPTION("description"),

    /**
     * режим страницы
     */
    PAGE_MODE("pageMode"),

    /**
     * создание
     */
    CREATE("create"),

    /**
     * изменение
     */
    EDIT("edit"),

    /**
     * ошибка
     */
    ERROR("Error"),

    /**
     * путь к списку
     */
    PROJECT_LIST("/projects"),

    /**
     * путь к странице редактирования
     */
    EDIT_PROJECT_PAGE("/jsp/editProject.jsp");

    private final String param;

    ProjectParam(String projectParam) {
        this.param = projectParam;
    }

    public String get() {
        return param;
    }
}
