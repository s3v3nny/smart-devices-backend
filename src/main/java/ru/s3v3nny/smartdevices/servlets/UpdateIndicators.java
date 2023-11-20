package ru.s3v3nny.smartdevices.servlets;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.services.IndicatorService;

@Path("/update")
public class UpdateIndicators {

    private final IndicatorService service = new IndicatorService();

    @POST
    @SneakyThrows
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIndicators(String body) {
        System.out.println(body);
        service.updateIndicators(body);

        return Response
                .status(Response.Status.OK)
                .build();
    }
}
