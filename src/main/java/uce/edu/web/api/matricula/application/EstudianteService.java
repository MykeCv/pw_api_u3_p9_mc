package uce.edu.web.api.matricula.application;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
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
        Estudiante estu = this.mapperToEstudiante(this.ConsultarPorId(id));
        estu.apellido = est.apellido;
        estu.nombre = est.nombre;
        estu.fechaNacimiento = est.fechaNacimiento;
        // Se actualiza automaticamente por dirty checking
    }
    @Transactional
    public void actualizarParcial(Integer id, Estudiante est) {
        Estudiante estu = this.mapperToEstudiante(this.ConsultarPorId(id));
        if (est.nombre != null) {
            estu.nombre = est.nombre;
        }
        if (est.apellido != null) {
            estu.apellido = est.apellido;
        }
        if (est.fechaNacimiento != null) {
            estu.fechaNacimiento = est.fechaNacimiento;
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
