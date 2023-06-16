package com.globallogic.ejerciciobci;

import com.globallogic.ejerciciobci.controllers.dto.UserDTO;
import com.globallogic.ejerciciobci.services.LoginUser;

public class LoginUserDTOTest {
  private LoginUser loginUser;
  private UserDTO userDTO;
//
//  @BeforeEach
//  void setUp() {
//    IUserRepository userRepository = new UserRepositoryInMemory();
//    JWTUtil jwtUtil = new JWTUtil();
//
////    loginUser = UserConfiguration.loginUser(userRepository, jwtUtil);
////    CreateUser createUser = UserConfiguration.createUser(userRepository, jwtUtil);
//
//    userDTO = UserDTO.builder()
//        .id(UUID.randomUUID().toString())
//        .name("name")
//        .email("email@email.com")
//        .password("a2asfGfdfdf4")
//        .phones(Collections.singletonList(PhoneDTO.builder()
//            .number(543764001122L)
//            .cityCode(33)
//            .countryCode("54")
//            .build()))
//        .build();
//
////    userDTO = createUser.create(userDTO);
//  }
//
//  @Test
//  void Should_ReturnUserInformation_When_ValidToken() {
//    UserDTO returnedUserDTO = loginUser.login(userDTO.getToken());
//
//    assertThat(returnedUserDTO.getId()).isEqualTo(userDTO.getId());
//  }
//
//  @Test
//  void Should_UpdateLastLogin_When_ValidToken() {
//    assertThat(userDTO.getLastLogin()).isNull();
//
//    UserDTO returnedUserDTO = loginUser.login(userDTO.getToken());
//
//    assertThat(returnedUserDTO.getLastLogin()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
//  }
//
//  @Test
//  void Should_UpdateToken_When_ValidToken() {
//    String oldToken = userDTO.getToken();
//    UserDTO returnedUserDTO = loginUser.login(userDTO.getToken());
//
//    assertThat(oldToken).isNotEqualTo(returnedUserDTO.getToken());
//  }
//
//  @Test
//  void Should_ThrowException_When_InvalidToken() {
//    assertThrows(RuntimeException.class,
//        () -> loginUser.login("invalid"));
//  }
}
