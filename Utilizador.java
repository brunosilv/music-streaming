/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Bruno Silva
 */
public class Utilizador {

    String nome;
    String username;
    String mail;

    public Utilizador(String nome, String username, String mail) {
        this.nome = nome;
        this.username = username;
        this.mail = mail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public static SeparateChainingHashST1 createUsersSt(SeparateChainingHashST1 utilizadorST) {

        In in = new In(".//data//pessoas.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];
            String email = texto[2];

            Utilizador u = new Utilizador(nome, username, email);
            utilizadorST.put(username, u);
        }

        return utilizadorST;
    }

    public static SeparateChainingHashST1 updateUser(SeparateChainingHashST1<String, Utilizador> utilizadorST, String key) {
        In in = new In();
        String nome = null, username = null, email = null;

        Utilizador u = new Utilizador(nome, username, email);
        u = (Utilizador) utilizadorST.get(key);
        StdOut.print("O que pretende editar");
        StdOut.print("1-Nome\n");
        StdOut.print("2-Username\n");
        StdOut.print("3-Email\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                nome = StdIn.readString();
                u.setNome(nome);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
                break;
            case 2:
                username = StdIn.readString();
                u.setUsername(username);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
                break;
            case 3:
                email = StdIn.readString();
                u.setMail(email);
                utilizadorST.put(key, null);
                utilizadorST.put(key, u);
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return utilizadorST;
    }

    public static SeparateChainingHashST1 deleteUserSt(SeparateChainingHashST1 utilizadorST, Integer key) {
        utilizadorST.delete(key);
        return utilizadorST;
    }
}
