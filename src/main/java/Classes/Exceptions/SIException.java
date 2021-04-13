/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Exceptions;

import Classes.Perfil.Perfil;

/**
 *
 * @author alanvitalp
 */
public class SIException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Perfil user;

    public SIException(Perfil seguidor) {
        this.user = seguidor;
    }

    @Override
    public String toString() {
        return "\nSIException: Seguidor inválido";
    }
}
