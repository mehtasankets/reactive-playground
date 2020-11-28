package com.mehtasan.reactive.playground

import io.smallrye.mutiny.Uni
import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/sleep")
class SleepResource {

    private val counter = AtomicInteger(0)

    fun doSomethingAsync(@PathParam seconds: Long): Uni<String> {
        val currentCounter = counter.addAndGet(1)
        println("$currentCounter | Input: $seconds")
        // Mimic an asynchronous computation.
        return Uni.createFrom()
                .item { "$currentCounter | Output: I'm awake after $seconds seconds!\n" }
                .onItem().delayIt().by(Duration.ofSeconds(seconds))
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{seconds}")
    fun doSomethingSync(@PathParam seconds: Long): String {
        val currentCounter = counter.addAndGet(1)
        println("$currentCounter | Input: $seconds")
        Thread.sleep(seconds * 1000)
        return  "$currentCounter | Output: I'm awake after $seconds seconds!\n"
    }
}