package com.patricio.contreras.controller;

import com.patricio.contreras.domain.enums.Estado;
import com.patricio.contreras.dto.response.*;
import com.patricio.contreras.dto.resquest.*;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.service.*;
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
    private final EstudianteService estudianteService;
    private final MensualidadService mensualidadService;
    private final RecorridoService recorridoService;
    private final AsignacionDeEstudianteService asignacionDeEstudianteService;
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

    // Se lista los transportistas sin furgon
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios-transportistas/sin-furgon")
    public ResponseEntity<List<UserProfileResponseDTO>> listarTransportistasSinFurgon(){
        List<UserProfileResponseDTO> usuariosTransportistasSinFurgon = userService.getUsuariosTransportistasSinFurgon();
        return ResponseEntity.ok(usuariosTransportistasSinFurgon);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios/transportistas")
    public ResponseEntity<List<UserProfileResponseDTO>> listarUsuaiosTransportistas(){
        List<UserProfileResponseDTO> usuariosTransportistas = userService.getUsuariosTransportista();
        return ResponseEntity.ok(usuariosTransportistas);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuarios/apoderados")
    public ResponseEntity<List<UserProfileResponseDTO>> listarUsuariosApoderados(){
        List<UserProfileResponseDTO> usuariosApoderados = userService.getUsuariosApoderados();
        return ResponseEntity.ok(usuariosApoderados);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pagos/page")
    public ResponseEntity<Page<PagoResponseDTO>> getAllPagos(
            @PageableDefault(size = 5) Pageable pageable){
        Page<PagoResponseDTO> pagos = pagoService.getAllPagos(pageable);
        return ResponseEntity.ok(pagos);
    }

    //   ver todas las mensualidades de los apoderados que estan activas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mensualidades/page")
    public ResponseEntity<Page<MensualidadResponseDTO>> getAllMensualidades(
            @PageableDefault(size = 5) Pageable pageable)
    {
        Page<MensualidadResponseDTO> mensualidades = mensualidadService.getAllMensualidades(pageable);
        return ResponseEntity.ok(mensualidades);

    }

    //Buscar el estado de una mensualidad por parte del admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("mensualidades/page/state")
    public ResponseEntity<Page<MensualidadResponseDTO>> findByState(
            @RequestParam Estado state_mensualidad,  @PageableDefault(sort = "estado",size = 5) Pageable pageable
    ){
      Page<MensualidadResponseDTO> mensualidades = mensualidadService.getMensualidadByEstado(state_mensualidad,pageable);
      return ResponseEntity.ok(mensualidades);
    }


//crear recorrido
   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping("/recorrido")
    public ResponseEntity<RecorridoResponseDTO> createRecorrido(
            @RequestBody @Validated RecorridoRequestDTO recorridoRequestDTO
    ){
        RecorridoResponseDTO recorridoResponseDTO =recorridoService.createRecorrido(recorridoRequestDTO);
       return new ResponseEntity<>(recorridoResponseDTO,HttpStatus.CREATED);
    }

    //actualizar un recorrido
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/recorrido/{id}")
    public ResponseEntity<RecorridoResponseDTO> updateRecorrido(
            @PathVariable Long id,
            @Validated @RequestBody UpdateRecorridoRequestDTO updateRecorridoRequestDTO
    ){
        RecorridoResponseDTO  recorridoResponseDTO = recorridoService.updateReciorrido(id,updateRecorridoRequestDTO);
        return new ResponseEntity<>(recorridoResponseDTO,HttpStatus.OK);
    }

    //buscar un recorrido por id

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/recorrido/{id}")
    public ResponseEntity<RecorridoResponseDTO> getRecorridoById(@PathVariable Long id){
        RecorridoResponseDTO recorrido = recorridoService.getRecorridoById(id);
        return ResponseEntity.ok(recorrido);
    }

    // se registra por parte del admin la mensualidad asociada al apoderado
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/mensualidad")
    public ResponseEntity<MensualidadResponseDTO> createMensualidad(
            @RequestBody @Validated MensualidadRequestDTO mensualidadRequestDTO
    ){
        MensualidadResponseDTO mensualidadResponseDTO = mensualidadService.createMensualidad(mensualidadRequestDTO);
        return new ResponseEntity<>(mensualidadResponseDTO, HttpStatus.CREATED);
    }

// se actualiza por Id una mensualidad por parte del admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/mensualidad/{id}")
    public ResponseEntity<MensualidadResponseDTO> updateMensualidad(
            @PathVariable Long id,
            @Validated @RequestBody UpdateMensualidadRequestDTO updateMensualidadRequestDTO
    ){
        MensualidadResponseDTO mensualidadResponseDTO = mensualidadService.updateMensualidad(updateMensualidadRequestDTO,id);
        return new ResponseEntity<>(mensualidadResponseDTO,HttpStatus.CREATED);
    }

// el admin busca por id de usuario apoderado las mensualidades que esté tiene asociadas
   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mensualidades/page/id-user")
    public ResponseEntity<Page<MensualidadResponseDTO>> findByIdUserMensualidad(
            @RequestParam Long id,@PageableDefault(size = 5) Pageable pageable
    ){
        Page<MensualidadResponseDTO> mensualidades = mensualidadService.getMensualidadByUserId(id,pageable);
        return ResponseEntity.ok(mensualidades);
    }

    // se desactiva la mensualidad es un soft delete
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/mensualidad/eliminar/{id}")
    public ResponseEntity<?> deleteMensualidad(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        mensualidadService.deleteMensualidad(id);
        response.put("mensaje","La mensualidad fue eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    // se reactiva la mensualidad
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/mensualidad/reactiva/{id}")
    public ResponseEntity<?> reactivarMensualidad(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        mensualidadService.reactivarMensualidad(id);
        response.put("mensaje", "La mensualidad con id: "+ " "+id+ " "+"fue activada nuevamente");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }





    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/pago")
    public ResponseEntity<PagoResponseDTO> createPago(
            @RequestBody @Validated PagoRequestDTO pagoRequestDTO
    ){
        PagoResponseDTO pagoResponseDTO = pagoService.createPago(pagoRequestDTO);
        return new ResponseEntity<>(pagoResponseDTO, HttpStatus.CREATED);
    }

    //se actualiza un pago
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/pago/{id}")
    public ResponseEntity<PagoResponseDTO> updatePago(
            @PathVariable Long id,
            @Validated @RequestBody UpdatePagoRequestDTO updatePagoRequestDTO
    ){
        PagoResponseDTO pagoResponseDTO = pagoService.updatePago(updatePagoRequestDTO,id);
        return new ResponseEntity<>(pagoResponseDTO,HttpStatus.OK);
    }

    // se desactiva el pago con un soft delete

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/pago/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> deletePago(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try {
            // Intenta eliminar el pago
            pagoService.deletePago(id);
            response.put("mensaje","El pago fue eliminado con éxito!");
            return new ResponseEntity<>(response, HttpStatus.OK); // Retorna 200 OK si todo sale bien
        } catch (ResourceNotFoundException e) {
            // Si el pago no se encuentra, captura la excepción y retorna un 404 NOT FOUND
            response.put("mensaje", e.getMessage()); // El mensaje de la excepción: "Pago no encontrado con ID: ..."
            response.put("error", "Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Retorna 404
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            response.put("mensaje", "Ocurrió un error inesperado al eliminar el pago.");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500
        }
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
    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserProfileResponseDTO> findByUser(@PathVariable Long id){
        UserProfileResponseDTO userProfileResponseDTO = userService.findByIdUser(id);
        return new ResponseEntity<>(userProfileResponseDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/mensualidad/{id}")
    public ResponseEntity<MensualidadResponseDTO> findMensualidadById(@PathVariable Long id){
        MensualidadResponseDTO mensualidadResponseDTO = mensualidadService.getMensualidadById(id);
        return new ResponseEntity<>(mensualidadResponseDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pago/{id}")
    public ResponseEntity<PagoResponseDTO> findPagoById(@PathVariable Long id){
        PagoResponseDTO pagoResponseDTO = pagoService.getPagoById(id);
        return new ResponseEntity<>(pagoResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/furgones")
    public ResponseEntity<List<FurgonResponseDTO>> getAllFurgones(){
        List<FurgonResponseDTO> furgones = furgonService.getAllFurgones();
        return ResponseEntity.ok(furgones);
    }

    // Listado de furgones que no tienen asignado estudiantes
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/furgones/sin-asignaciones")
    public ResponseEntity<List<FurgonResponseDTO>> getAllFurgonesSinAsignaciones(){
        List<FurgonResponseDTO> furgonesSinAsignacion = furgonService.getFurgonesSinAsignacion();
        return ResponseEntity.ok(furgonesSinAsignacion);
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/estudiantes")
    public ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantes(){
        List<EstudianteResponseDTO> estudiantes = estudianteService.getAllEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/asignacion")
    public ResponseEntity<AsignacionDeEstudianteResponseDTO> createAsignacion(
            @RequestBody @Validated AsignacionDeEstudianteRequestDTO
                    asignacionDeEstudianteRequestDTO ){
        AsignacionDeEstudianteResponseDTO asignacionDeEstudianteResponseDTO=
                asignacionDeEstudianteService.createAsignaciones(asignacionDeEstudianteRequestDTO);
        return new ResponseEntity<>(asignacionDeEstudianteResponseDTO,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/asignaciones/page")
    public ResponseEntity<Page<AsignacionDeEstudianteResponseDTO>>getAllAsignaciones(
            @PageableDefault(size = 5)Pageable pageable){
        Page<AsignacionDeEstudianteResponseDTO>asignaciones =
                asignacionDeEstudianteService.getAllAsignaciones(pageable);
        return ResponseEntity.ok(asignaciones);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/asignacion/eliminar/{id}")
    public ResponseEntity<?> deleteAsignacion(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        asignacionDeEstudianteService.deleteAsignacion(id);
        response.put("mensaje", "La asignación fue eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
    }

    //es para ver el furgon al cual esta asignado
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/asigEstudiante/idEstudiante")
    public ResponseEntity<AsignacionDeEstudianteResponseDTO>findByIdEstudiante(
            @RequestParam Long id){

        AsignacionDeEstudianteResponseDTO asignaciones =
                asignacionDeEstudianteService.getAsignacionByEstudianteId(id);
        return ResponseEntity.ok(asignaciones);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/asigFurgon/page/idFurgon")
    public ResponseEntity<Page<AsignacionDeEstudianteResponseDTO>>findByFurgon(
            @RequestParam Long id,@PageableDefault(size = 5) Pageable pageable	){
        Page<AsignacionDeEstudianteResponseDTO> asignaciones =
                asignacionDeEstudianteService.getAsignacionByFurgonId(id, pageable);
        return ResponseEntity.ok(asignaciones);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/asignacion/{id}")
    public ResponseEntity<AsignacionDeEstudianteResponseDTO>getAsignacionById(
            @PathVariable Long id) {
        AsignacionDeEstudianteResponseDTO asignacion =
                asignacionDeEstudianteService.getAsignacionById(id);
        return ResponseEntity.ok(asignacion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/furgon/asignaciones/count")
    public ResponseEntity<Integer> contarEstudiantesAsignados(@RequestParam Long furgonId){
        Integer total = asignacionDeEstudianteService.contarAsignacionesPorFurgon(furgonId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/recorridos/page")
    public ResponseEntity<Page<RecorridoResponseDTO>> getAllRecorridos(
            @PageableDefault(size = 5)Pageable pageable){
        Page<RecorridoResponseDTO> recorridos = recorridoService.getAllRecorridos(pageable);
        return ResponseEntity.ok(recorridos);
    }

}
