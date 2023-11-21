package clinica.medica.vitalcare.domain.dtos.Paciente;

public record PacienteResponseDto(
        String nome,
        String email,
        String telefone,
        double altura,
        String tipoSanguineo,
        double peso
) {
}
