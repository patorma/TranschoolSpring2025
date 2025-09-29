package com.patricio.contreras.service;

import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.domain.enums.Role;
import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.SignupRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.mapper.UserMapper;
import com.patricio.contreras.repository.UserRepository;
import com.patricio.contreras.security.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
/*Aquí defines lo que se va a simular (mockear),
en lugar de usar implementaciones reales.
Esto te
permite probar solo la lógica del UserService sin conectarte
 a BD, sin encriptar de verdad, etc.*/
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private TokenProvider tokenProvider;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserService userService;

    //@BeforeEach se ejecuta antes de cada test.
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMapper = mock(UserMapper.class);
        tokenProvider = mock(TokenProvider.class);
        authenticationManagerBuilder = mock(AuthenticationManagerBuilder.class);

        userService = new UserService(
                userRepository,
                passwordEncoder,
                userMapper,
                tokenProvider,
                authenticationManagerBuilder
        );
    }

    @Test
    void crearUsuario_DeberiaCrearConRolApoderado() {
        // Arrange (preparar)
        SignupRequestDTO request = new SignupRequestDTO();
        request.setEmail("test@mail.com");
        request.setPassword("1234");

        User user = new User();
        user.setEmail(request.getEmail());

        UserProfileResponseDTO responseDTO = new UserProfileResponseDTO();
        responseDTO.setEmail("test@mail.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.toUser(request)).thenReturn(user);
        when(passwordEncoder.encode("1234")).thenReturn("encoded");
        when(userMapper.toUserProfileResponseDTO(user)).thenReturn(responseDTO);

        // Act (ejecutar)
        UserProfileResponseDTO result = userService.crearUsuario(request);

        // Assert (verificar)
        assertEquals("test@mail.com", result.getEmail());
        assertEquals(Role.APODERADO, user.getRole());
        assertEquals("encoded", user.getPassword());

        verify(userRepository).save(user);
    }

    @Test
    void crearUsuario_DeberiaLanzarExcepcionSiEmailExiste() {
        SignupRequestDTO request = new SignupRequestDTO();
        request.setEmail("test@mail.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> userService.crearUsuario(request));
    }


}
