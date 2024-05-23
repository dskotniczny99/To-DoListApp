package pl.skotniczny.app.task.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.skotniczny.app.task.TaskMapper;
import pl.skotniczny.app.task.dto.request.CreateTaskRequestDto;
import pl.skotniczny.app.task.dto.response.CreateTaskResponseDto;
import pl.skotniczny.app.task.model.Task;
import pl.skotniczny.app.task.service.TaskService;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.service.UserRetriever;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private UserRetriever userRetriever;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void create_Task_Should_Return_OK_Response_When_User_Exists_In_Database() {
        // Given
        CreateTaskRequestDto requestDto = CreateTaskRequestDto.builder()
                .title("Test Task")
                .description("This is a test task")
                .category("Test")
                .priority("High")
                .dueDate(LocalDateTime.now())
                .userId(1L)
                .build();

        User user = new User();
        user.setId(1L);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        task.setCategory("Test");
        task.setPriority("High");
        task.setDueDate(requestDto.dueDate());
        task.setUser(user);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Test Task");
        savedTask.setDescription("This is a test task");
        savedTask.setCategory("Test");
        savedTask.setPriority("High");
        savedTask.setDueDate(requestDto.dueDate());
        savedTask.setUser(user);

        CreateTaskResponseDto responseDto = TaskMapper.mapFromTaskToCreateTaskResponseDto(savedTask);

        when(userRetriever.findByUserId(requestDto.userId())).thenReturn(Optional.of(user));
        when(taskService.saveTask(any(Task.class))).thenReturn(savedTask);

        // When
        ResponseEntity<CreateTaskResponseDto> responseEntity = taskController.createTask(requestDto);

        // Then
        assertEquals(ResponseEntity.ok(responseDto), responseEntity);
    }


    @Test
    void createTask_shouldReturnBadRequest_whenUserDoesNotExist() {
        // Given
        CreateTaskRequestDto requestDto = CreateTaskRequestDto.builder()
                .title("Test Task")
                .description("This is a test task")
                .category("Test")
                .priority("High")
                .dueDate(LocalDateTime.now())
                .userId(1L)
                .build();

        when(userRetriever.findByUserId(requestDto.userId())).thenReturn(Optional.empty());

        // When
        ResponseEntity<CreateTaskResponseDto> responseEntity = taskController.createTask(requestDto);

        // Then
        assertEquals(ResponseEntity.badRequest().build(), responseEntity);
    }

}