package com.globallogic.ejerciciobci.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.globallogic.ejerciciobci.controllers.UserController
import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO
import com.globallogic.ejerciciobci.controllers.dto.UserDTO
import com.globallogic.ejerciciobci.services.CreateUser
import com.globallogic.ejerciciobci.services.LoginUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.bind.MethodArgumentNotValidException
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertInstanceOf
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest extends Specification {
    @Autowired
    MockMvc mvc;
    @MockBean
    CreateUser createUser
    @MockBean
    LoginUser loginUser

    def "Should Throw Exception When Invalid Email"() {
        given:
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email(email)
                .password("a2asfGfdfdf4")
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();

        when:
        def result = mvc.perform(
                post("/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))

        then:
        result.andExpect(status().isBadRequest())
        result.andExpect(res -> assertInstanceOf(MethodArgumentNotValidException, res.getResolvedException()))

        where:
        email << ["asd", "@asd.com", "asd@.com", "@.com", "@"]
    }

    def "Should Throw Exception When Invalid Password"() {
        given:
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password(password)
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build()

        when:
        def result = mvc.perform(
                post("/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))

        then:
        result.andExpect(status().isBadRequest())
        result.andExpect(res -> assertInstanceOf(MethodArgumentNotValidException, res.getResolvedException()))

        where:
        password << ["s1Gf6fd", "52asfGFfdfdfa", "52asfGFfdfdfab", "a2asfcfdfdf4"]
    }
}
