package com.immortal.configurations.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMapperFactory
{
    public static ObjectMapper createObjectMapper()
    {
        return new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                ;
        //                .setDateFormat(getISO8601DateFormat());
    }

    //    private static DateFormat getISO8601DateFormat()
    //    {
    //        final DateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
    //        df.setTimeZone(TimeZone.getTimeZone("UTC"));
    //
    //        return df;
    //    }
}