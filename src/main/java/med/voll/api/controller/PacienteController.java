package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
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

}
