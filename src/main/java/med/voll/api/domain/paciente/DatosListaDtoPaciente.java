package med.voll.api.domain.paciente;

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
