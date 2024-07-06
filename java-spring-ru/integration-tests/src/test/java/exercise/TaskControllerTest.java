package exercise;

import exercise.util.BaseUtil;
import exercise.util.TestUtil;
import net.javacrumbs.jsonunit.assertj.JsonAssert;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.setMaxElementsForPrinting;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private BaseUtil baseUtil;
    private List<Task> tasksTestList;
    private final int QUANTITY_OF_TASKS = 3;

    @BeforeEach
    public void prepareTasks() throws Exception {

        tasksTestList =  baseUtil.generateTestTasksData(QUANTITY_OF_TASKS);
        baseUtil.saveTestData(tasksTestList);
        tasksTestList.forEach(System.out::println);
    }

    @Test
    @Order(1)
    public void testWelcomePage() throws Exception {
        /*MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();*/

        var body = testUtil.peformRequest(mockMvc, get("/"),status().isOk())
                .getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    @Order(2)
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = testUtil.peformRequest(mockMvc, get("/tasks"),status().isOk())
                .getContentAsString();
        assertThatJson(body)
                .isArray()
                .hasSize(QUANTITY_OF_TASKS);
    }


    // BEGIN
    @Test
    @Order(3)
    public void postTask() throws Exception {
        var taskMap = new HashMap<String, String>();
        taskMap.put("title", faker.lorem().word());
        taskMap.put("description", faker.lorem().paragraph());

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskMap));

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        assertThatJson(result.getResponse().getContentAsString())
                .and(
                        a -> a.node("title").isEqualTo(taskMap.get("title")),
                        a -> a.node("description").isEqualTo(taskMap.get("description"))
                );

    }

    @Test
    @Order(4)
    public void testGetTask() throws Exception {
//        var task4Save = Instancio.of(Task.class)
//                .ignore(Select.field(Task::getId))
//                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
//                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
//                .create();
//        taskRepository.save(task4Save);


        for (Task task : tasksTestList) {


            MvcResult mvcResult = mockMvc.perform(get("/tasks/" + task.getId()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertThatJson(mvcResult.getResponse().getContentAsString())
                    .and(
                            a -> a.node("id").isEqualTo(task.getId()),
                            a -> a.node("title").isEqualTo(task.getTitle()),
                            a -> a.node("description").isEqualTo(task.getDescription())

                    );
        }

    }

    @Test
    @Order(5)
    public void testUpdateTask() throws Exception {
  /*      var task4Save = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        taskRepository.save(task4Save);*/

        var taskMap = new HashMap<String, String>();

        for (Task task : tasksTestList) {
            taskMap.put("title", faker.lorem().word());
            taskMap.put("description", faker.lorem().paragraph());

            var request = put("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    // ObjectMapper конвертирует Map в JSON
                    .content(om.writeValueAsString(taskMap));

            MvcResult mvcResult = mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            assertThatJson(mvcResult.getResponse().getContentAsString())
                    .and(
                            a -> a.node("id").isEqualTo(task.getId()),
                            a -> a.node("title").isEqualTo(taskMap.get("title")),
                            a -> a.node("description").isEqualTo(taskMap.get("description"))

                    );
        }

    }

    @Test
    @Order(6)
    public void testDeleteTask() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/tasks/" + tasksTestList.get(1).getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertThat(taskRepository.existsById(tasksTestList.get(1).getId())).isFalse();

        MvcResult mvcGetResult = mockMvc.perform(get("/tasks/" + tasksTestList.get(1).getId()))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();

    }
    // END
}
