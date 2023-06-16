package com.globallogic.ejerciciobci.services

import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO
import com.globallogic.ejerciciobci.controllers.dto.UserDTO
import com.globallogic.ejerciciobci.repositories.entities.User
import com.globallogic.ejerciciobci.services.util.JWTUtil
import spock.lang.Specification
import spock.lang.Subject

import static org.assertj.core.api.Assertions.assertThat

class JWTUtilTest extends Specification {
    @Subject
    private JWTUtil jwtUtil = new JWTUtil()

    def "Should Contain Token When Created Successfully"() {
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

        when:
        String JWT = jwtUtil.sign(new User(userDTO))

        then:
        assertThat(JWT).isNotEmpty();
    }

    def "Should Extract User Email From Token"() {
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

        String token = jwtUtil.sign(new User(userDTO))

        when:
        String userEmail = jwtUtil.verify(token)

        then:
        assertThat(userEmail).isEqualTo(userDTO.getEmail())
    }
}
