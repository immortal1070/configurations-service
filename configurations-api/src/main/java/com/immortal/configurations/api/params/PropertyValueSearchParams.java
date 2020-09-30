package com.immortal.configurations.api.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static com.immortal.configurations.api.params.PropertyValueSearchParams.Params.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyValueSearchParams implements Serializable {
    interface Params {
        String VALUE = "value";
        String NAME = "name";
        String ID = "id";
        String CONFIG_INSTANCE_ID = "configInstanceId";
    }

    @QueryParam(VALUE)
    List<String> values;

    @QueryParam(NAME)
    List<String> names;

    @QueryParam(ID)
    List<UUID> ids;

    @QueryParam(CONFIG_INSTANCE_ID)
    List<UUID> configInstanceIds;

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

    public PropertyValueSearchParams() {
    }

    public List<String> getValues() {
        return values;
    }

    public PropertyValueSearchParams setValues(final List<String> values) {
        this.values = values;
        return this;
    }

    public List<String> getNames() {
        return names;
    }

    public PropertyValueSearchParams setNames(final List<String> names) {
        this.names = names;
        return this;
    }

    public List<UUID> getIds() {
        return ids;
    }

    public PropertyValueSearchParams setIds(final List<UUID> ids) {
        this.ids = ids;
        return this;
    }

    public List<UUID> getConfigInstanceIds() {
        return configInstanceIds;
    }

    public PropertyValueSearchParams setConfigInstanceIds(final List<UUID> configInstanceIds) {
        this.configInstanceIds = configInstanceIds;
        return this;
    }
}
