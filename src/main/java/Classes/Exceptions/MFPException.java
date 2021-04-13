/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Exceptions;

/**
 *
 * @author alanvitalp
 */
public class MFPException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String message;

    public MFPException(String mensagem) {
        this.message = mensagem;
    }

    @Override
    public String toString() {
        return "\nMFPException: Mensagem fora do padrão";
    }
}
