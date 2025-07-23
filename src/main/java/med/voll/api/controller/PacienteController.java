package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/paciente")
@RestController
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transient
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder){
        var paciente= new Paciente(datos);
        repository.save(paciente);

        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaDtoPaciente>> listarPaciente(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaDtoPaciente::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarDtoPaciente datos) {

        var paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.eliminar();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detallarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        
        return ResponseEntity.ok(new DatosDetallesPaciente(paciente));
    }

}
