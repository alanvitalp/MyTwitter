/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Perfil;

import Classes.Tweet;

import java.util.Vector;

/**
 *
 * @author alanvitalp
 */
public abstract class Perfil {
    private String usuario;
    private Vector<Perfil> seguidos = new Vector();
    private Vector<Perfil> seguidores = new Vector();
    private Vector<Tweet> timeline = new Vector();
    private boolean ativo;

    public Perfil(String usuario, Vector<Perfil> seguidores, Vector<Tweet> timeline, boolean ativo) {
        this.usuario = usuario;
        this.seguidores = seguidores;
        this.timeline = timeline;
        this.ativo = ativo;
    }

    public void addSeguido(Perfil usuario) {
        this.seguidos.add(usuario);
    }

    public void addSeguidor(Perfil usuario) {
        this.seguidores.add(usuario);
    }

    public void addTweet(Tweet tweet) {
        this.timeline.add(tweet);
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public Vector<Perfil> getSeguidos() {
        return seguidos;
    }

    public Vector<Perfil> getSeguidores() {
        return seguidores;
    }

    public Vector<Tweet> getTimeline() {
        return timeline;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo != false;
    }

}
