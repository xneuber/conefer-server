package br.edu.ifg.manutencao.coneferserver.util;

import br.edu.ifg.manutencao.coneferserver.exception.ConeferException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.swing.text.MaskFormatter;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UtilString {

    public static String converterZeroParaSlashZero(String s) {
        if (StringUtils.isNotBlank(s)) {
            return s.replaceAll("0", "\u00D8");
        }
        return s;
    }

    public static String removerCaracteresEspeciais(final String nome) {
        String novoNome = nome;
        novoNome = Normalizer.normalize(novoNome, Normalizer.Form.NFD);
        novoNome = novoNome.replaceAll("[^\\p{ASCII}]", "");
        return novoNome;
    }

    public static String convertStringParaAplhanumeric(String str) {
        return str.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    public static String desformatarCpf(String cpf) {
        return desformatarCnpj(cpf);
    }

    public static String desformatarCnpj(String cnpj) {
        if (StringUtils.isNotBlank(cnpj)) {
            cnpj = cnpj.replaceAll("\\.", "");
            cnpj = cnpj.replaceAll("/", "");
            cnpj = cnpj.replaceAll("-", "");
            return cnpj;
        }
        return cnpj;
    }

    public static String formatarCnpj(String cnpj) {
        if (StringUtils.isNotBlank(cnpj)) {
            String somenteNumeros = considerarSomenteNumerosDoTexto(cnpj);

            if (somenteNumeros.length() == 14) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(somenteNumeros, 0, 2);
                stringBuilder.append(".");
                stringBuilder.append(somenteNumeros, 2, 5);
                stringBuilder.append(".");
                stringBuilder.append(somenteNumeros, 5, 8);
                stringBuilder.append("/");
                stringBuilder.append(somenteNumeros, 8, 12);
                stringBuilder.append("-");
                stringBuilder.append(somenteNumeros, 12, 14);

                return stringBuilder.toString();
            }
        }
        return cnpj;
    }

    public static String convertStringToUnicode(String str) {
        StringBuffer ostr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ((ch >= 0x0020) && (ch <= 0x007e)) {
                ostr.append(ch);
            } else {
                ostr.append("\\u");
                String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
                for (int j = 0; j < 4 - hex.length(); j++) {
                    ostr.append("0");
                }
                ostr.append(hex.toLowerCase());
            }
        }
        return (new String(ostr));
    }

    public static String formatarCpf(String cpf) throws ConeferException {
        if (StringUtils.isNotBlank(cpf)) {
            String somenteNumeros = considerarSomenteNumerosDoTexto(cpf);

            if (somenteNumeros.length() == 11) {
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(somenteNumeros, 0, 3);
                stringBuilder.append(".");
                stringBuilder.append(somenteNumeros, 3, 6);
                stringBuilder.append(".");
                stringBuilder.append(somenteNumeros, 6, 9);
                stringBuilder.append("-");
                stringBuilder.append(somenteNumeros, 9, 11);

                return stringBuilder.toString();
            } else {
                throw new ConeferException("CPF Inválido");
            }
        } else {
            return cpf;

        }
    }

    public static boolean validarEmail(String email) {
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String nomeMesConformeNumero(int mesNumero) {
        switch (mesNumero) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "Mês Inválido";
        }
    }

    public static String considerarSomenteNumerosDoTexto(String texto) {
        if (StringUtils.isNotBlank(texto)) {
            return texto.replaceAll("\\D+", "");
        } else {
            return texto;
        }
    }

    public static boolean isNotNumeroInteiro(String stringAValidar) {
        return !isNumeroInteiro(stringAValidar);
    }

    public static boolean isNumeroInteiro(String stringAValidar) {
        try {
            Long.parseLong(stringAValidar);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static String extrairPrimeiroNome(String nomeCompleto) {
        if (StringUtils.isNotBlank(nomeCompleto)) {
            String nomeCompletoTrim = nomeCompleto.trim();
            String[] nome = nomeCompletoTrim.split(" ");
            return nome[0];
        }
        return nomeCompleto;
    }

    public static String considerarSomenteLetrasENumerosDoTexto(String texto) {
        if (texto != null) {
            return texto.replaceAll("[^a-zA-Z0-9]", "");
        }
        return texto;
    }

    public static String removerAcentosECase(String string) {

        String retorno = string;
        if (string != null && !string.isEmpty()) {
            retorno = retorno.replaceAll("[ÁÀÂÃ]", "A");
            retorno = retorno.replaceAll("[áàâã]", "a");
            retorno = retorno.replaceAll("[ÉÈÊ]", "E");
            retorno = retorno.replaceAll("[éèê]", "e");
            retorno = retorno.replaceAll("[ÍÌÎÏ]", "I");
            retorno = retorno.replaceAll("[íìîï]", "i");
            retorno = retorno.replaceAll("[ÓÒÔÕÖ]", "O");
            retorno = retorno.replaceAll("[óòôõºö]", "o");
            retorno = retorno.replaceAll("[ÚÙÛÜ]", "U");
            retorno = retorno.replaceAll("[úùûü]", "u");
            retorno = retorno.replaceAll("[Ç]", "C");
            retorno = retorno.replaceAll("[ç]", "c");
        }
        return retorno.toLowerCase();
    }


    public static String aplicarMascara(String pattern, String value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validarCEP(String cep) {
        String numeros = UtilString.considerarSomenteNumerosDoTexto(cep);
        if (StringUtils.isNotBlank(numeros)) {
            return numeros.length() == 8;
        }
        return false;
    }



    public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }


    public static StringBuilder converterListaDeStringParaStringBuilder(List<String> lista) {
        StringBuilder stringBuilder = new StringBuilder();

        lista.forEach(i -> stringBuilder.append(i).append("\n"));

        return stringBuilder;
    }

    public static boolean validarNumeroCelular(String telefone) {
        if (StringUtils.isNotBlank(telefone)) {
            String telefoneStringNumeros = considerarSomenteNumerosDoTexto(telefone);
            return StringUtils.isNotBlank(telefoneStringNumeros) && (telefoneStringNumeros.length() >= 10);
        }
        return false;
    }

    public static boolean validarFormatacaoTelefone(String telefone) {
        if (StringUtils.isNotBlank(telefone)) {
            telefone = telefone.replaceAll(" ", "");
            telefone = considerarSomenteNumerosDoTexto(telefone);
            return telefone.length() == 11;
        } else {
            return false;
        }

    }

    public static boolean isTelefoneValido(String telefone) {
        final String TELEFONE_REGEX = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$";

        return Pattern.matches(TELEFONE_REGEX, considerarSomenteNumerosDoTexto(telefone));

    }

    public static boolean validarNome(String nome) {
        boolean isValido = false;
        nome = Objects.nonNull(nome) ? removerCaracteresEspeciaisNome(nome) : "";

        if (!nome.isEmpty() && nome.length() > 2) {

            String nomeCompleto[] = nome.split(" ");

            isValido = nomeCompleto.length > 1;
        }

        return isValido;
    }

    public static String removerCaracteresEspeciaisNome(String str) {

        String[] caracteresEspeciais = {"\\.", ",", "-", "\\+", "\\*", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "’"};

        for (int i = 0; i < caracteresEspeciais.length; i++) {
            str = str.replaceAll(caracteresEspeciais[i], "");
        }

        //Troca os espaços no início por ""
        str = str.replaceAll("^\\s+", "");

        //Troca os espaços no início por ""
        str = str.replaceAll("\\s+$", "");

        //Troca os espaços duplicados, tabulações e etc por  " "
        str = str.replaceAll("\\s+", " ");

        return str;
    }

    public static boolean validarPlaca(String placa) {
        placa = placa.replaceAll("-", "");

        boolean result = false;

        String regexPlaca = "^[a-zA-Z]{3}[0-9]{4}$";
        String regexPlacaMercosulCarro = "^[a-zA-Z]{3}[0-9]{1}[a-zA-Z]{1}[0-9]{2}$";
        String regexPlacaMercosulMoto = "^[a-zA-Z]{3}[0-9]{2}[a-zA-Z]{1}[0-9]{1}$";

        if (Pattern.compile(regexPlaca).matcher(placa).matches()) {

            result = true;

        }else if(Pattern.compile(regexPlacaMercosulCarro).matcher(placa).matches()) {

            result = true;

        }else if (Pattern.compile(regexPlacaMercosulMoto).matcher(placa).matches()) {

            result = true;
        }

        return result;
    }
}
