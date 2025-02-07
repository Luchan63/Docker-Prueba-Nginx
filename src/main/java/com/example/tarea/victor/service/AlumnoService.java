package com.example.tarea.victor.service;

import com.example.tarea.victor.entity.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    List<Alumno> findAll();
    Optional<Alumno> findById(Long id);
    Alumno save(Alumno alumno);
   Optional <Alumno> update(Alumno alumno, Long id);
    void delete(Long id);


}
