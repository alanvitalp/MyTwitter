/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Vector;

import Classes.Interfaces.IRepositorioUsuario;
import Classes.Interfaces.ITwitter;

import Classes.Perfil.Perfil;

import Classes.Exceptions.PEException;
import Classes.Exceptions.PIException;
import Classes.Exceptions.PDException;
import Classes.Exceptions.MFPException;
import Classes.Exceptions.SIException;
// import java.util.stream.Collectors;

/**
 *
 * @author alanvitalp
 */

public class MyTwitter implements ITwitter {
    IRepositorioUsuario repositorio;

    public MyTwitter(IRepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void criarPerfil(Perfil usuario) {
        try {
            Perfil search = repositorio.buscar(usuario.getUsuario());

            if (search != null) {
                throw new PEException(usuario);
            }

            else {
                repositorio.cadastrar(usuario);
                usuario.setAtivo(true);
                System.out.println("\nPerfil criado com sucesso");
            }
        } catch (PEException error) {
            System.out.println(error);
        }
    }

    @Override
    public void cancelarPerfil(String usuario) {
        try {
            Perfil search = repositorio.buscar(usuario);

            if (search != null && search.isAtivo()) {
                search.setAtivo(false);
                System.out.println("Você desativou o seu perfil");
            } else if (search == null) {
                throw new PIException(search);
            } else if ((search != null) && (!search.isAtivo())) {
                throw new PDException(search);
            }

        } catch (PDException | PIException error) {
            System.out.println(error);
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) {
        Perfil search = repositorio.buscar(usuario);
        int length = mensagem.length();

        try {
            if (search != null && search.isAtivo()) {
                if (length >= 1 && length <= 140) {
                    Tweet tweet = new Tweet(usuario, mensagem);
                    search.addTweet(tweet);
                    Vector<Perfil> seguidores = search.getSeguidores();

                    for (int i = 0; i < seguidores.size(); i++) {
                        String follower = seguidores.get(i).getUsuario();
                        Perfil newSearch = repositorio.buscar(follower);
                        newSearch.addTweet(tweet);
                    }

                    System.out.println("\nTweet enviado");

                } else {
                    throw new MFPException(mensagem);
                }
            } else {
                throw new PIException(search);
            }
        } catch (MFPException | PIException error) {
            System.out.println(error);
        }

    }

    @Override
    public Vector<Tweet> timeline(String usuario) {
        Perfil search = repositorio.buscar(usuario);

        try {
            if (search != null && search.isAtivo()) {
                return search.getTimeline();
            } else if (search != null && !search.isAtivo()) {
                throw new PDException(search);
            } else {
                throw new PIException(search);
            }
        } catch (PIException | PDException error) {
            System.out.println(error);
        }

        return null;
    }

    @Override
    public Vector<Tweet> tweets(String usuario) {
        Perfil search = repositorio.buscar(usuario);

        try {
            if (search != null && search.isAtivo()) {
                Vector<Tweet> tweets = new Vector();
                Vector<Tweet> timeline = timeline(usuario);

                for (int i = 0; i < timeline.size(); i++) {
                    Tweet tweet = timeline.get(i);
                    if (tweet.getUsuario().equals(usuario)) {
                        tweets.add(tweet);
                    }
                }

                return tweets;

            } else if (search != null && !search.isAtivo()) {
                throw new PDException(search);
            } else {
                throw new PIException(search);
            }
        } catch (PIException | PDException error) {
            System.out.println(error);
        }

        return null;
    }

    @Override
    public void seguir(String seguidor, String seguido) {
        Perfil follower = repositorio.buscar(seguidor);
        Perfil followed = repositorio.buscar(seguido);

        try {
            if (follower.getUsuario().equals(followed.getUsuario())) {
                throw new SIException(followed);
            }

            if (follower == null || followed == null) {
                throw new PIException(follower);
            } else {
                if (follower.isAtivo() && followed.isAtivo()) {
                    followed.addSeguidor(follower);
                    follower.addSeguido(followed);
                } else {
                    throw new PDException(followed);
                }
            }
        } catch (PIException | PDException | SIException error) {
            System.out.println(error);
        }
    }

    @Override
    public int numeroSeguidores(String usuario) {
        Perfil search = repositorio.buscar(usuario);
        int count = 0;
        try {
            if (search != null && search.isAtivo()) {
                count = search.getSeguidores().stream().filter(u -> u.isAtivo()).map(_item -> 1).reduce(count,
                        Integer::sum);
                return count;
            } else if (search == null) {
                throw new PIException(search);
            } else {
                throw new PDException(search);
            }
        } catch (PDException | PIException error) {
            System.out.println(error);
        }

        return count;
    }

    @Override
    public Vector<Perfil> seguidores(String usuario) {
        Perfil search = repositorio.buscar(usuario);

        try {
            if (search != null && search.isAtivo()) {
                Vector<Perfil> userFollowers = new Vector();

                search.getSeguidores().stream().filter(u -> u.isAtivo()).forEach(user -> userFollowers.add(user));

                return userFollowers;
            } else if (search == null) {
                throw new PIException(search);
            } else {
                throw new PDException(search);
            }
        } catch (PDException | PIException error) {
            System.out.println(error);
        }

        return null;
    }

    @Override
    public Vector<Perfil> seguidos(String usuario) {
        Perfil search = repositorio.buscar(usuario);

        try {
            if (search != null && search.isAtivo()) {
                Vector<Perfil> userFollowers = new Vector();

                search.getSeguidos().stream().filter(u -> u.isAtivo()).forEach(user -> userFollowers.add(user));

                return userFollowers;
            } else if (search == null) {
                throw new PIException(search);
            } else {
                throw new PDException(search);
            }
        } catch (PDException | PIException error) {
            System.out.println(error);
        }

        return null;
    }

}
