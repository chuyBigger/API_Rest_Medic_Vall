package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;

import java.time.DayOfWeek;

public class FueraHorarioTrabajo {

    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesApertura = fechaConsulta.getHour() < 7;
        var horarioDespuesCierre = fechaConsulta.getHour() > 18;
        if (domingo||horarioAntesApertura||horarioDespuesCierre){
            throw new ValidacionException("Horario seleccionado fuera del Horario de Servicio");
        }

    }

}
