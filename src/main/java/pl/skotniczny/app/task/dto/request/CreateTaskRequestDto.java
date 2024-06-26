package pl.skotniczny.app.task.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateTaskRequestDto(
        String title,
        String description,
        String category,
        String priority,
        LocalDateTime dueDate,
        Long userId
) {
}
