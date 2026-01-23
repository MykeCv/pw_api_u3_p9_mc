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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> obtenerTodas() {
        return materiaService.listarTodas();
    }

    @POST
    @Path("")
    public Response guardar(Materia materia) {
        this.materiaService.crear(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return materiaService.consultarPorId(id);
    }

    @GET
    @Path("/nivel/{nivel}")
    public List<Materia> listarPorNivel(@PathParam("nivel") String nivel) {
        return materiaService.listarPorNivel(nivel);
    }

    @GET
    @Path("/nombre/{nombre}")
    public Materia buscarPorNombre(@PathParam("nombre") String nombre) {
        return materiaService.buscarPorNombre(nombre);
    }
    
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
        return Response.status(210).entity(null).build();
    }


    @PATCH
    @Path("/{id}/{creditos}")
    public Response actualizarCreditos(
            @PathParam("id") Integer id,
            @PathParam("creditos") Integer creditos) {
        this.materiaService.actualizarCreditos(id, creditos);
        return Response.status(211).entity(null).build();
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        materiaService.eliminar(id);
    }
}
