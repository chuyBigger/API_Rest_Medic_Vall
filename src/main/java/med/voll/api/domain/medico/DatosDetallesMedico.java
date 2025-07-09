package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.Direccion;

public record DatosDetallesMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,
        Direccion direccion

        ) {

    public DatosDetallesMedico(Medico medico){

        this(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getDocumento(),
                medico.getEspecialidad(),
                medico.getDireccion()
        );

    }

}

