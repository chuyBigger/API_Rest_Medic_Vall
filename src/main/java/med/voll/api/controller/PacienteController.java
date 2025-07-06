package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosActualizarDtoMedico;
import med.voll.api.medico.DatosListaDtoMedico;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/paciente")
@RestController
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transient
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos){

        repository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<DatosListaDtoPaciente> listarPaciente(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaDtoPaciente::new);
    }

    @Transactional
    @PutMapping
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarDtoPaciente datos) {

        var paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);

    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.eliminar();
    }

}
