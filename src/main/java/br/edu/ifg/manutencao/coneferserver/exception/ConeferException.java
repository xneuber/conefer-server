package br.edu.ifg.manutencao.coneferserver.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

public class ConeferException extends Exception {

    private int status = HttpStatus.BAD_REQUEST.value();

    @Accessors(chain = true)
    @Setter
    @Getter
    private String errMessage;

    @Accessors(chain = true)
    @Setter
    @Getter
    private Object[] msgArgs;

    public ConeferException(String message) {
        super(message);
    }

    public ConeferException() {
    }

    public ConeferException(String message, Object... args) {
        super(String.format(message, args));
        this.msgArgs = args;
    }

    public ConeferException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConeferException(Throwable cause, String message, Object... args) {
        super(String.format(message, args), cause);
        this.msgArgs = args;
    }

    public ConeferException(Throwable cause) {
        super(cause);
    }

    public ConeferException(int status, String mensagem) {
        super(mensagem);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
