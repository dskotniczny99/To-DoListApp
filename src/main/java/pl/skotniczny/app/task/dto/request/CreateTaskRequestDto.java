package pl.skotniczny.app.task.dto.request;

import java.time.LocalDateTime;

public record CreateTaskRequestDto(
        String title,
        String description,
        String category,
        String priority,
        LocalDateTime dueDate,
        Long userId
) {
}
