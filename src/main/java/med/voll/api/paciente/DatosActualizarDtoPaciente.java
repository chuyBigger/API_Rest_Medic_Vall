package med.voll.api.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;

public record DatosActualizarDtoPaciente(

        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion

) {



}
