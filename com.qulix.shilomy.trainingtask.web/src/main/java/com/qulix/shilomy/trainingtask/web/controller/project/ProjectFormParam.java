package com.qulix.shilomy.trainingtask.web.controller.project;

import java.util.ArrayList;
import java.util.List;

public enum ProjectFormParam {
    PROJECT_NAME_PARAM("projectName"),
    DESCRIPTION_PARAM("description");

    private final String param;

    ProjectFormParam(String projectParam) {
        this.param = projectParam;
    }

    public String get() {
        return param;
    }

    public static List<String> getStringValues() {
        List<String> list = new ArrayList<>();
        for (ProjectFormParam value :
                ProjectFormParam.values()) {
            list.add(value.get());
        }

        return list;
    }
}
