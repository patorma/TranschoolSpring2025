package com.patricio.contreras.controller;

import com.patricio.contreras.dto.response.FurgonResponseDTO;
import com.patricio.contreras.dto.response.PagoResponseDTO;
import com.patricio.contreras.dto.response.UserProfileResponseDTO;
import com.patricio.contreras.dto.resquest.*;
import com.patricio.contreras.service.FurgonService;
import com.patricio.contreras.service.PagoService;
import com.patricio.contreras.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PagoService pagoService;
    private final FurgonService furgonService;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pagos/page")
    public ResponseEntity<Page<PagoResponseDTO>> getAllPagos(
            @PageableDefault(size = 5) Pageable pageable){
        Page<PagoResponseDTO> pagos = pagoService.getAllPagos(pageable);
        return ResponseEntity.ok(pagos);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/pago")
    public ResponseEntity<PagoResponseDTO> createPago(
            @RequestBody @Validated PagoRequestDTO pagoRequestDTO
    ){
        PagoResponseDTO pagoResponseDTO = pagoService.createPago(pagoRequestDTO);
        return new ResponseEntity<>(pagoResponseDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateUser(
            @PathVariable Long id,
            @Validated @RequestBody UpdateUserRequestDTO signupDTO
    ){
        UserProfileResponseDTO userProfileResponseDTO = userService.updateUsuario(id,signupDTO);
        return new ResponseEntity<>(userProfileResponseDTO ,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/furgones")
    public ResponseEntity<List<FurgonResponseDTO>> getAllFurgones(){
        List<FurgonResponseDTO> furgones = furgonService.getAllFurgones();
        return ResponseEntity.ok(furgones);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/furgon")
    public ResponseEntity<FurgonResponseDTO> createFurgon(
            @RequestBody @Validated FurgonRequestDTO furgonRequestDTO
    ){
        FurgonResponseDTO furgonResponseDTO = furgonService
                .CreateFurgon(furgonRequestDTO);
        return new ResponseEntity<>(furgonResponseDTO,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/furgon/{id}")
    public ResponseEntity<FurgonResponseDTO> updateFurgon(
            @PathVariable Long id,
            @Validated @RequestBody UpdateFurgonRequestDTO updateFurgonRequestDTO
    ){
       FurgonResponseDTO furgonResponseDTO =  furgonService.updateFurgon(id,updateFurgonRequestDTO);
        return new ResponseEntity<>(furgonResponseDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("furgon/elimina/{id}")
    public ResponseEntity<?> deleteFurgon(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        furgonService.deleteFurgon(id);
        response.put("mensaje", "El furgon fue eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("furgon/reactiva/{id}")
    public ResponseEntity<?> reactivaFurgon(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        furgonService.reactivarFurgon(id);
        response.put("mensaje", "El furgon con id: "+ " "+id+ " "+"fue activado nuevamente");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

}
