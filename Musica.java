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
        String ISR;
        String nome;
        Float duracao;
        String artista;
        String genero;

    public Musica(String ISRC, String nome, Float duracao, String artista, String genero) {
        this.ISR = ISRC;
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    public String getISR() {
        return ISR;
    }

    public void setISR(String ISRC) {
        this.ISR = ISRC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
        
        
}
  
   