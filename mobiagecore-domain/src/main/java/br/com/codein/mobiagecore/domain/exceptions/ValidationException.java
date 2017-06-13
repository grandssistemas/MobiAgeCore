package br.com.codein.mobiagecore.domain.exceptions;

public class ValidationException extends RuntimeException{

    /**
     *
     * @param message mensagem que será emitida na exceção
     */
    public ValidationException(String message) {
        super(message);
    }
}
