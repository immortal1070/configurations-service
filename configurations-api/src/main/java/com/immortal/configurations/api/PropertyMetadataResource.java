package com.immortal.configurations.api;

import com.immortal.configurations.api.annotations.PATCH;
import com.immortal.configurations.api.constants.ConfigurationsMessages;
import com.immortal.configurations.api.dto.PropertyMetadataDto;
import com.immortal.configurations.api.dto.PropertyMetadataPersistDto;
import com.immortal.configurations.api.params.PropertyMetadataSearchParams;
import com.webcohesion.enunciate.metadata.rs.ResponseCode;
import com.webcohesion.enunciate.metadata.rs.StatusCodes;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.api.PropertyMetadataResource.Routing.Endpoints.ID_PATH;
import static com.immortal.configurations.api.PropertyMetadataResource.Routing.SERVICE_PREFIX;

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
//@RolesAllowed(JaasRights.MANAGE_PROPERTY_METADATA)
public interface PropertyMetadataResource {

    interface Routing {
        String SERVICE_PREFIX = "/props/metadata";

        interface Endpoints {
            String ID_PATH = "/{" + Params.ID + "}";
        }
    }

    interface Params {
        String ID = "ID";
    }

    /**
     * Returns PropertyMetadata by id
     */
    @GET
    @Path(ID_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = "entity not found")})
    PropertyMetadataDto findById(@PathParam(Params.ID) UUID id);

    /**
     * Returns PropertyMetadata by search parameters
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @StatusCodes(@ResponseCode(code = HttpURLConnection.HTTP_OK, condition = ConfigurationsMessages.SUCCESS))
    List<PropertyMetadataDto> find(@BeanParam PropertyMetadataSearchParams searchParams);

    /**
     * Creates new PropertyMetadata.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    PropertyMetadataDto create(@Valid PropertyMetadataPersistDto dto);

    /**
     * Updates PropertyMetadata.
     */
    @PUT
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    PropertyMetadataDto update(@PathParam(Params.ID) UUID id, @Valid PropertyMetadataPersistDto dto);

    /**
     * Updates PropertyMetadata partially.
     */
    @PATCH
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    PropertyMetadataDto partialUpdate(@PathParam(Params.ID) UUID id, @Valid PropertyMetadataPersistDto dto);

    /**
     * Deletes PropertyMetadata.
     */
    @DELETE
    @Path(ID_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @StatusCodes({@ResponseCode(code = HttpURLConnection.HTTP_NO_CONTENT, condition = ConfigurationsMessages.SUCCESS),
        @ResponseCode(code = HttpURLConnection.HTTP_CONFLICT, condition = "input validation failed")})
    void delete(@PathParam(Params.ID) UUID id);
}
