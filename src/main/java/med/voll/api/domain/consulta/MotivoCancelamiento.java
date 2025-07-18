package med.voll.api.domain.consulta;

public enum MotivoCancelamiento {
    PACIENTE_DESISTIO("Paciente_Desistio"),
    MEDICO_CANCELO("Medico_Cancelo"),
    OTROS("Otros");
    private String motivoCancelamiento;

    MotivoCancelamiento(String motivoCancelamiento) {
        this.motivoCancelamiento = motivoCancelamiento;
    }
}
