package com.immortal.configurations.api;

import com.immortal.configurations.api.annotations.PATCH;
import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.api.dto.ConfigInstanceDto;
import com.immortal.configurations.api.dto.ConfigInstancePersistDto;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.api.ConfigInstanceResource.Routing.Endpoints.ID_PATH;
import static com.immortal.configurations.api.ConfigInstanceResource.Routing.SERVICE_PREFIX;

/**
 * Service for UI languages (ones with en-US format).
 * <p>
 * language can get the SELF_TRANSLATE value and ru-RU will be translated with ru, en-US - with en, etc.
 * For not authenticated user the GDPR related data is not shown.
 */
@Path(SERVICE_PREFIX)
//@SecurityDomain(SecurityConstants.Domain.IMMORTAL)
@Produces(MediaType.APPLICATION_JSON)
//@ServiceContextRoot(ConfigurationsConstants.Rest.RootContexts.CONFIGURATIONS)
//@RolesAllowed(JaasRights.MANAGE_CONFIGURATION_INSTANCES)
public interface ConfigInstanceResource {
    /**
     * Returns ConfigInstance by id
     */
    @GET
    @Path(ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = "entity not found")})
    ConfigInstanceDto findById(@PathParam(Params.ID) UUID id);

    /**
     * Returns all ConfigInstance
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes(@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS))
    List<ConfigInstanceDto> find();

    /**
     * Creates new ConfigInstance.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    ConfigInstanceDto create(@Valid ConfigInstancePersistDto dto);

    /**
     * Updates ConfigInstance.
     */
    @PUT
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    ConfigInstanceDto update(@PathParam(Params.ID) UUID id, @Valid ConfigInstancePersistDto dto);

    /**
     * Updates ConfigInstance partially.
     */
    @PATCH
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    ConfigInstanceDto partialUpdate(@PathParam(Params.ID) UUID id, @Valid ConfigInstancePersistDto dto);

    /**
     * Deletes ConfigInstance.
     */
    @DELETE
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    void delete(@PathParam(Params.ID) UUID id);

    interface Routing {
        String SERVICE_PREFIX = "/instances";

        interface Endpoints {
            String ID_PATH = "/{" + Params.ID + "}";
        }
    }

    interface Params {
        String ID = "ID";
    }
}
