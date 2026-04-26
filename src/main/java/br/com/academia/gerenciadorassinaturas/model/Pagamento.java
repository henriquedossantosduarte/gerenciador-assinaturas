package br.com.academia.gerenciadorassinaturas.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataConfirmacao;  // era dateConfirmacao
    private String mesReferencia;       // era Mesreferencial

    @ManyToOne
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public LocalDate getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDate dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}