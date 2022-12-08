package com.sn.sir.dematest.controller;

import com.sn.sir.dematest.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void Hello(){
        String name= "tonux";
        String url = "http://localhost:" + port + "/api/person/hello?name="+name;
        String response = restTemplate.getForObject(url, String.class);
        assertEquals("Hello "+name, response);
    }

    @Test
    void getAllPerson() throws Exception {
        //Given
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person")
                .contentType(MediaType.APPLICATION_JSON);

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getPerson() throws Exception {
       /* int name=1;
        RequestBuilder request = MockMvcRequestBuilders
                .get("http://localhost:"+ port +"/api/person"+name)
                .contentType(MediaType.APPLICATION_JSON);

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());*/
        String url="http://localhost:" + port + "/api/person/"+1;
        Person person=restTemplate.getForObject(url, Person.class);
        assertEquals(0,person.getId());
        // TODO : add test getPerson
    }

    @Test
    void createPerson() {
        // TODO : add test createPerson
        Person person=new Person("tall","tallahmad047@gmail.com","784219485");
        person.setId(1);
        String url="http://localhost:" + port + "/api/person";
        Person person1=restTemplate.postForObject(url,person, Person.class);
        assertEquals(person.getName(),person1.getName());
    }

    @Test
    void updatePerson() {
        // TODO : add test updatePerson
        String url="http://localhost:" + port + "/api/person"+1;
        Person person=new Person("tall","tallahmad047@gmail.com","784219485");
        person.setId(1);
        person.setName("ahmad");
        restTemplate.put(url,person);
        assertEquals("ahmad",person.getName());
    }

    @Test
    void deletePerson() {
        // TODO : add test deletePerson
        String url="http://localhost:" + port + "/api/person/1";
        String geturl="http://localhost:" + port + "/api/person";
        Person person=new Person("tall","tallahmad047@gmail.com","784219485");
        Person person1=restTemplate.postForObject(geturl,person,Person.class);
        person.setId(1);

        restTemplate.delete(url,person);
        assertEquals(1,person.getId());
    }
}