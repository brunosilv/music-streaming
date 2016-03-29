/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

/**
 *
 * @author jtorres
 */
public class Genero {
    
    private String genero; // chave unica
    private String descricao; // chave unica
    private RedBlackBST_Projecto<String, Músicas> generoMusicsST = new RedBlackBST_Projecto<>(); // musicas deste genero


    public Genero(String genero, String descricao) {
        this.genero = genero;
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    /**
     * Get the value of genero
     *
     * @return the value of genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Set the value of genero
     *
     * @param genero new value of genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }


    /**
     * Get the value of generoMusicsST
     *
     * @return the value of generoMusicsST
     */
    public RedBlackBST_Projecto getGeneroMusicsST() {
        return generoMusicsST;
    }

    /**
     * Set the value of generoMusicsST
     *
     * @param generoMusicsST new value of generoMusicsST
     */
    public void setGeneroMusicsST(RedBlackBST_Projecto generoMusicsST) {
        this.generoMusicsST = generoMusicsST;
    }

}
