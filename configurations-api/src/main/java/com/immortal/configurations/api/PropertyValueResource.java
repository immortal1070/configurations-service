package com.immortal.configurations.api;

import static com.immortal.configurations.api.PropertyValueResource.Routing.Endpoints.ID_PATH;
import static com.immortal.configurations.api.PropertyValueResource.Routing.SERVICE_PREFIX;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.immortal.configurations.api.annotations.PATCH;
import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.api.dto.PropertyValueDto;
import com.immortal.configurations.api.dto.PropertyValuePersistDto;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;

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
//@RolesAllowed(JaasRights.MANAGE_CONFIGURATION_VALUES)
public interface PropertyValueResource
{
    interface Routing
    {
        String SERVICE_PREFIX = "/props/values";

        interface Endpoints
        {
            String ID_PATH = "/{" + Params.ID + "}";
        }
    }

    interface Params
    {
        String ID = "ID";
    }

    /**
     * Returns PropertyValue by id
     */
    @GET
    @Path(ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes({ @ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS),
            @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = "entity not found") })
    PropertyValueDto findById(@PathParam(Params.ID) UUID id);

    /**
     * Returns all PropertyValue
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes(@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS))
    List<PropertyValueDto> find();

    /**
     * Creates new PropertyValue.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({ @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
            @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed") })
    PropertyValueDto create(@Valid PropertyValuePersistDto dto);

    /**
     * Updates PropertyValue.
     */
    @PUT
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({ @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
            @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed") })
    PropertyValueDto update(@PathParam(Params.ID) UUID id, @Valid PropertyValuePersistDto dto);

    /**
     * Updates PropertyValue partially.
     */
    @PATCH
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({ @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
            @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed") })
    PropertyValueDto partialUpdate(@PathParam(Params.ID) UUID id, @Valid PropertyValuePersistDto dto);

    /**
     * Deletes PropertyValue.
     */
    @DELETE
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({ @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
            @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed") })
    void delete(@PathParam(Params.ID) UUID id);
}
