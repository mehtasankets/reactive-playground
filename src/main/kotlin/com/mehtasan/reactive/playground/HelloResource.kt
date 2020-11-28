package com.mehtasan.reactive.playground

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
open class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    open fun hello(): String {
        return "hello"
    }
}