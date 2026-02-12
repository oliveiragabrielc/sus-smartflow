package br.com.sus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Triagem extends PanacheEntity {

    @ManyToOne
    public Paciente paciente;

    public String sintomas;
    public String classificacao; // VERDE, AMARELO, VERMELHO
    public LocalDateTime dataHora;
}
