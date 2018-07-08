/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Wilson
 * classe para escrita no arquivo de saída
 */
public class Escritor {

    private final File arq;
    private final String path = "./Data/output.txt";

    public Escritor() {
        this.arq = new File(this.path);
    }

    public void escreveArquivo(List<Conferencia> conferencia, int tempo, String turno) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(this.path, true))) {
            //cria um string no formato hh:mm para escrita no arquivo
            String horario = Integer.toString(tempo);
            horario = horario.concat(":00");
            if(horario.length() == 4){
                horario = "0".concat(horario);
            }
            for (int i = 0; i < conferencia.size(); i++) {
                escritor.append(horario + turno + " " + conferencia.get(i).getTitulo() + " " + conferencia.get(i).getDuracao() + " min");
                escritor.newLine();
                
                //conversão das horas pra minutos para soma do horario com as durações dos eventos.
                String sHora = horario.substring(0, 2);
                int hora = Integer.parseInt(sHora);
                String sMinuto = horario.substring(horario.length()-2, horario.length());
                int minuto = Integer.parseInt(sMinuto);
                int aux = hora * 60 + minuto + conferencia.get(i).getDuracao();
                hora = aux / 60;
                minuto = aux % 60;
                //conversão da hora de volta pro formato hh:mm
                if (hora < 10){
                    horario = "0".concat(Integer.toString(hora));
                }else{
                    horario = Integer.toString(hora);
                }
                if (minuto < 10){
                    horario = horario.concat(":").concat("0").concat(Integer.toString(minuto));
                }else{
                    horario = horario.concat(":").concat(Integer.toString(minuto));
                }
            }
            //adição do horário de almoço
            if ("AM".equals(turno)){
                escritor.append(horario + turno + " Lunch");
                escritor.newLine();
            } 
            //ou do horário do Networking
            else if("PM".equals(turno)){
                escritor.append(horario + turno + " Networking Event");
                escritor.newLine();
                escritor.newLine();
            }
            escritor.close();
        }
    }

}
