package com.immortal.configurations.api.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.api.params.PropertyMetadataSearchParams.Params.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyMetadataSearchParams implements Serializable {
    interface Params {
        String GROUP = "group";
        String NAME = "name";
        String ID = "id";
        String CONFIG_METADATA_ID = "configMetadataId";
    }

    @QueryParam(GROUP)
    List<String> groups;

    @QueryParam(NAME)
    List<String> names;

    @QueryParam(ID)
    List<UUID> ids;

    @QueryParam(CONFIG_METADATA_ID)
    List<String> configMetadataIds;

//
//    @QueryParam(USER_ID)
//    Integer userId;
//
//    @QueryParam(REFERENCE_ID)
//    Integer referenceId;
//
//    @QueryParam(USER_FULL_NAME)
//    String userFullName;
//
//    @QueryParam(ACTION)
//    String action;
//
//    @QueryParam(MODULE)
//    String module;
//
//    @QueryParam(OBJECT_ID)
//    String objectId;
//
//    @QueryParam(OBJECT_TYPE)
//    List<String> objectType;
//
//    @QueryParam(OBJECT_NAME)
//    String objectName;
//
//    @QueryParam(OFFSET)
//    @DefaultValue("0")
//    Integer offset;
//
//    @QueryParam(LIMIT)
//    @DefaultValue("25")
//    Integer limit;
//
//    @QueryParam(DATE_BEFORE)
//    ZonedDateTime dateBefore;
//
//    @QueryParam(DATE_AFTER)
//    ZonedDateTime dateAfter;
//
//    @QueryParam(SEARCH_DATA)
//    String searchData;
//
//    /**
//     * Column name to sort. Allowed values: CREATION_DATE, SENDER_FULL_NAME
//     */
//    @QueryParam(SORT_BY)
//    @DefaultValue(DEFAULT_SORTING)
//    String sortBy;
//
//    /**
//     * Column direction to sort. Allowed values: ASC, DESC
//     */
//    @QueryParam(SORT_DIRECTION)
//    @DefaultValue(DbConstants.Sorting.DESC)
//    String sortDirection;

    public PropertyMetadataSearchParams() {
    }

    public List<String> getGroups() {
        return groups;
    }

    public PropertyMetadataSearchParams setGroups(final List<String> groups) {
        this.groups = groups;
        return this;
    }

    public List<String> getNames() {
        return names;
    }

    public PropertyMetadataSearchParams setNames(final List<String> names) {
        this.names = names;
        return this;
    }

    public List<UUID> getIds() {
        return ids;
    }

    public PropertyMetadataSearchParams setIds(final List<UUID> ids) {
        this.ids = ids;
        return this;
    }

    public List<String> getConfigMetadataIds() {
        return configMetadataIds;
    }

    public PropertyMetadataSearchParams setConfigMetadataIds(List<String> configMetadataIds) {
        this.configMetadataIds = configMetadataIds;
        return this;
    }
}
