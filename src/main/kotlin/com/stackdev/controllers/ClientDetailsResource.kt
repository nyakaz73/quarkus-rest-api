package com.stackdev.controllers

import com.stackdev.models.ClientDetails
import com.stackdev.service.ClientDetailsService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api")
class ClientDetailsResource {

    @Inject
    lateinit var clientDetailsService: ClientDetailsService

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll():Response{return  Response.ok(clientDetailsService.findAll()).build()}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findById(@PathParam("id") id:Long):Response{
        val client = clientDetailsService.findById(id)
        if (client!=null){
            return Response.ok(client).build()
        }
        return Response.ok("Client does not Exist").status(Response.Status.NOT_FOUND).build()
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findByName(@PathParam("name") name:String):Response{
        val client = clientDetailsService.findByName(name)
        if (client!=null){
            return Response.ok(client).build()
        }
        return Response.ok("Client does not Exist").status(Response.Status.NOT_FOUND).build()
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun saveClient(client:ClientDetails):Response{
        clientDetailsService.saveClient(client)
        return Response.ok(client).status(Response.Status.CREATED).build()
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateClient(client: ClientDetails):Response{
        clientDetailsService.updateClient(client)
        val updatedClient = clientDetailsService.findById(client.id!!)
        if(updatedClient != null){
            return Response.ok(updatedClient).build()
        }
        return  Response.ok("Client not  found").build()
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteClient(@PathParam("id") id:Long):Response{
        val client = clientDetailsService.findById(id)
        if (client!=null){
            clientDetailsService.deleteClient(id)
            return Response.ok("Client deleted").build()
        }
        return Response.ok("Client does not Exist").status(Response.Status.NOT_FOUND).build()
    }

}