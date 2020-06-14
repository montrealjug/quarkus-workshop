package org.montrealjug.api;

import org.bson.Document;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodosResource {

    private TodoService service;

    @Inject
    public TodosResource(TodoService service) {
        this.service = service;
    }

    @PUT
    public Document add(Todo todo) {
        return service.add(todo);
    }

    @GET
    public List<Todo> list() {
        return service.list();
    }
}
