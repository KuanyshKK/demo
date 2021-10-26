package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Sample;
import com.example.demo.model.type.StatusType;
import com.example.demo.repository.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoApplication.class)
@AutoConfigureMockMvc
class SampleControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SampleRepository sampleRepository;

    @BeforeEach
    void setUp() {
        sampleRepository.save(Sample.builder().id(1L).name("sample 1").status(StatusType.TODO).build());
        sampleRepository.save(Sample.builder().id(2L).name("sample 2").status(StatusType.IN_PROGRESS).build());
        sampleRepository.save(Sample.builder().id(3L).name("sample 3").status(StatusType.DONE).build());

        log.info("samples=[{}]", sampleRepository.findAll());
    }

    @Test
    void getAllByStatus_SHOULD_return_WHEN_statusesIN_PROGRESS() throws Exception {
        mockMvc.perform(get("/samples/status")
                .param("statuses", "IN_PROGRESS", "TODO"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].status").value(StatusType.TODO.getCaption()))
                .andExpect(jsonPath("$[1].status").value(StatusType.IN_PROGRESS.getCaption()))
        ;
    }

    @Test
    void getAll_SHOULD_returnAll() throws Exception {
        mockMvc.perform(get("/samples"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("sample 1"))
                .andExpect(jsonPath("$[0].status").value(StatusType.TODO.getCaption()))
                .andExpect(jsonPath("$[1].status").value(StatusType.IN_PROGRESS.getCaption()))
        ;
    }
}