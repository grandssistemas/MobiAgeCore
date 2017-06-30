package br.com.codein.mobiagecore.domain.exceptions;

/**
 * Created by gelatti on 07/06/17.
 */
public class ReportException extends RuntimeException {

    /**
     * @param message mensagem que será emitada na exceção
     */
    public ReportException(String message) {
        super(message);
    }
}
