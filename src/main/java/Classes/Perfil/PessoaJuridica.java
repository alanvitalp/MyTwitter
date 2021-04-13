/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Perfil;

import java.util.Vector;

import Classes.Tweet;

/**
 *
 * @author alanvitalp
 */
public class PessoaJuridica extends Perfil {
    private long cnpj;

    public PessoaJuridica(String usuario, Vector<Perfil> seguidores, Vector<Tweet> timeline, boolean ativo) {
        super(usuario, seguidores, timeline, ativo);
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public long getCnpj() {
        return cnpj;
    }
}
