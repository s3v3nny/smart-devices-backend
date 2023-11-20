package ru.s3v3nny.smartdevices.servlets;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.services.UserService;

@Path("/add")
public class Add {

    private final UserService service = new UserService();

    @POST
    @SneakyThrows
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(String body) {
        service.addUser(body);

        return Response
                .status(Response.Status.OK)
                .build();
    }
}
