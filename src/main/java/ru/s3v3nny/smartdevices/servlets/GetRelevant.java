package ru.s3v3nny.smartdevices.servlets;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.models.RelevantIndicator;
import ru.s3v3nny.smartdevices.services.IndicatorService;
import ru.s3v3nny.smartdevices.services.UserService;

import java.util.List;

@Path("/getrelevant")
public class GetRelevant {

    private final IndicatorService service = new IndicatorService();

    @GET
    @SneakyThrows
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRelevantIndicators() {
        List<RelevantIndicator> relevantIndicatorsList = service.getRelevantIndicators();

        return Response
                .status(Response.Status.OK)
                .entity(relevantIndicatorsList)
                .build();
    }
}
