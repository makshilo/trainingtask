package com.qulix.shilomy.trainingtask.wicket.form;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.task.TaskListPage;
import com.qulix.shilomy.trainingtask.wicket.validator.DateValidator;
import com.qulix.shilomy.trainingtask.wicket.validator.WorkValidator;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.*;
import org.apache.wicket.util.value.ValueMap;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

public class TaskForm extends Form<ValueMap> {
    private static final String TASK_NAME_LABEL = "Наименование";
    private static final String PROJECT_NAME_LABEL = "Наименование проекта";
    private static final String WORK_LABEL = "Работа";
    private static final String START_DATE_LABEL = "Дата начала";
    private static final String END_DATE_LABEL = "Дата окончания";
    private static final String STATUS_LABEL = "Статус";
    private static final String EXECUTOR_LABEL = "Исполнитель";
    private static final String TASK_NAME_ERROR = "taskNameError";
    private static final String PROJECT_ID_ERROR = "projectError";
    private static final String WORK_ERROR = "workError";
    private static final String START_DATE_ERROR = "startDateError";
    private static final String END_DATE_ERROR = "endDateError";
    private static final String STATUS_ERROR = "statusError";
    private static final String EXECUTOR_ERROR = "executorError";
    private static final String NAME = "name";

    TaskEntity task;

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    public TaskForm(String id, TaskEntity task, boolean projectLock) {
        super(id, new CompoundPropertyModel<>(new ValueMap()));

        this.task = task;

        if (task.getId() != null) {
            setModelObject(new ValueMap(
                    Map.of(TaskParam.TASK_NAME.get(), task.getName(),
                            TaskParam.PROJECT.get(), projectService.get(task.getProjectId()),
                            TaskParam.WORK.get(), task.getWork(),
                            TaskParam.START_DATE.get(), task.getStartDate().toString(),
                            TaskParam.END_DATE.get(), task.getEndDate().toString(),
                            TaskParam.STATUS.get(), task.getStatus(),
                            TaskParam.EXECUTOR.get(), employeeService.get(task.getExecutorId()))));
        } else {
            setModelObject(new ValueMap(
                    /* Map.of(TaskParam.TASK_NAME.get(), task.getName(),
                            TaskParam.PROJECT.get(), projectService.get(task.getProjectId()),
                            TaskParam.WORK.get(), task.getWork(),
                            TaskParam.STATUS.get(), task.getStatus(),
                            TaskParam.EXECUTOR.get(), employeeService.get(task.getExecutorId())
                            ) */
                        )
                    );
        }

        add(new TextField<String>(TaskParam.TASK_NAME.get())
                .setLabel(Model.of(TASK_NAME_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(TASK_NAME_ERROR, get(TaskParam.TASK_NAME.get())));

        add(new DropDownChoice<>(TaskParam.PROJECT.get(),
                projectService.findAll(),
                new ChoiceRenderer<>(NAME, ProjectParam.ID.get()))
                .setLabel(Model.of(PROJECT_NAME_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(PROJECT_ID_ERROR, get(TaskParam.PROJECT.get())));

        if (projectLock) {
            get(TaskParam.PROJECT.get()).setEnabled(false);
        }

        add(new TextField<String>(TaskParam.WORK.get())
                .setLabel(Model.of(WORK_LABEL))
                .setRequired(true)
                .add(new WorkValidator()));
        add(new ComponentFeedbackPanel(WORK_ERROR, get(TaskParam.WORK.get())));

        add(new TextField<String>(TaskParam.START_DATE.get())
                .setLabel(Model.of(START_DATE_LABEL))
                .setRequired(true)
                .add(new DateValidator()));
        add(new ComponentFeedbackPanel(START_DATE_ERROR, get(TaskParam.START_DATE.get())));

        add(new TextField<String>(TaskParam.END_DATE.get())
                .setLabel(Model.of(END_DATE_LABEL))
                .setRequired(true)
                .add(new DateValidator()));
        add(new ComponentFeedbackPanel(END_DATE_ERROR, get(TaskParam.END_DATE.get())));

        add(new DropDownChoice<>(
                TaskParam.STATUS.get(),
                Arrays.asList(TaskStatus.values()),
                new ChoiceRenderer<>(TaskParam.STATUS.get()))
                .setLabel(Model.of(STATUS_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(STATUS_ERROR, get(TaskParam.STATUS.get())));

        add(new DropDownChoice<>(
                TaskParam.EXECUTOR.get(),
                employeeService.findAll(),
                new ChoiceRenderer<>(EmployeeParam.LAST_NAME.get(), EmployeeParam.ID.get()))
                .setLabel(Model.of(EXECUTOR_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(EXECUTOR_ERROR, get(TaskParam.EXECUTOR.get())));
    }

    @Override
    protected void onSubmit() {
        ValueMap values = getModelObject();

        if (task.getId() != null) {
            taskService.update(new TaskEntity(
                    (TaskStatus) values.get(TaskParam.STATUS.get()),
                    (String) values.get(TaskParam.TASK_NAME.get()),
                    ((ProjectEntity) values.get(TaskParam.PROJECT.get())).getId(),
                    ((String) values.get(TaskParam.WORK.get())),
                    LocalDate.parse((String) values.get(TaskParam.START_DATE.get())),
                    LocalDate.parse((String) values.get(TaskParam.END_DATE.get())),
                    ((EmployeeEntity) values.get(TaskParam.EXECUTOR.get())).getId(),
                    task.getId()));
        } else {
            taskService.add(new TaskEntity(
                    (TaskStatus) values.get(TaskParam.STATUS.get()),
                    (String) values.get(TaskParam.TASK_NAME.get()),
                    ((ProjectEntity) values.get(TaskParam.PROJECT.get())).getId(),
                    ((String) values.get(TaskParam.WORK.get())),
                    LocalDate.parse((String) values.get(TaskParam.START_DATE.get())),
                    LocalDate.parse((String) values.get(TaskParam.END_DATE.get())),
                    ((EmployeeEntity) values.get(TaskParam.EXECUTOR.get())).getId()));
        }
        setResponsePage(TaskListPage.class);
    }
}
