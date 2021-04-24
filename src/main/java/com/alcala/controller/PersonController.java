package com.alcala.controller;

import com.alcala.model.Person;
import org.eclipse.microprofile.faulttolerance.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    List<Person> personList = new ArrayList<>();
    Logger LOGGER = Logger.getLogger("Demologger");

    @GET
    @Fallback(fallbackMethod = "getPersonFallbackList")
    public List<Person> getPersonList() {
        LOGGER.info("Ejecutando person list");
        doFail();
        return this.personList;
    }

    public List<Person> getPersonFallbackList() {
        var person = new Person(-1L, "Alcala", "hector.alcala@alumnos.udg.mx");
        return List.of(person);
    }

    public void doFail() {
        var random = new Random();
        if (random.nextBoolean()) {
            LOGGER.warning("Se produce una falla");
            throw new RuntimeException("Haciendo que la implementacion falle");
        }
    }
}