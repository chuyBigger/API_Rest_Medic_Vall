package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;

public class ValidadMedicoCoincidenciaConsultasPorFecha {

    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
    }

}
