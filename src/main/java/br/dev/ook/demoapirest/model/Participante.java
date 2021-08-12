package br.dev.ook.demoapirest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Participante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento, dataCadastro;

    private int periodo, tipoCurso;

    public void setDataNascimento(LocalDate dataNascimento) {
        LocalDate now = LocalDate.now();
        Period dif = Period.between(dataNascimento, now);
        System.out.println("Idade = " + dif.getYears());
        if (dif.getYears() < 17) throw new IllegalArgumentException("Idade deve ser superior a 18 anos");
        this.dataNascimento = dataNascimento;
    }
}
