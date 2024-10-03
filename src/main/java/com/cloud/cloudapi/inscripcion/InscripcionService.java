package com.cloud.cloudapi.inscripcion;

import com.cloud.cloudapi.estudiante.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    public List<Inscripcion> getInscripcionesByEstudiante(String codigo) {
        return inscripcionRepository.findByCodigoEstudiante(codigo);
    }

    public Inscripcion createInscripcion(String codigo, Inscripcion inscripcion) {
        if (estudianteRepository.existsById(codigo)) {
            inscripcion.setCodigoEstudiante(codigo);
            return inscripcionRepository.save(inscripcion);
        }
        return null;
    }

    public void deleteInscripcion(Long idInscripcion) {
        inscripcionRepository.deleteById(idInscripcion);
    }
}