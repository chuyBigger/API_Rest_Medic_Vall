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

    public void actualizarDireccion(DatosDireccion datos) {
        if (datos.calle()!=null){
            this.calle = datos.calle();
        }
        if (datos.numero()!=null){
            this.numero = datos.numero();
        }
        if (datos.complemento()!=null){
            this.complemento = datos.complemento();
        }
        if (datos.barrio()!=null){
            this.barrio = datos.barrio();
        }
        if (datos.ciudad()!=null){
            this.ciudad = datos.ciudad();
        }
        if (datos.estado()!=null){
            this.estado = datos.estado();
        }
        if (datos.codigo_postal()!=null){
            this.codigo_postal = datos.codigo_postal();
        }
    }

}
