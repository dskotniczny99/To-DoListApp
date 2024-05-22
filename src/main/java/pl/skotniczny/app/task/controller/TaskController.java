package pl.skotniczny.app.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skotniczny.app.task.dto.request.CreateTaskRequestDto;
import pl.skotniczny.app.task.dto.response.CreateTaskResponseDto;
import pl.skotniczny.app.task.model.Task;
import pl.skotniczny.app.task.service.TaskService;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.service.UserRetriever;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
            Task task = new Task();
            task.setTitle(requestDto.title());
            task.setDescription(requestDto.description());
            task.setCategory(requestDto.category());
            task.setPriority(requestDto.priority());
            task.setDueDate(requestDto.dueDate());
            task.setUser(user.get());

            Task savedTask = taskService.saveTask(task);
            CreateTaskResponseDto responseDto =
                    CreateTaskResponseDto.builder()
                            .id(savedTask.getId())
                            .title(savedTask.getTitle())
                            .description(savedTask.getDescription())
                            .category(savedTask.getCategory())
                            .priority(savedTask.getPriority())
                            .dueDate(savedTask.getDueDate())
                            .userId(savedTask.getId()).build();


            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.badRequest().build();
    }


}
