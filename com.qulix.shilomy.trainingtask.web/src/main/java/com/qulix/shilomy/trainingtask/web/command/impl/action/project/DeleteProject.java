package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class DeleteProject implements Command {
    private static DeleteProject instance;
    private final RequestFactory requestFactory;

    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_PROJECT_LIST = "/controller?command=projectsPage";

    private final ProjectService projectService;

    private DeleteProject(ProjectService projectService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.projectService = projectService;
    }

    public static synchronized DeleteProject getInstance(ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new DeleteProject(projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Long projectId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        projectService.delete(projectId);
        return requestFactory.createRedirectResponse(COMMAND_PROJECT_LIST);
    }
}
