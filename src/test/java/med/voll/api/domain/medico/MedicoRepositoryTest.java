package med.voll.api.domain.medico;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.MotivoCancelamiento;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deberia devolde null cuendo el medio buscado no existe pero esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEsenario1() {
        // Given o Arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(10, 0);
        var medico = registrarMedico("medico one", "emailonemedic@gmail.com", "1029383475", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("paciente one", "paciente@gmail.com", "127368723682");
        registrarConsulta(medico, paciente, lunesSiguienteALas10);
        // when o Act
        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        // THen o Assert
        assertThat(medicoLibre).isNull();

    }

    @Test
    @DisplayName("Deberia devolver null cuando el medio buscado esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEsenario2() {
        // Given o Arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(10, 0);
        var medico = registrarMedico("medico one", "emailonemedic@gmail.com", "1029383475", Especialidad.CARDIOLOGIA);
        // when o Act
        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        // THen o Assert
        assertThat(medicoLibre).isEqualTo(medico);

    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        entityManager.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosRegistroMedico(nombre, email, documento, especialidad));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosRegistroPaciente(nombre, email, documento));
        entityManager.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosRegistroMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "44423312",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosRegistroPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "12345678",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle x",
                "distrito y",
                "complemnto 1",
                "barrio nose",
                "ciudad z",
                "tal Vez ",
                "00000"
        );
    }
}