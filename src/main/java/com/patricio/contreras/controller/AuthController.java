package com.patricio.contreras.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.AuthResponseDTO;
import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.AuthRequestDTO;
import com.patricio.contreras.dto.resquest.SignupRequestDTO;
import com.patricio.contreras.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

	 private final UserService userService;

	 //para que inicie sesion el usuario
	  @PostMapping("/sign-in")
	  public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequest) {
	    AuthResponseDTO authResponse = userService.signIn(authRequest);
	    return new ResponseEntity<>(authResponse, HttpStatus.OK);
	  }

	  //para registrar  un usuario
	  @PostMapping("/sign-up")
	  public ResponseEntity<UserProfileResponseDTO> register(@RequestBody @Validated SignupRequestDTO signupRequestDTO) {
	    UserProfileResponseDTO userProfileResponseDTO = userService.signup(signupRequestDTO);
	    return new ResponseEntity<>(userProfileResponseDTO, HttpStatus.CREATED);
	  }
}
