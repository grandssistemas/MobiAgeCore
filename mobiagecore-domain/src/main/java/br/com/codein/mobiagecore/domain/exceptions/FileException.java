package br.com.codein.mobiagecore.domain.exceptions;

public class FileException extends RuntimeException {

    /**
     * @param message mensagem que será emitada na exceção
     */
    public FileException(String message) {
        super(message);
    }


}
