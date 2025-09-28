package com.patricio.contreras.service;

import com.patricio.contreras.dto.resquest.UpdateUserRequestDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.domain.enums.Role;
import com.patricio.contreras.dto.response.AuthResponseDTO;
import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.AuthRequestDTO;
import com.patricio.contreras.dto.resquest.SignupRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.UserMapper;
import com.patricio.contreras.repository.UserRepository;
import com.patricio.contreras.security.TokenProvider;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

	  private final UserRepository userRepository;


	  //Para encodear el password
	  private final PasswordEncoder passwordEncoder;
	  private final UserMapper userMapper;

	  private final TokenProvider tokenProvider;
	  private final AuthenticationManagerBuilder authenticateManagerBuilder;
	  
	  
	  //se recuperan las credenciales en el inicio de sesion
	  @Transactional(readOnly = true)
	  public AuthResponseDTO signIn(AuthRequestDTO authRequest) {
		  
		    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		    	      authRequest.getEmail(),
		    	      authRequest.getPassword()
		    	    );
		    	    Authentication authentication = authenticateManagerBuilder.getObject().authenticate(authenticationToken);
		    	    //asignamos ese usuario que quiere iniciar sesion a un token en el contexto de
		    	    //spring security
		    	    SecurityContextHolder.getContext().setAuthentication(authentication);

		    	    //creamos el token que se hace en un proceso de inicio de sesion
		    	    String accessToken = tokenProvider.createAccessToken(authentication);
		    	   //se hace busqueda de usuario por correo  para garantizar que sea el correcto
		    	    UserProfileResponseDTO userProfileDTO = findByEmail(authRequest.getEmail());
		    	//con lo anterior retornamos las respuestas
		    	    return userMapper.toAuthResponseDTO(accessToken, userProfileDTO);
		  
	  }
	  
	//registro usuario
	/*  @Transactional
	  public UserProfileResponseDTO signup(SignupRequestDTO signupFormDTO) {
	    boolean emailAlreadyExists = userRepository.existsByEmail(signupFormDTO.getEmail());

	    if (emailAlreadyExists) {
	      throw new BadRequestException("El email ya está siendo usado por otro usuario.");
	    }

	    User user = userMapper.toUser(signupFormDTO);
	    user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
	    user.setRole(Role.APODERADO);//se pone como defecto el usuario  como apoderado

	    userRepository.save(user);

	    return userMapper.toUserProfileResponseDTO(user);
	  }*/
	@Transactional
	public UserProfileResponseDTO crearUsuario(SignupRequestDTO signupFormDTO){
		boolean emailAlreadyExists = userRepository.existsByEmail(signupFormDTO.getEmail());

		if (emailAlreadyExists) {
			throw new BadRequestException("El email ya está siendo usado por otro usuario.");
		}

		User user = userMapper.toUser(signupFormDTO);
		user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
		user.setRole(Role.APODERADO);
		userRepository.save(user);
		return userMapper.toUserProfileResponseDTO(user);
	}

	//Update de usuarios
	@Transactional
	public UserProfileResponseDTO updateUsuario(Long id, UpdateUserRequestDTO dto){
       User user = userRepository.findById(id)
			   .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

		if (dto.getNombres() != null) user.setNombres(dto.getNombres());
		if (dto.getApellidos() != null) user.setApellidos(dto.getApellidos());
		if (dto.getComuna() != null) user.setComuna(dto.getComuna());
		if (dto.getTelefono() != null) user.setTelefono(dto.getTelefono());
		if (dto.getEmail() != null) user.setEmail(dto.getEmail());
		if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		userRepository.save(user);
		return userMapper.toUserProfileResponseDTO(user);

	}
//Registrar tipo usuario transportista por parte de admin
   @Transactional
	public UserProfileResponseDTO signupAdmin(SignupRequestDTO signupAdminFormDTO){

	   boolean emailAlreadyExists = userRepository.existsByEmail(signupAdminFormDTO.getEmail());

	   if (emailAlreadyExists) {
		   throw new BadRequestException("El email ya esta asociado a un transportista!!!!!.");
	   }
	   User user = userMapper.toUser(signupAdminFormDTO);
	   user.setPassword(passwordEncoder.encode(signupAdminFormDTO.getPassword()));
	   user.setRole(Role.TRANSPORTISTA);//se pone como defecto el usuario como transportista para el administrador

	   userRepository.save(user);
	   return userMapper.toUserProfileResponseDTO(user);
   }

	  @Transactional(readOnly = true)
	  public UserProfileResponseDTO findByEmail(String email) {
	    User user = userRepository.findOneByEmail(email)
	      .orElseThrow(ResourceNotFoundException::new);

	    return userMapper.toUserProfileResponseDTO(user);
	  }

	  @Transactional(readOnly = true)
	  public UserProfileResponseDTO findByIdUser(Long id){
		  // Obtener el email del usuario autenticado (admin)
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String currentEmail = authentication.getName();

		  // Buscar el usuario autenticado
		  User currentUser = userRepository.findOneByEmail(currentEmail)
				  .orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado no encontrado"));

		  // Buscar el usuario que se quiere consultar
		  User user = userRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("El usuario con id: " + id + " no existe"));

		  // Si el usuario a consultar también es admin Y no es el mismo admin autenticado
		  if (user.getRole() == Role.ADMIN && !user.getId().equals(currentUser.getId())) {
			  throw new BadRequestException("No puedes acceder a la información de otro administrador.");
		  }

		  return userMapper.toUserProfileResponseDTO(user);
	  }

	  @Transactional(readOnly = true)
	  public Page<UserProfileResponseDTO> getUsuariosSinAdmin(Pageable pageable) {

		Page<User> usuarios = userRepository.findByRoleNot(Role.ADMIN,pageable);
        return usuarios.map(userMapper::toUserProfileResponseDTO);
	}

	@Transactional(readOnly = true)
	public Page<UserProfileResponseDTO> getUsuariosTransportista(Pageable pageable){
		  Page<User> usuarios = userRepository.findByRole(Role.TRANSPORTISTA,pageable);
		  return usuarios.map(userMapper::toUserProfileResponseDTO);
	}

	@Transactional(readOnly = true)
	public Page<UserProfileResponseDTO> getUsuariosApoderados(Pageable pageable){
		  Page<User> usuarios = userRepository.findByRole(Role.APODERADO,pageable);
		  return usuarios.map(userMapper::toUserProfileResponseDTO);
	}

    @Transactional(readOnly = true)
    public List<UserProfileResponseDTO> getUsuariosTransportistasSinFurgon(){
        List<User> usuariosTransportistasSinFurgon = userRepository.transportistasSinFurgon();
        return userMapper.toUserProfileResponseDTOList(usuariosTransportistasSinFurgon);
    }

	@Transactional(readOnly = true)
	public UserProfileResponseDTO me(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User user = userRepository.findOneByEmail(email)
				.orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
		return userMapper.toUserProfileResponseDTO(user);
	}

    @Transactional
    public void  deleteUser(Long id){
        userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not fund with" + id));
        userRepository.deleteById(id);
    }

}
