package pl.skotniczny.app.task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skotniczny.app.task.model.Task;
import pl.skotniczny.app.task.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

}
