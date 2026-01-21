package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todas")
    public List<Materia> obtenerTodas() {
        return materiaService.listarTodas();
    }

    @POST
    @Path("/crear")
    public void crear(Materia materia) {
        materiaService.crear(materia);
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return materiaService.consultarPorId(id);
    }

    @GET
    @Path("/porNivel/{nivel}")
    public List<Materia> listarPorNivel(@PathParam("nivel") String nivel) {
        return materiaService.listarPorNivel(nivel);
    }

    @GET
    @Path("/porNombre/{nombre}")
    public Materia buscarPorNombre(@PathParam("nombre") String nombre) {
        return materiaService.buscarPorNombre(nombre);
    }
    
    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Materia materia) {
        materiaService.actualizar(id, materia);
    }


    @PATCH
    @Path("/actualizar/{id}/{creditos}")
    public void actualizarCreditos(
            @PathParam("id") Integer id,
            @PathParam("creditos") Integer creditos) {
        materiaService.actualizarCreditos(id, creditos);
    }

    @DELETE
    @Path("/borrar/{id}")
    public void borrar(@PathParam("id") Integer id) {
        materiaService.eliminar(id);
    }
}
