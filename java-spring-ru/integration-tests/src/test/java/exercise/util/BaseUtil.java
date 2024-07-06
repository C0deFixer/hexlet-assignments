package exercise.util;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.apache.catalina.LifecycleState;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseUtil {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    Faker faker;
    public List<Task> generateTestTasksData(int quantityOfTasks) {
        List<Task> result = new ArrayList<>(quantityOfTasks);
        for (int i = 0; i < quantityOfTasks; i++) {
            var task4Save = Instancio.of(Task.class)
                    .ignore(Select.field(Task::getId))
                    .ignore(Select.field(Task::getUpdatedAt))
                    .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                    .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                    .create();
            result.add(task4Save);
        }

        return result;
    }

    public void saveTestData(List<Task> listData) {
        taskRepository.saveAll(listData);
    }
}
