package com.patricio.contreras.controller;

import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.SignupRequestDTO;
import com.patricio.contreras.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    // para registrar un usuario transportista por parte del admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sign-up")
    public ResponseEntity<UserProfileResponseDTO> registerAdmin(@RequestBody @Validated SignupRequestDTO signupAdminRequestDTO){
        UserProfileResponseDTO userProfileResponseDTO = userService.signupAdmin(signupAdminRequestDTO);

        return new ResponseEntity<>(userProfileResponseDTO, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserProfileResponseDTO>> listarUsuariosSinAdmin(){

        List<UserProfileResponseDTO> usuarios = userService.getUsuariosSinAdmin();
        return ResponseEntity.ok(usuarios);
    }
}
