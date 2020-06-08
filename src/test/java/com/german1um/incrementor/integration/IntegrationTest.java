package com.german1um.incrementor.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getNumber() throws Exception {
        //When GET[/number] Then status is Ok And response body is "0"
        this.mockMvc
                .perform(get("/number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
        //When POST[/number/increment] Then status is Ok
        this.mockMvc
                .perform(post("/number/increment"))
                .andExpect(status().isOk());
        //When GET[/number] Then status is Ok And response body is "1"
        this.mockMvc
                .perform(get("/number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        //When POST[/number/setMaximumValue] with body "2" Then status is Ok
        this.mockMvc
                .perform(post("/number/setMaximumValue").contentType(APPLICATION_JSON).content("2"))
                .andExpect(status().isOk());
        //When POST[/number/increment] Then status is Ok
        this.mockMvc
                .perform(post("/number/increment"))
                .andExpect(status().isOk());
        //When GET[/number] Then status is Ok And response body is "0"
        this.mockMvc
                .perform(get("/number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

    }
}