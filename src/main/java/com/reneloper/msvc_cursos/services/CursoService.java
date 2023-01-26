package com.reneloper.msvc_cursos.services;

import com.reneloper.msvc_cursos.entities.Curso;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);

    Curso save(Curso curso);

    Optional<Curso> update(Curso curso, Long id);

    Optional<Map<String, String>> deleteById(Long id);

    Optional<Curso> findByNombre(String nombre);

}
