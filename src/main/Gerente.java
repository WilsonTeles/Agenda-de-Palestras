/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wilson 
 * Classe que gerencia a leitura, escrita e escolha das
 * conferencias do arquivo input
 */
public class Gerente {

    //definição dos horários por constantes para possíveis mudanças futuras
    //como personalizar os horarios das sessões.
    final int Sessao_Manha_Ini = 9;
    final int Sessao_Manha_Fim = 12;
    final int Sessao_Tarde_Ini = 1;
    final int Sessao_Tarde_Fim = 5;
    private int tempo_manha;
    private int tempo_tarde;

    public void OrganizaAgenda() throws IOException {
        String caminho = "./Data/input.txt";
        Lista_Conferencia l_conferencia;
        Leitor leitor = new Leitor();
        l_conferencia = leitor.abreArquivo(caminho);
        List<Conferencia> listaAux;
        List<Conferencia> conferencia = l_conferencia.getLista_Conferencia();
        int tamanho = conferencia.size();
        List<Integer> gerador;
        this.tempo_manha = (Sessao_Manha_Fim - Sessao_Manha_Ini) * 60;
        this.tempo_tarde = (Sessao_Tarde_Fim - Sessao_Tarde_Ini - 1) * 60;
        //cria uma lista com as conferencias para turno da manha
        listaAux = ordenaConferencia(conferencia, tempo_manha, "AM");
        Escritor escritor = new Escritor();
        escritor.escreveArquivo(listaAux, Sessao_Manha_Ini, "AM");
        // remove selecionados da lista completa de conferencia
        conferencia = deletaSelecionados(conferencia, listaAux);
        //cria uma lista com as conferencia para o turno da tarde
        listaAux = ordenaConferencia(conferencia, tempo_tarde, "PM");
        escritor.escreveArquivo(listaAux, Sessao_Tarde_Ini, "PM");

    }

    //metodo para randomizar a lista do arquivo input. gera-se uma lista de inteiros
    //do tamanho da lista do arquivo e mistura eles para pegar os objetos randomicamente
    private List<Integer> gerador(int tamanho) {
        List<Integer> gerador = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            gerador.add(i);
        }
        Collections.shuffle(gerador);
        return gerador;
    }

    //metodo para encaixar as conferencias nos horários sem gargalos através da
    //recursão. Gera-se uma ordem aleatória da lista de conferencias. Se não for
    //possível encaixa-las em horários sem intervalos, o proprio metodo se chama
    //até que o horário zere
    private List<Conferencia> ordenaConferencia(List<Conferencia> conferencia, int tempoOrdena, String turno) {
        int tempoLocal = tempoOrdena;
        List<Integer> gerador = gerador(conferencia.size());
        List<Conferencia> l_final = new ArrayList<>();
        List<Conferencia> sobras = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < gerador.size(); i++) {
            //confere se o tempo disponível é maior que a duração da conferencia
            if (tempoLocal >= conferencia.get(gerador.get(i)).getDuracao()) {
                tempoLocal = tempoLocal - conferencia.get(gerador.get(i)).getDuracao();
                l_final.add(conferencia.get(gerador.get(i)));

            } else {
                sobras.add(conferencia.get(gerador.get(i)));
            }
        }
        if (tempoLocal != 0) {
            return ordenaConferencia(conferencia, tempoOrdena, turno);
        }
        //caso seja o turno da tarde, uma conferencia qualquer que tenha sobrado
        //na seleção no loop acima é escolhida para o último horário.
        if ("PM".equals(turno)) {
            Collections.shuffle(sobras);
            l_final.add(sobras.get(0));
        }
        return l_final;
    }

    //metodo para deletar as conferencias escolhidas para a sessão da manhã da lista
    //geral de conferencias.
    private List<Conferencia> deletaSelecionados(List<Conferencia> conferencia, List<Conferencia> l_selecionados) {
        for (int i = 0; i < l_selecionados.size(); i++) {
            conferencia.remove(l_selecionados.get(i));
        }
        return conferencia;
    }

}
