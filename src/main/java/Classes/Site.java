/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import java.util.Scanner;
import java.util.Vector;

import Classes.Exceptions.PEException;
import Classes.Exceptions.PIException;
import Classes.Exceptions.SIException;
import Classes.Exceptions.UNCException;
import Classes.Perfil.Perfil;
import Classes.Perfil.PessoaFisica;
import Classes.Perfil.PessoaJuridica;

/**
 *
 * @author alanvitalp
 */
public class Site {
    static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) throws PIException {
        Repositorio repository = new Repositorio();
        MyTwitter twitter = new MyTwitter(repository);

        int endProgram = 1;
        String option;

        while (endProgram == 1) {
            System.out.println("\n\t\t-------------------- Bem-vindo ao MyTwitter --------------------");
            System.out.println("\n1 - Entrar na sua conta");
            System.out.println("2 - Registrar-se");
            System.out.println("3 - Sair da sua conta");
            System.out.println("4 - Atualizar os dados de uma conta");
            System.out.print("\nDigite sua opção: ");

            option = scanner.next();

            switch (option) {

            case "1":
                System.out.print("\nDigite o seu nome de usuário: ");
                String username = scanner.next();

                Perfil user = repository.buscar(username);

                if (user == null) {
                    throw new PIException(user);
                } else {

                    if (user.isAtivo()) {
                        System.out.println("\nBem-vindo " + user.getUsuario() + "!");
                    } else {
                        System.out.println("\nBem-vindo de volta " + user.getUsuario() + "!");
                        user.setAtivo(true);
                    }

                    System.out.println("\nO que você deseja?");

                    boolean end = true;
                    String option1;

                    while (end) {

                        System.out.println("\n\n1 - Tweetar");
                        System.out.println("2 - Ver timeline");
                        System.out.println("3 - Ver tweets");
                        System.out.println("4 - Seguir um usuário");
                        System.out.println("5 - Ver total de seguidores");
                        System.out.println("6 - Ver seguidores");
                        System.out.println("7 - Ver seguidos");
                        System.out.println("8 - Atualizar dados");
                        System.out.println("9 - Desativar sua conta");
                        System.out.println("0 - Sair da sua conta");

                        option1 = scanner.next();

                        switch (option1) {
                        case "1":
                            System.out.print("\nO que está acontecendo? ");
                            String userTweet = scanner.next();

                            twitter.tweetar(user.getUsuario(), userTweet);

                            break;

                        case "2":
                            Vector<Tweet> userTimeline = twitter.timeline(user.getUsuario());

                            System.out.println("\nTimeline: ");
                            for (int i = 0; i < userTimeline.size(); i++) {
                                Tweet tweet = (Tweet) userTimeline.get(i);

                                System.out.println("\n");
                                System.out.println(tweet.getUsuario() + ": " + tweet.getMensagem());
                                System.out.println("\n");
                            }

                            break;

                        case "3":
                            Vector<Tweet> userTweets = twitter.tweets(user.getUsuario());

                            System.out.println("\nSeus Tweets: ");
                            for (int i = 0; i < userTweets.size(); i++) {
                                Tweet tweet1 = (Tweet) userTweets.get(i);

                                System.out.println(tweet1.getUsuario() + ": " + tweet1.getMensagem());
                                System.out.println("\n");
                            }
                            break;

                        case "4":
                            System.out.print("\nDigite o nome do usuário que você quer seguir: ");
                            String newFollower = scanner.next();

                            if (repository.buscar(newFollower) != null) {
                                try {

                                    if (newFollower.equals(user.getUsuario())) {
                                        throw new SIException(user);
                                    } else {
                                        twitter.seguir(user.getUsuario(), newFollower);
                                        System.out.println("\nVocê seguiu " + newFollower + "!");
                                    }

                                } catch (SIException error) {
                                    System.out.println("\n" + error);
                                }
                            } else {
                                System.out.println("Usuário não foi encontrado!");
                            }
                            break;

                        case "5":
                            int total = twitter.numeroSeguidores(user.getUsuario());

                            System.out.print("\nTotal dos seus seguidores: " + total);
                            System.out.println("");

                            break;

                        case "6":
                            Vector<Perfil> userFollowers = twitter.seguidores(user.getUsuario());

                            System.out.println("\nSeus seguidores: ");
                            for (int i = 0; i < userFollowers.size(); i++) {
                                String usersPerfil = userFollowers.get(i).getUsuario();

                                System.out.println(usersPerfil);
                            }

                            break;

                        case "7":
                            Vector<Perfil> userInRow = twitter.seguidos(user.getUsuario());

                            System.out.println("\nVocê segue: ");
                            for (int i = 0; i < userInRow.size(); i++) {
                                String usersProfile = userInRow.get(i).getUsuario();

                                System.out.println(usersProfile);
                            }

                            break;

                        case "8":
                            repository.atualizar(user);
                            break;

                        case "9":
                            twitter.cancelarPerfil(user.getUsuario());
                            end = false;
                            break;

                        case "0":
                            end = false;
                            break;

                        default:
                            System.out.println("Opcão inválida ");
                        }

                    }
                }
                break;

            case "2":

                System.out.print("\nDigite um nome de usuário: ");

                String newUser = scanner.next();

                Vector<Perfil> newFollowers = new Vector();
                Vector<Tweet> newTimeline = new Vector();

                Perfil newSearch = repository.buscar(newUser);

                while (newSearch != null) {
                    try {
                        throw new PEException(newSearch);
                    } catch (PEException error) {
                        System.out.println(error);
                    }

                    System.out.print("\nDigite outro nome de usuário: ");
                    newUser = scanner.next();
                    newSearch = repository.buscar(newUser);
                }

                System.out.println("\n1 - Você é uma Pessoa física?");
                System.out.println("2 - Você é uma Pessoa Jurídica?");

                String userOption = scanner.next();

                if (userOption.equals("1")) {
                    System.out.print("\nDigite o seu CPF: ");
                    String cpf = scanner.next();
                    long cpf1 = Long.parseLong(cpf);

                    while (cpf.length() != 1) {
                        System.out.println("\ncpf deve ter 11 numeros");
                        System.out.print("Digite  seu CPF: ");
                        cpf = scanner.next();
                        cpf1 = Long.parseLong(cpf);
                    }

                    PessoaFisica newPerfil = new PessoaFisica(newUser, newFollowers, newTimeline, true);
                    newPerfil.setCpf(cpf1);
                    twitter.criarPerfil(newPerfil);
                } else if (userOption.equals("2")) {
                    System.out.print("\nDigite o seu CNPJ: ");
                    String cnpj = scanner.next();
                    long cnpj1 = Long.parseLong(cnpj);

                    while (cnpj.length() != 1) {
                        System.out.println("\nCNPJ deve ter 14 numeros");
                        System.out.print("Digite  seu CNPJ: ");
                        cnpj = scanner.next();
                        cnpj1 = Long.parseLong(cnpj);
                    }

                    PessoaJuridica newPerfil1 = new PessoaJuridica(newUser, newFollowers, newTimeline, true);
                    newPerfil1.setCnpj(cnpj1);
                    twitter.criarPerfil(newPerfil1);
                }
                break;

            case "3":
                System.out.println("\nSaindo...");
                endProgram = 0;
                scanner.close();
                break;

            case "4":
                try {

                    System.out.print("\nDigite o usuário à atualizar: ");
                    String userAtt = scanner.next();

                    Perfil searchUser = repository.buscar(userAtt);

                    if (searchUser == null) {
                        throw new UNCException(searchUser);
                    } else {
                        repository.atualizar(searchUser);
                    }

                } catch (UNCException error) {
                    System.out.println(error);
                }

                break;

            default:
                System.out.println("Opcão inválida. 2");
                break;
            }

        }

    }

}
