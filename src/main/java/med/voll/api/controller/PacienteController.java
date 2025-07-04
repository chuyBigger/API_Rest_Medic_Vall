package med.voll.api.controller;

import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.paciente.DatosRegistroPaciente;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/paciente")
@RestController
public class PacienteController {

    @PostMapping
    public void registrar(@RequestBody DatosRegistroPaciente datos){

        System.out.println(datos);

    }

}
