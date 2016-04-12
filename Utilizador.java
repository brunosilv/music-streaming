/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.RedBlackBST;
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
   
    private RedBlackBST_Projecto<String, Playlist> userplSt = new RedBlackBST_Projecto<>();//playlists deste user
    
    /**
     *
     * @param nome
     * @param username
     * @param mail
     */
    public Utilizador(String nome, String username, String mail) {
        this.nome = nome;
        this.username = username;
        this.mail = mail;
    }

    /**
     *
     * @return
     */
    public RedBlackBST_Projecto<String, Playlist> getUserplSt() {
        return userplSt;
    }

    /**
     *
     * @param userplST
     */
    public void setUserplSt(RedBlackBST_Projecto<String, Playlist> userplST) {
        this.userplSt = userplST;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

}
