package pl.skotniczny.app.task.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDto(
        Long id,
        String title,
        String description,
        String category,
        String priority,
        LocalDateTime dueDate,
        Long userId
) {
}