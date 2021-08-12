package br.dev.ook.demoapirest.controller;

import br.dev.ook.demoapirest.model.Participante;
import br.dev.ook.demoapirest.repository.ParticipanteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping({"/participantes"})
public class ParticipanteController {

    private ParticipanteRepository repository;

    ParticipanteController(ParticipanteRepository participanteRepository) {
        this.repository = participanteRepository;
    }

    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(regitro -> ResponseEntity.ok().body(regitro))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participante create(@RequestBody Participante participante){
        return repository.save(participante);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody Participante participante) {
        return repository.findById(id)
                .map(regitro -> {
                    regitro.setNome(participante.getNome());
                    regitro.setDataNascimento(participante.getDataNascimento());
                    regitro.setDataCadastro(participante.getDataCadastro());
                    regitro.setPeriodo(participante.getPeriodo());
                    regitro.setTipoCurso(participante.getTipoCurso());
                    Participante updated = repository.save(regitro);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
