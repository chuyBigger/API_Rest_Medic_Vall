package med.voll.api.direccion;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String ciudad;
    private String estado;
    private String codigo_postal;

    public Direccion(DatosDireccion datosDireccion) {

        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.ciudad = datosDireccion.ciudad();
        this.estado = datosDireccion.estado();
        this.codigo_postal = datosDireccion.codigo_postal();

    }
}
