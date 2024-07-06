package exercise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Component
public class TestUtil {

    @Autowired
    private ObjectMapper om;


    public MockHttpServletResponse peformRequest(MockMvc mockMvc, RequestBuilder mockMvcRequestBuilder,
                                                 ResultMatcher resultMatcher) throws Exception {
        MvcResult result = mockMvc.perform(mockMvcRequestBuilder)
                .andExpect(resultMatcher)
                .andDo(print())
                .andReturn();


        return  result.getResponse();

    }
}
