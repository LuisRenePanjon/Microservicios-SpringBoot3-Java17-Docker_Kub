package com.reneloper.msvc_cursos.repositories;

import com.reneloper.msvc_cursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);
}
