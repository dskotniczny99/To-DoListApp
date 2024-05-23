package pl.skotniczny.app.task;

import org.springframework.http.HttpStatus;
import pl.skotniczny.app.task.dto.request.CreateTaskRequestDto;
import pl.skotniczny.app.task.dto.response.*;
import pl.skotniczny.app.task.error.ErrorTaskResponseDto;
import pl.skotniczny.app.task.model.Task;
import pl.skotniczny.app.user.dto.response.DeleteUserResponseDto;
import pl.skotniczny.app.user.dto.response.GetAllUsersResponseDto;
import pl.skotniczny.app.user.dto.response.UserDto;
import pl.skotniczny.app.user.mapper.UserMapper;
import pl.skotniczny.app.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static Task mapFromCreateTaskRequestDtoToTask(CreateTaskRequestDto dto, User user) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setCategory(dto.category());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setUser(user);
        return task;
    }

    public static CreateTaskResponseDto mapFromTaskToCreateTaskResponseDto(Task task) {
        return CreateTaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .userId(task.getUser().getId())
                .build();
    }

    public static GetAllTasksResponseDto mapFromAllTasksToGetAllTasksResponseDto(List<Task> allTasks) {
        List<TaskDto> tasksDtos = allTasks.stream()
                .map(TaskMapper::mapFromTaskToTaskDto).collect(Collectors.toList());
        return new GetAllTasksResponseDto(tasksDtos);
    }

    public static TaskDto mapFromTaskToTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .userId(task.getUser().getId())
                .build();
    }

    public static GetTaskResponseDto mapFromTaskToGetTasksResponseDto(Task task) {
        return GetTaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .category(task.getCategory())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .userId(task.getUser().getId())
                .build();
    }

    public static DeleteTaskResponseDto mapFromTaskToDeleteTaskResponseDto(Long id) {
        return new DeleteTaskResponseDto("You deleted task with id:  " + id, HttpStatus.OK);
    }

}
