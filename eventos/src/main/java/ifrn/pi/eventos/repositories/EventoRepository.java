package ifrn.pi.eventos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	//List<Evento> findByConvidado(Convidado convidado);
}
