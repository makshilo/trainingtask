package com.qulix.shilomy.trainingtask.wicket.table;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.link.TaskDeleteLink;
import com.qulix.shilomy.trainingtask.wicket.link.TaskEditLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.HashMap;
import java.util.List;

public class TaskTable extends ListView<TaskEntity> {
    private static final String EDIT_TASK = "editTask";
    private static final String DELETE_TASK = "deleteTask";

    private PageParameters parameters;

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    public TaskTable(String id, List<TaskEntity> list, PageParameters parameters) {
        super(id, list);
        this.parameters = parameters;
    }

    @Override
    protected void populateItem(ListItem<TaskEntity> item) {
        TaskEntity task = item.getModelObject();
        item.add(new Label(TaskParam.STATUS.get(), task.getStatus().getStatus()));
        item.add(new Label(TaskParam.TASK_NAME.get(), task.getName()));
        item.add(new Label(TaskParam.PROJECT.get(), projectService.get(task.getProjectId()).getName()));
        item.add(new Label(TaskParam.WORK.get(), task.getWork()));
        item.add(new Label(TaskParam.START_DATE.get(), task.getStartDate().toString()));
        item.add(new Label(TaskParam.END_DATE.get(), task.getEndDate().toString()));
        item.add(new Label(TaskParam.EXECUTOR.get(), getEmployeeNames().get(task.getExecutorId())));
        item.add(new TaskEditLink(EDIT_TASK, task, parameters));
        item.add(new TaskDeleteLink(DELETE_TASK, task));
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), String.join(" ", employee.getLastName(), employee.getFirstName()));
        }
        return employeeNames;
    }
}


