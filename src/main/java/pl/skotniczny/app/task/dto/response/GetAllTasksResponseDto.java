package pl.skotniczny.app.task.dto.response;

import java.util.List;

public record GetAllTasksResponseDto(List<TaskDto> allTasks) {
}
