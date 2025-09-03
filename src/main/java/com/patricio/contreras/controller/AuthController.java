package com.patricio.contreras.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


      @PostMapping("/crear")
	  public ResponseEntity<UserProfileResponseDTO> crear(@RequestBody @Validated SignupRequestDTO signupRequestDTO){
		  UserProfileResponseDTO userProfileResponseDTO =userService.crearUsuario(signupRequestDTO);
		  return new ResponseEntity<>(userProfileResponseDTO, HttpStatus.CREATED);
	  }


	@GetMapping("/me")
	public ResponseEntity<UserProfileResponseDTO> me(){
		UserProfileResponseDTO userProfileResponseDTO = userService.me();
		return ResponseEntity.ok(userProfileResponseDTO);
	}



}
