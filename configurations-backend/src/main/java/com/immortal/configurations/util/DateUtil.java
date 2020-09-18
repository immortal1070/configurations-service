package com.immortal.configurations.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final String DATE_PATTERN = "yyyy.MM.dd HH:mm:ss";
    private static final String NOW = "now";

    public static ZonedDateTime fromZonedString(String value)
    {
        if (value == null || value.isEmpty())
        {
            return null;
        }

        if(NOW.equalsIgnoreCase(value))
        {
            return ZonedDateTime.now();
        }
        return ZonedDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    //output time in UTC
    public static String toUtcString(ZonedDateTime value)
    {
        if (value == null)
        {
            return "";
        }
        final LocalDateTime utcDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneOffset.UTC);
        return DateTimeFormatter.ofPattern(ISO_DATE_FORMAT).format(utcDateTime);
    }

    public static ZonedDateTime convertToDate(final String dateString)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        return ZonedDateTime.parse(dateString, formatter.withZone(ZoneOffset.UTC));
    }

    public static String convertToString(final ZonedDateTime zonedDateTime)
    {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(zonedDateTime);
    }

    public static ZonedDateTime getNowDateByTimeZoneId(final String timeZoneId)
    {
        return ZonedDateTime.now(ZoneId.of(timeZoneId));
    }

    public static ZonedDateTime getZonedNowInUtc()
    {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static String getNowUtcDateAsString()
    {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(ZonedDateTime.now(ZoneOffset.UTC));
    }

    public static LocalDateTime toUtcLocalDateTime(final ZonedDateTime zonedDateTime)
    {
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneOffset.UTC);
    }

    public static ZonedDateTime convertToZoneDateTimeInUtc(final Calendar calendar)
    {
        if (calendar == null)
        {
            return null;
        }
        return ZonedDateTime
                .of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND), 0, ZoneOffset.UTC);
    }

    public static ZonedDateTime convertToZoneDateTimeInUtc(final Date date)
    {
        if (date == null)
        {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return convertToZoneDateTimeInUtc(calendar);
    }

    public static Date convertToDateFromZoneDateTime(final ZonedDateTime zonedDateTime)
    {
        if (zonedDateTime == null)
        {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String dateString = convertToString(zonedDateTime);

        return java.sql.Timestamp.valueOf(LocalDateTime.parse(dateString, formatter));
    }

    public static DateIntervalDto getIntervalForToday(final String timeZoneId)
    {
        // Now date (2018-04-02 11:52:23) in user timeZoneId (GMT + 7)
        ZonedDateTime now = DateUtil.getNowDateByTimeZoneId(timeZoneId);

        // Date (2018-04-02 00:00:00) in user timeZoneId (GMT + 7)
        ZonedDateTime dateFromStart = now.with(LocalTime.of(0, 0, 0, 0));

        // Date (2018-04-01 17:00:00) in UTC - startDateForSearch
        ZonedDateTime startDate = dateFromStart.withZoneSameInstant(ZoneOffset.UTC);

        // Now date (2018-04-02 04:52:23) in UTC - endDateForSearch
        ZonedDateTime endDate = now.withZoneSameInstant(ZoneOffset.UTC);

        return new DateIntervalDto(convertToDateFromZoneDateTime(startDate), convertToDateFromZoneDateTime(endDate));
    }

    public static Date getNowInUtc()
    {
        return toDate(LocalDateTime.now(ZoneOffset.UTC));
    }

    private static Date toDate(final LocalDateTime dateTime)
    {
        return java.sql.Timestamp.valueOf(dateTime);
    }

    public static class DateIntervalDto
    {
        private Date startDate;
        private Date endDate;

        public DateIntervalDto()
        {

        }

        public DateIntervalDto(final Date startDate, final Date endDate)
        {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate()
        {
            return startDate;
        }

        public void setStartDate(final Date startDate)
        {
            this.startDate = startDate;
        }

        public Date getEndDate()
        {
            return endDate;
        }

        public void setEndDate(final Date endDate)
        {
            this.endDate = endDate;
        }
    }
}
