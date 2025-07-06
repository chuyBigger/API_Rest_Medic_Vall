package med.voll.api.medico;

public record DatosListaDtoMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {

    public DatosListaDtoMedico(Medico medico) {
        this(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
