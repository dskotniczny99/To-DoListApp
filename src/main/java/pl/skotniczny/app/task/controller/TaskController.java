package pl.skotniczny.app.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skotniczny.app.task.TaskMapper;
import pl.skotniczny.app.task.dto.request.CreateTaskRequestDto;
import pl.skotniczny.app.task.dto.response.*;
import pl.skotniczny.app.task.error.ErrorTaskResponseDto;
import pl.skotniczny.app.task.model.Task;
import pl.skotniczny.app.task.service.TaskService;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.service.UserRetriever;

import java.util.List;
import java.util.Optional;

import static pl.skotniczny.app.task.TaskMapper.mapFromAllTasksToGetAllTasksResponseDto;
import static pl.skotniczny.app.task.TaskMapper.mapFromTaskToDeleteTaskResponseDto;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserRetriever userRetriever;

    @PostMapping
    public ResponseEntity<CreateTaskResponseDto> createTask(@RequestBody CreateTaskRequestDto requestDto) {
        Optional<User> user = userRetriever.findByUserId(requestDto.userId());
        if (user.isPresent()) {
            Task task = TaskMapper.mapFromCreateTaskRequestDtoToTask(requestDto, user.get());
            Task savedTask = taskService.saveTask(task);
            CreateTaskResponseDto responseDto = TaskMapper.mapFromTaskToCreateTaskResponseDto(savedTask);
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<GetAllTasksResponseDto> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        GetAllTasksResponseDto allTaskResponseDto = mapFromAllTasksToGetAllTasksResponseDto(allTasks);
        return ResponseEntity.ok(allTaskResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseDto> getTasksById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        GetTaskResponseDto responseDto = TaskMapper.mapFromTaskToGetTasksResponseDto(task);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<DeleteTaskResponseDto> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        DeleteTaskResponseDto responseDto = mapFromTaskToDeleteTaskResponseDto(id);
        return ResponseEntity.ok(responseDto);
    }




}
