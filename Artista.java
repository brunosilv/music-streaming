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
public class Artista {

    String username;
    String nome;
    String generomusical;
    private RedBlackBST_Projecto<String, Musica> artistMusicSt = new RedBlackBST_Projecto<>();//Musica deste artista

    /**
     *
     * @param username
     * @param nome
     * @param generomusical
     */
    public Artista(String username, String nome, String generomusical) {
        this.username = username;
        this.nome = nome;
        this.generomusical = generomusical;
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
    public String getGeneromusical() {
        return generomusical;
    }

    /**
     *
     * @param generomusical
     */
    public void setGeneromusical(String generomusical) {
        this.generomusical = generomusical;
    }

    /**
     *
     * @return
     */
    public RedBlackBST_Projecto<String, Musica> getArtistMusicSt() {
        return artistMusicSt;
    }

    /**
     *
     * @param artistMusicSt
     */
    public void setArtistMusicSt(RedBlackBST_Projecto<String, Musica> artistMusicSt) {
        this.artistMusicSt = artistMusicSt;
    }

}
