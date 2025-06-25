package com.patricio.contreras.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.dto.response.AuthResponseDTO;
import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.SignupRequestDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
	
	private final ModelMapper modelMapper;
	
	public User toUser(SignupRequestDTO signupRequestDTO) {
		return modelMapper.map(signupRequestDTO, User.class);
	}
	

	public UserProfileResponseDTO toUserProfileResponseDTO(User user) {
		return modelMapper.map(user, UserProfileResponseDTO.class);
	}

	public AuthResponseDTO toAuthResponseDTO (String token,UserProfileResponseDTO userProfile) {
		AuthResponseDTO authResponseDTO = new AuthResponseDTO();
		authResponseDTO.setToken(token);
		authResponseDTO.setUser(userProfile);
		
		return authResponseDTO;
	}

	public List<UserProfileResponseDTO> toUserProfileResponseDTOList(List<User> users) {
		return users.stream()
				.map(this::toUserProfileResponseDTO)
				.toList();
	}

}
