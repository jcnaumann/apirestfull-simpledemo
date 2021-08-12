package br.dev.ook.demoapirest.repository;

import br.dev.ook.demoapirest.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
