package com.taller4.services;
import com.taller4.models.Candidato;
import com.taller4.models.ResultadoEleccion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
public class CalculadorDeVotos {

    public boolean esVotoValido(int[] voto, int n) {
        if (voto.length != n) {
            return false;
        }
        Set<Integer> revisados = new HashSet<>();
        for (int numero : voto) {
            if (numero < 1 || numero > n) {
                return false;
            }
            if (!revisados.add(numero)) {
                return false;
            }
        }
        return true;
    }

    public ResultadoEleccion calcularMayoria(List<int[]> votos, int n) {
        int nulos = 0;
        int validos = 0;
        ArrayList<Candidato> listaCandidatos = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            listaCandidatos.add(new Candidato(i));
        }
        for (int[] voto : votos) {
            if (esVotoValido(voto, n)) {
                validos++;

                int idCandidatoElegido = voto[0];
                listaCandidatos.get(idCandidatoElegido - 1).añadirPuntos(1);
            } else {
                nulos++;
            }
        }
        int maxPuntos = 0;
        for (Candidato c : listaCandidatos) {
            if (c.getPuntosAgregados() > maxPuntos) {
                maxPuntos = c.getPuntosAgregados();
            }
        }

        List<Integer> ganadores = new ArrayList<>();
        for (Candidato c : listaCandidatos) {
            if (c.getPuntosAgregados() == maxPuntos) {
                ganadores.add(c.getId());
            }
        }
        return new ResultadoEleccion("Mayoría", votos.size(), validos, nulos, listaCandidatos, ganadores);
    }
    public ResultadoEleccion calcularBorda(List<int[]> votos, int n) {
        int nulos = 0;
        int validos = 0;
        ArrayList<Candidato> listaCandidatos = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            listaCandidatos.add(new Candidato(i));
        }
        for (int[] voto : votos) {
            if (esVotoValido(voto, n)) {
                validos++;

                for (int i = 0; i < voto.length; i++) {
                    int idCandidato = voto[i];
                    int puntos = (n - 1) - i; // Posición 0 recibe n-1 puntos, etc.
                    listaCandidatos.get(idCandidato - 1).añadirPuntos(puntos);
                }
            } else {
                nulos++;
            }
        }
        int maxPuntos = 0;
        for (Candidato c : listaCandidatos) {
            if (c.getPuntosAgregados() > maxPuntos) {
                maxPuntos = c.getPuntosAgregados();
            }
        }
        List<Integer> ganadores = new ArrayList<>();
        for (Candidato c : listaCandidatos) {
            if (c.getPuntosAgregados() == maxPuntos) {
                ganadores.add(c.getId()); // Aquí extraemos solo el ID (el número) del candidato
            }
        }

        return new ResultadoEleccion("Método de Borda", votos.size(), validos, nulos, listaCandidatos,ganadores);
    }
}