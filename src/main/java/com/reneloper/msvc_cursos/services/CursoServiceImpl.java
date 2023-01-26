package com.reneloper.msvc_cursos.services;

import com.reneloper.msvc_cursos.entities.Curso;
import com.reneloper.msvc_cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return this.cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return this.cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return this.cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Optional<Curso> update(Curso curso, Long id) {
        var cursoOptional = this.cursoRepository.findById(id);
        if (cursoOptional.isEmpty()) {
            return Optional.empty();
        }
        var cursoUpdate = cursoOptional.get();
        if (curso.getNombre() != null) {
            cursoUpdate.setNombre(curso.getNombre());
        }
        if (curso.getDescripcion() != null) {
            cursoUpdate.setDescripcion(curso.getDescripcion());
        }
        return Optional.of(this.cursoRepository.save(cursoUpdate));
    }

    @Override
    @Transactional
    public Optional<Map<String, String>> deleteById(Long id) {
        var cursoOptional = this.cursoRepository.findById(id);
        if (cursoOptional.isEmpty()) {
            return Optional.empty();
        }
        this.cursoRepository.deleteById(id);
        return Optional.of(Map.of("message", "Curso eliminado con éxito"));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findByNombre(String nombre) {
        return this.cursoRepository.findByNombre(nombre);
    }
}
