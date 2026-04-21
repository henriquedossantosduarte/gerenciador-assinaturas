package br.com.academia.gerenciadorassinaturas.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private int idade;
    private String endereco;
    private int numero;
    private String plano;
    private LocalDate dataInicio;
    private int diaVencimento;

    public Aluno(){

    }

    public Aluno(String nome, int idade, String endereco, int numero, String plano, LocalDate dataInicio, int diaVencimento){
        this.nome = nome;
        this.idade = idade;
        this.plano = plano;
        this.dataInicio  = dataInicio;
        this.diaVencimento= diaVencimento;
        this.endereco = endereco;
        this.numero = numero;

    }





}
