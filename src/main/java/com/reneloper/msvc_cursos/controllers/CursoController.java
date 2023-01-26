package com.reneloper.msvc_cursos.controllers;


import com.reneloper.msvc_cursos.entities.Curso;
import com.reneloper.msvc_cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        var cursos = this.cursoService.findAll();
        if (cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        var curso = this.cursoService.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Curso> findByNombre(@PathVariable String nombre){
        var curso = this.cursoService.findByNombre(nombre);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso){
        var cursoSaved = this.cursoService.save(curso);
        return ResponseEntity.status(201).body(cursoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@RequestBody Curso curso, @PathVariable Long id){
        var cursoSaved = this.cursoService.update(curso, id);
        if (cursoSaved.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoSaved.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        var message = this.cursoService.deleteById(id);
        if (message.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message.get());
    }

}
