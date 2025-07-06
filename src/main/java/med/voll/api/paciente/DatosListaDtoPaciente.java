package med.voll.api.paciente;

import med.voll.api.medico.Medico;
import med.voll.api.paciente.Paciente;

public record DatosListaDtoPaciente(

        Long id,
        String nombre,
        String email,
        String documento

) {

    public DatosListaDtoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
