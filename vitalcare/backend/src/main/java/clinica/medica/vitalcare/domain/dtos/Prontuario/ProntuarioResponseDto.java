package clinica.medica.vitalcare.domain.dtos.Prontuario;

public record ProntuarioResponseDto(
        Long id,
        String paciente,
        String anamnese,
        String medicamentos,
        String atestados,
        String exames
) {
}
