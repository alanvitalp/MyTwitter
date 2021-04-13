/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Interfaces;

import Classes.Perfil.Perfil;

/**
 *
 * @author alanvitalp
 */
public interface IRepositorioUsuario {
    public void cadastrar(Perfil usuario);
    public Perfil buscar(String usuario);
    public void atualizar(Perfil usuario);
}
