package br.edu.ifg.manutencao.coneferserver.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class UtilDataHora {

    /**
     * Zona id de São Pulo.
     */
    public static final String ZONA_ID_SAO_PAULO = "America/Sao_Paulo";

    public static final String FORMATO_DATA_BRASIL = "dd/MM/yyyy";

    public static final String FORMATO_DATA_BRASIL_REDUZIDA = "dd/MM/yy";

    public static final String FORMATO_DATA_HORA_BRASIL = "dd/MM/YYYY HH:mm:ss";

    public static final String FORMATO_DATA_INTERNACIONAL = "yyyy-MM-dd";

    public static final String FORMATO_HORA_BRASIL = "HH:mm";

    public static final String FORMATO_HORA_MINUTO_SEGUNDO_BRASIL = "HH:mm:ss";

    public static final int QUANTIDADE_SEGUNDO_UM_DIA = (60 * 60 * 24);

    /**
     * Método responsável por converter um LocalTime para Date.
     */
    public static Date converterLocalTimeParaDate(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return Date.from(localTime.atDate(LocalDate.now()).atZone(getZoneId()).toInstant());
    }

    /**
     * Método responsável por retornar o LocalDateTime com última hora do dia.
     * Ou seja: Pega um LocalDateTime e atribui na hora dele o valor de 23:59:59.999999
     */
    public static LocalDateTime ultimaHoraDoDia(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 23, 59, 59, 999999999);
    }

    /**
     * Método responsável por retornar o LocalDateTime com a primeiro hora do dia.
     * Ou seja: Pega um LocalDateTime e atribui na hora dele o valor de 00:00:00.000000
     */
    public static LocalDateTime primeiraHoraDoDia(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 0, 0, 0, 0);
    }

    /**
     * Método responsável por transformar um LocalDateTime em uma string conforme a
     * data padrão do Brasil.
     */
    public static String formatarLocalDateTimeParaString(LocalDateTime localDateTime) {
        return formatarLocalDateTimeParaString(localDateTime, FORMATO_DATA_BRASIL);
    }

    public static String formatarLocalDateTimeParaDataHoraString(LocalDateTime localDateTime) {
        return formatarLocalDateTimeParaString(localDateTime, FORMATO_DATA_HORA_BRASIL);
    }

    public static String formatarLocalDateTimeParaHoraString(LocalDateTime localDateTime) {
        return formatarLocalDateTimeParaString(localDateTime, FORMATO_HORA_BRASIL);
    }

    public static String formatarLocalTimeParaString(LocalTime localTime) {
        if (Objects.nonNull(localTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_HORA_BRASIL);
            return localTime.format(formatter);
        }
        return null;
    }

    /**
     * Método responsável por transformar um LocalDateTime em uma string conforme o
     * padrão de data informado.
     */
    public static String formatarLocalDateTimeParaString(LocalDateTime localDateTime, String padraoData) {
        if (localDateTime != null && StringUtils.isNotBlank(padraoData)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padraoData);
            return localDateTime.format(formatter);
        }
        return null;
    }

    public static LocalTime formatarStringParaLocalTime(String padraoHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_HORA_BRASIL);
        return LocalTime.parse(padraoHora, formatter);
    }

    public static LocalTime formatarStringComSegundosParaLocalTime(String padraoHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_HORA_MINUTO_SEGUNDO_BRASIL);
        return LocalTime.parse(padraoHora, formatter);
    }

    public static LocalDate parseStringBrasilParaLocalDate(String padraoHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_BRASIL);
        return LocalDate.parse(padraoHora, formatter);
    }

    public static LocalDate parseStringInternacionalParaLocalDate(String padraoHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_INTERNACIONAL);
        return LocalDate.parse(padraoHora, formatter);
    }

    /**
     * Método responsável por transformar um localdate em uma string conforme a
     * data padrão do Brasil.
     */
    public static String formatarLocalDateParaString(LocalDate localDate) {
        return formatarLocalDateParaString(localDate, FORMATO_DATA_BRASIL);
    }

    public static String formatarLocalDateParaString(LocalDateTime localDate) {
        return formatarLocalDateParaString(localDate, FORMATO_DATA_BRASIL);
    }

    /**
     * Método responsável por transformar um localdate em uma string conforme o
     * padrão de data informado.
     */
    public static String formatarLocalDateParaString(LocalDate localDate, String padraoData) {
        if (localDate != null && StringUtils.isNotBlank(padraoData)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padraoData);
            return localDate.format(formatter);
        }
        return null;
    }

    public static String formatarLocalDateParaString(LocalDateTime localDate, String padraoData) {
        if (localDate != null && StringUtils.isNotBlank(padraoData)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padraoData);
            return localDate.format(formatter);
        }
        return null;
    }

    /**
     * Método responsável por obter a hora e data atual de acordo com Brasília.
     */
    public static LocalDateTime obterDataHoraAtualDeBrasilia() {
        return LocalDateTime.now(getZoneId());
    }

    /**
     * Método responsável por obter a data atual de acordo com Brasília.
     */
    public static LocalDate obterDataAtualDeBrasilia() {
        return LocalDate.now(getZoneId());
    }

    /**
     * Método responsável por obter a hora atual de acordo com Brasília.
     */
    public static LocalTime obterHoraAtualDeBrasilia() {
        return LocalTime.now(getZoneId());
    }

    /**
     * Obtém a data atual de brasília já formatada no padrão dd/MM/yyyy
     */
    public static String obterDataAtualDeBrasiliaFormatada() {
        LocalDate localDate = LocalDate.now(getZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_BRASIL);
        return localDate.format(formatter);
    }

    /**
     * Obtém a data atual de brasília já formatada no padrão yyyy-MM-dd
     */
    public static String obterDataAtualDeBrasiliaFormatadaPadraoSQL() {
        LocalDate localDate = LocalDate.now(getZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_INTERNACIONAL);
        return localDate.format(formatter);
    }

    /**
     * Obtém a data e hora atual de brasília já formatada no padrão dd/MM/YYYY HH:mm:ss
     */
    public static String obterDataHoraAtualDeBrasiliaFormatada() {
        LocalDateTime localDateTime = LocalDateTime.now(getZoneId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_HORA_BRASIL);
        return localDateTime.format(formatter);
    }

    /**
     * Obtém a data atual com a hora zerada
     */
    public static LocalDateTime obterDataHoraAtualDeBrasiliaComHoraZerada() {
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDate dataAtual = LocalDate.now(getZoneId());
        return LocalDateTime.of(dataAtual, meiaNoite);
    }

    /**
     * Obtém data e hora atual em forma de millisegundos.
     */
    public static long obterEpochDataHoraAtualDeBrasilia() {
        return LocalDateTime.now(getZoneId()).atZone(getZoneId()).toInstant().toEpochMilli();
    }

    /**
     * Método responsável por transformar EpochMilli em LocalDateTime.
     */
    public static LocalDateTime converterEpochMilliEmLocalDateTime(long tempo) {
        if (tempo == 0) {
            return null;
        }
        return Instant.ofEpochMilli(tempo).atZone(getZoneId()).toLocalDateTime();
    }

    /**
     * Método responsável por transformar EpochMilli em LocalDate.
     */
    public static LocalDate converterEpochMilliEmLocalDate(long tempo) {
        if (tempo == 0) {
            return null;
        }

        return Instant.ofEpochMilli(tempo).atZone(getZoneId()).toLocalDate();
    }

    /**
     * Método responsável por converter LocalDateTime para LocalDate.
     */
    public static LocalDate converterLocalDateTimeParaLocalDate(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return localDateTime.toLocalDate();
        }
        return null;
    }

    /**
     * Converte LocalDateTime para LocalTime.
     */
    public static LocalTime converterLocalDateTimeParaLocalTime(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime();
    }

    /**
     * Método responsável por transformar EpochMilli em LocalDateTime.
     */
    public static LocalDateTime converterEpochSecondsEmLocalDateTime(long tempo) {
        if (tempo == 0) {
            return null;
        }
        return Instant.ofEpochSecond(tempo).atZone(getZoneId()).toLocalDateTime();
    }

    /**
     * Método responsável por transformar EpochMilli em LocalDate.
     */
    public static LocalDate converterEpochSecondsEmLocalDate(long tempo) {
        if (tempo == 0) {
            return null;
        }

        return Instant.ofEpochSecond(tempo).atZone(getZoneId()).toLocalDate();
    }

    public static boolean isDataValida(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_DATA_BRASIL);
        DateTimeFormatter dateTimeFormatterReduzida = DateTimeFormatter.ofPattern(FORMATO_DATA_BRASIL_REDUZIDA);
        try {
            LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException dataNormal) {
            try {
                LocalDate.parse(strDate, dateTimeFormatterReduzida);
                return true;
            } catch (DateTimeParseException dataReduzida) {
                return false;
            }
        }
    }

    public static String formatarData(String valor) {
        try {

            DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");

            DateFormat from = new SimpleDateFormat("yyyy-MM-dd");

            return outputFormatter.format(from.parse(valor));

        } catch (Exception e) {
            return null;
        }
    }

    public static Long converterLocalDateParaEpochTime(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atStartOfDay(getZoneId()).toEpochSecond();
        }
        return 0L;
    }

    public static Long converterLocalDateParaEpochTimeMS(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atStartOfDay(getZoneId()).toInstant().toEpochMilli();
        }
        return 0L;
    }

    public static Date converterLocalDateEmDate(LocalDate localDate) {
        if (localDate != null) {
            return Date.from(localDate.atStartOfDay(getZoneId()).toInstant());
        }
        return null;
    }

    public static LocalDate converterDateEmLocalDate(Date date) {

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean checkBetween(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
    }

    public static LocalDateTime converterDataHoraEmLocalDateTime(LocalDate data, LocalTime hora){
        return LocalDateTime.of(data, hora);
    }

    public static LocalDate converterStringParaLocalDate(String localDate){
        return LocalDate.parse(localDate);
    }

    public static TimeZone getTimezone() {
        return TimeZone.getTimeZone(ZONA_ID_SAO_PAULO);
    }

    public static ZoneId getZoneId() {
        return ZoneId.of(ZONA_ID_SAO_PAULO);
    }

    public static Integer getIdade(LocalDate dataNascimento) {
        if (Objects.nonNull(dataNascimento)) {
            return Period.between(dataNascimento, UtilDataHora.obterDataAtualDeBrasilia()).getYears();
        } else {
            return 0;
        }
    }
}
