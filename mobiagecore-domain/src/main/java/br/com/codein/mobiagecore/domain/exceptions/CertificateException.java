package br.com.codein.movementgroup.domain.exception;

public class CertificateException extends RuntimeException{

    /**
     * @param message mensagem que será emitada na exceção
     */
    public CertificateException(String message) {
        super(message);
    }
}