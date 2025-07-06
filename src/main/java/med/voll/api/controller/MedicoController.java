package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/medicos")
@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transient
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {

        repository.save(new Medico(datos));

    }

    @GetMapping
    public Page<DatosListaDtoMedico> listarMedico(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListaDtoMedico::new);
    }

    @Transactional
    @PutMapping
    public void actualizarMedico(@RequestBody @Valid DatosActualizarDtoMedico datos) {

        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.eliminar();
    }

}
