package uce.edu.web.api.matricula.application;

import jakarta.inject.Inject;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos(){
        return this.estudianteRepository.listAll();
    }
}
