package br.com.codein.mobiagecore.domain.exceptions;

public class CertificateException extends RuntimeException{

    /**
     * @param message mensagem que será emitada na exceção
     */
    public CertificateException(String message) {
        super(message);
    }
}