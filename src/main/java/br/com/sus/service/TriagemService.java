package br.com.sus.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TriagemService {

    public String classificar(String sintomas, int idade) {
        sintomas = sintomas.toLowerCase();

        if (sintomas.contains("dor no peito") || sintomas.contains("falta de ar")) {
            return "VERMELHO";
        }

        if (sintomas.contains("desmaio") || sintomas.contains("convulsão")) {
            return "VERMELHO";
        }

        if (idade >= 60 && sintomas.contains("febre")) {
            return "AMARELO";
        }

        if (sintomas.contains("dor")) {
            return "AMARELO";
        }

        return "VERDE";
    }
}
