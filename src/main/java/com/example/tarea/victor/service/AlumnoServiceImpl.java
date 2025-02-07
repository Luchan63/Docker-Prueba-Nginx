package com.example.tarea.victor.service;

import com.example.tarea.victor.entity.Alumno;
import com.example.tarea.victor.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public Optional<Alumno> update(Alumno alumno, Long id) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            Alumno alumnoEntity = alumnoOptional.get();
            alumnoEntity.setNombre(alumno.getNombre());
            alumnoEntity.setApellido(alumno.getApellido());
            alumnoEntity.setDireccion(alumno.getDireccion());
            alumnoEntity.setTelefono(alumno.getTelefono());
            return Optional.of(alumnoRepository.save(alumnoEntity));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }
}
