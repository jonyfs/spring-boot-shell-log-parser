package br.com.jonyfs.parser;

import java.time.format.DateTimeFormatter;

public class ParserConstants {
    public static final DateTimeFormatter FORMATTER_LOG = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static final DateTimeFormatter FORMATTER_QUERY = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");
}
