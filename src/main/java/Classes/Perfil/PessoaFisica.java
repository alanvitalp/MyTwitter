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
public class PessoaFisica extends Perfil {
    private long cpf;

    public PessoaFisica(String usuario, Vector<Perfil> seguidores, Vector<Tweet> timeline, boolean ativo) {
        super(usuario, seguidores, timeline, ativo);
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCpf() {
        return cpf;
    }
}
