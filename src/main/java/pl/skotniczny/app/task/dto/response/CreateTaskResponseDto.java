package pl.skotniczny.app.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateTaskResponseDto(

        Long id,
        String title,
        String description,
        String category,
        String priority,
        LocalDateTime dueDate,
        Long userId
) {
}
