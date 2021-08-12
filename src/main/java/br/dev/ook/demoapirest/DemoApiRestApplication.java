package br.dev.ook.demoapirest;

import br.dev.ook.demoapirest.model.Participante;
import br.dev.ook.demoapirest.repository.ParticipanteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.LongStream;

@SpringBootApplication
public class DemoApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiRestApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ParticipanteRepository repository) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNasc = LocalDate.parse("1980-01-01");
		LocalDate dataCad = LocalDate.now();
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						Participante participante = new Participante();
						participante.setNome("Participante " + i);
						participante.setDataNascimento(dataNasc);
						participante.setDataCadastro(dataCad);
						participante.setPeriodo(0);
						participante.setTipoCurso(0);
						return participante;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}
