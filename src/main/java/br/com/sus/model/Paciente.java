package br.com.sus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Paciente extends PanacheEntity {

    public String nome;
    public String cpf;
    public int idade;
    public String doencasPreExistentes;
}
