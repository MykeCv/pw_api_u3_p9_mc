package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodas() {
        return materiaRepository.listAll();
    }

    @Transactional
    public void crear(Materia materia) {
        materiaRepository.persist(materia);
    }

    public Materia consultarPorId(Integer id) {
        return materiaRepository.findById(id.longValue());
    }

    public Materia buscarPorNombre(String nombre) {
        return materiaRepository.find("nombre", nombre).firstResult();
    }

    public List<Materia> listarPorNivel(String nivel) {
        return materiaRepository.find("nivel", nivel).list();
    }

     @Transactional
    public void actualizar(Integer id, Materia materia) {
        Materia existente = materiaRepository.findById(id.longValue());

        if (existente != null) {
            existente.setNombre(materia.getNombre());
            existente.setNivel(materia.getNivel());
            existente.setCreditos(materia.getCreditos());
        }
    }

    @Transactional
    public void actualizarCreditos(Integer id, Integer creditos) {
        Materia materia = materiaRepository.findById(id.longValue());
        if (materia != null) {
            materia.setCreditos(creditos);
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        materiaRepository.deleteById(id.longValue());
    }

}
