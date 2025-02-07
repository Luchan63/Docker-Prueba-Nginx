package com.example.tarea.victor.controller;

import com.example.tarea.victor.entity.Alumno;
import com.example.tarea.victor.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoCrontroller {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        try {
            List<Alumno> alumnos = alumnoService.findAll();
            return ResponseEntity.ok(alumnos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving data");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Optional<Alumno> alumno = alumnoService.findById(id);
            if (alumno.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(alumno.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving data");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Alumno alumno) {
        try {
            Alumno savedAlumno = alumnoService.save(alumno);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAlumno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Alumno alumno, @PathVariable Long id) {
        try {
            Optional<Alumno> alumnoOptional = alumnoService.update(alumno, id);
            if (alumnoOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoOptional.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating data");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            alumnoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data");
        }
    }
}
