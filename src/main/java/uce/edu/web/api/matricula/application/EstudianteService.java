package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list=new ArrayList<>();
        for(Estudiante estu:this.estudianteRepository.listAll()){
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    public EstudianteRepresentation ConsultarPorId(Integer id) {
        return this.mapperToER(this.estudianteRepository.findById(id.longValue()));

    }

    @Transactional
    public void crear(EstudianteRepresentation estu) {
        this.estudianteRepository.persist(this.mapperToEstudiante(estu));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation est) {
        Estudiante entity = estudianteRepository.findById(id.longValue());
           if (entity != null) {
            entity.nombre = est.nombre;
            entity.apellido = est.apellido;
            entity.fechaNacimiento = est.fechaNacimiento;
            entity.genero = est.genero;
            entity.provincia = est.provincia;
        }
    }
    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation est) {
        Estudiante entity = estudianteRepository.findById(id.longValue());
        if (entity != null) {
        if (entity.nombre != null) entity.nombre = est.nombre;
        if (entity.apellido != null) entity.apellido = est.apellido;
        if (entity.fechaNacimiento != null) entity.fechaNacimiento = est.fechaNacimiento;
        if (entity.genero != null) entity.genero = est.genero;
        if (entity.provincia != null) entity.provincia = est.provincia;
        }
    }
    // Se actualiza automaticamente por dirty checking
    @Transactional
    public void eliminar(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero){
        //return this.estudianteRepository.find("provincia", provincia).list();
         List<EstudianteRepresentation> list=new ArrayList<>();
        for(Estudiante estu:this.estudianteRepository.find("provincia =?1 and genero = ?2", provincia, genero).list()){
            list.add(this.mapperToER(estu));
        }
        return list;
    }

    private EstudianteRepresentation mapperToER (Estudiante est){
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.genero = est.genero;
        estuR.provincia = est.provincia;
        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation est){
        Estudiante estuR = new Estudiante();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.genero = est.genero;
        estuR.provincia = est.provincia;
        return estuR;
    }
    
}
