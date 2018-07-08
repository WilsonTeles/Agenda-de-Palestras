/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilson
 * classe para agrupar os objetos da classe Conferencia em uma lista
 */
public class Lista_Conferencia {
    private final ArrayList<Conferencia> l_conferencia;

    public Lista_Conferencia() {
        this.l_conferencia = new ArrayList<>();
    }
    
    public void setLista_Conferencia (Conferencia conferencia){
        this.l_conferencia.add(conferencia);
    }
    
    public List<Conferencia> getLista_Conferencia(){
        return this.l_conferencia;
    }
    public void removeConferencia(Conferencia conferencia){
        this.l_conferencia.remove(conferencia);
    }
    
}
