/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author bluis
 */
public class Musica {

    String ISRC;
    String nome;
    Float duracao;
    String artista;
    String genero;

    /**
     *
     * @param ISRC
     * @param nome
     * @param duracao
     * @param artista
     * @param genero
     */
    public Musica(String ISRC, String nome, Float duracao, String artista, String genero) {
        this.ISRC = ISRC;
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    /**
     *
     * @return
     */
    public String getISRC() {
        return ISRC;
    }

    /**
     *
     * @param ISRC
     */
    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
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
    public Float getDuracao() {
        return duracao;
    }

    /**
     *
     * @param duracao
     */
    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    /**
     *
     * @return
     */
    public String getArtista() {
        return artista;
    }

    /**
     *
     * @param artista
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     *
     * @return
     */
    public String getGenero() {
        return genero;
    }

    /**
     *
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

}
