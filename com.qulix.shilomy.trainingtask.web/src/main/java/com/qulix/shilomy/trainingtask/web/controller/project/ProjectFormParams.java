package com.qulix.shilomy.trainingtask.web.controller.project;

public enum ProjectFormParams {
    PROJECT_PARAM("project"),
    PROJECTS_PARAM("projects"),
    PROJECT_NAME_PARAM("projectName"),
    DESCRIPTION_PARAM("description");

    private final String param;

    ProjectFormParams(String projectParam) {
        this.param = projectParam;
    }

    public String get() {
        return param;
    }
}
