package com.globallogic.ejerciciobci.services

import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO
import com.globallogic.ejerciciobci.controllers.dto.UserDTO
import com.globallogic.ejerciciobci.repositories.UserRepositorySQL
import com.globallogic.ejerciciobci.repositories.entities.User
import com.globallogic.ejerciciobci.services.CreateUser
import com.globallogic.ejerciciobci.services.util.JWTUtil
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.within

class CreateUserTest extends Specification {
    @Subject
    private CreateUser createUser;
    private UserRepositorySQL userRepository;

    void setup() {
        userRepository = Stub()
        userRepository.findByEmail(_) >> null

        createUser = CreateUser.builder()
                .userRepository(userRepository)
                .jwtUtil(new JWTUtil())
                .build()
    }

    def "Should Contain Current DateTime In Created Field When Created Successfully"() {
        given:
        LocalDateTime createDateTime = LocalDateTime.now();

        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();

        and:
        userRepository.save(_) >> new User(userDTO)

        when:
        User user = createUser.create(userDTO);

        then:
        assertThat(user.getCreated()).isCloseTo(createDateTime, within(500, ChronoUnit.MILLIS));
    }

    def "Should IsActive Be True When Created Successfully"() {
        given:
        boolean isActive = true;

        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build()
        and:
        userRepository.save(_) >> new User(userDTO)

        when:
        User user = createUser.create(userDTO);

        then:
        assertThat(user.getIsActive()).isEqualTo(isActive);
    }

    def "Should Create Successfully When Valid Email"() {
        given:
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build()

        and:
        userRepository.save(_) >> new User(userDTO)

        when:
        createUser.create(userDTO)

        then:
        noExceptionThrown()
    }

    def "Should Create Successfully When Valid Password"() {
        given:
        String password = "a2asfGfdfdf4"

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

        and:
        userRepository.save(_) >> new User(userDTO)

        when:
        createUser.create(userDTO)

        then:
        noExceptionThrown()
    }
}
