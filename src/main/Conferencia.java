/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Wilson
 * classe para instanciar as linhas lidas do arquivo input, separando em titulo
 * e duracao
 */
public class Conferencia {
    private String titulo;
    private int duracao;
    
    public Conferencia (String titulo, int duracao){
        this.titulo = titulo;
        this.duracao = duracao;
    }
    
    public void setTitulo (String titulo){
        this.titulo = titulo;
    }
    public String getTitulo(){
        return this.titulo;
    }
    
    public void setDuracao(int duracao){
        this.duracao = duracao;
    }
    public int getDuracao(){
        return this.duracao;
    }
}
