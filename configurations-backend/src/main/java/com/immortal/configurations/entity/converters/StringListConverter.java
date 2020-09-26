package com.immortal.configurations.entity.converters;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ", ";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        if (CollectionUtils.isNotEmpty(stringList)) {
            return null;
        }
        return StringUtils.join(stringList, SPLIT_CHAR);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (StringUtils.isBlank(string)) {
            return Collections.emptyList();
        }
        return Arrays.asList(StringUtils.split(string, SPLIT_CHAR));
    }
}
