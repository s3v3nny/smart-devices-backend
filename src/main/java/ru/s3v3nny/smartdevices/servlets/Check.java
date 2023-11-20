package ru.s3v3nny.smartdevices.servlets;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.services.UserService;

@Path("/check/{uuid}")
public class Check {

    private final UserService service = new UserService();

    @GET
    @SneakyThrows
    public Response checkUuid(@PathParam("uuid") String key) {

        if (!service.checkUUID(key)) {
            return Response
                    .status(Response.Status.OK)
                    .build();
        }

        return Response
                .status(Response.Status.CONFLICT)
                .build();
    }

}
