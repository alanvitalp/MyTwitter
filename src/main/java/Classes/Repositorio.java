/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Vector;
import java.util.Scanner;

import Classes.Interfaces.IRepositorioUsuario;
import Classes.Perfil.Perfil;

import Classes.Exceptions.UJCException;
import Classes.Exceptions.UNCException;

/**
 *
 * @author alanvitalp
 */
public class Repositorio implements IRepositorioUsuario {
    Vector<Perfil> users = new Vector();
    Scanner input = new Scanner(System.in);

    @Override
    public void cadastrar(Perfil usuario) {
        try {
            Perfil search = buscar(usuario.getUsuario());

            if (search == null) {
                users.add(usuario);
                usuario.setAtivo(true);
            } else {
                throw new UJCException(usuario);
            }
        } catch (UJCException error) {
            System.out.println(error);
        }
    }

    @Override
    public Perfil buscar(String usuario) {
        for (int i = 0; i < users.size(); i++) {
            Perfil searched = users.get(i);

            if (searched.getUsuario().equals(usuario)) {
                return searched;
            }
        }
        return null;
    }

    @Override
    public void atualizar(Perfil usuario) {
        try {
            Perfil search = buscar(usuario.getUsuario());

            if (search == null) {
                throw new UNCException(usuario);
            } else {
                System.out.print("Escreva o novo nome de usuário: ");
                String newUser = input.next();

                Perfil searchNew = buscar(newUser);

                while (searchNew != null) {
                    System.out.println("\nNome de usuário já existe");
                    System.out.print("Escreva o novo nome de usuário: ");
                    newUser = input.next();
                    searchNew = buscar(newUser);
                }

                search.setUsuario(newUser);
            }
        } catch (UNCException error) {
            System.out.println(error);
        }
    }
}
