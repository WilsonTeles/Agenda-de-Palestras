/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Wilson classe para leitura do arquivo input e alocação das linhas
 * para uma lista
 */
public class Leitor {

    public Lista_Conferencia abreArquivo(String caminho) throws IOException {
        Lista_Conferencia l_conferencia;
        try (BufferedReader buffer = new BufferedReader(new FileReader(caminho))) {
            String linha = "", duracao = "", titulo = "";
            Conferencia conferencia;
            l_conferencia = new Lista_Conferencia();
            while (true) {
                linha = buffer.readLine();
                if (linha == null) {
                    break;
                }
                if (linha.contains("lightning")) {
                    titulo = linha.substring(0, linha.indexOf("lightning"));
                    conferencia = new Conferencia(titulo, 5);
                    l_conferencia.setLista_Conferencia(conferencia);

                } else {
                    for (int i = 0; i < linha.length(); i++) {

                        if ((linha.charAt(i) >= '0') && (linha.charAt(i) <= '9')) {
                            titulo = linha.substring(0, i - 1);
                            duracao = linha.substring(i, linha.length() - 3);
                            conferencia = new Conferencia(titulo, Integer.parseInt(duracao));
                            l_conferencia.setLista_Conferencia(conferencia);
                            break;
                        }
                    }
                }

            }
        }
        return l_conferencia;
    }

}
