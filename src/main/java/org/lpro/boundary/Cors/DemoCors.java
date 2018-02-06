package org.lpro.boundary.Cors;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("demo")
public class DemoCors {

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("message", "get method ok");
        JsonObject jsonObj = jsonObjBuilder.build();
        String jsonStr = jsonObj.toString();
        return Response.status(Response.Status.OK).entity(jsonStr).build();
    }

    @PUT
    @Path("put")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("message", "put method ok");
        JsonObject jsonObj = jsonObjBuilder.build();
        String jsonStr = jsonObj.toString();
        return Response.status(Response.Status.ACCEPTED).entity(jsonStr).build();
    }

    @POST
    @Path("post")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("message", "post method ok");
        JsonObject jsonObj = jsonObjBuilder.build();
        String jsonStr = jsonObj.toString();
        return Response.status(Response.Status.CREATED).entity(jsonStr).build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMethod() {
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("message", "delete method ok");
        JsonObject jsonObj = jsonObjBuilder.build();
        String jsonStr = jsonObj.toString();
        return Response.status(Response.Status.ACCEPTED).entity(jsonStr).build();
    }
}
