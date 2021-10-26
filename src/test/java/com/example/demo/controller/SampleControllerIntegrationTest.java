package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.model.Sample;
import com.example.demo.model.Status;
import com.example.demo.repository.SampleRepository;
import com.example.demo.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
    StatusRepository statusRepository;

    @Autowired
    SampleRepository sampleRepository;

    @BeforeEach
    void setUp() {
        statusRepository.save(Status.builder().id(1L).code(Status.Code.TO_DO).build());
        statusRepository.save(Status.builder().id(2L).code(Status.Code.IN_PROGRESS).build());

        sampleRepository.save(Sample.builder().id(1L).name("sample 1").statusId(1L).build());
        sampleRepository.save(Sample.builder().id(2L).name("sample 2").statusId(2L).build());

        log.info("statuses, statuses=[{}]",statusRepository.findAll());
        log.info("samples, samples=[{}]",sampleRepository.findAll());
    }

    @Test
    void test() throws Exception {
        log.info("start");

        mockMvc.perform(get("/samples/{status}", 1))
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value("sample 1"))
                .andExpect(jsonPath("$[0].statusId").value(1))
        ;
    }
}