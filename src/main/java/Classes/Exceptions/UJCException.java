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
public class UJCException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Perfil user;

    public UJCException(Perfil usuario) {
        this.user = usuario;
    }

    @Override
    public String toString() {
        return "\nUJCException: Usuário já está cadastrado";
    }
}
