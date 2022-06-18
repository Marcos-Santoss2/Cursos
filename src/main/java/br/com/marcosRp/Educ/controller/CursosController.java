package br.com.marcosRp.Educ.controller;

import br.com.marcosRp.Educ.model.Cursos;
import br.com.marcosRp.Educ.repository.CursosRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    @Autowired
    CursosRepository repository;

    @GetMapping("/listarCursos")
    public ResponseEntity <List<Cursos>> Listar (){return ResponseEntity.ok(repository.findAll());}

    @PostMapping("/criarCurso")
    public ResponseEntity<Cursos> criar(@RequestBody Cursos cursos){return ResponseEntity.ok(repository.save(cursos));
    }

}
