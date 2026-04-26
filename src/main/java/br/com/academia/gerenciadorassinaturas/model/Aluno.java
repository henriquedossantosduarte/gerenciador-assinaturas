package br.com.academia.gerenciadorassinaturas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ← @Id e @GeneratedValue pertencem aqui

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Pagamento> historicoPagamentos = new ArrayList<>();  // ← @OneToMany pertence aqui

    private String nome;
    private int idade;
    private String endereco;
    private int numero;
    private String plano;
    private LocalDate dataInicio;
    private int diaVencimento;
    private LocalDate dataUltimoPagamento;

    public Aluno(){

    }

    public Aluno(String nome, int idade, String endereco, int numero, String plano, LocalDate dataInicio, int diaVencimento, LocalDate dataUltimoPagamento){
        this.nome = nome;
        this.idade = idade;
        this.plano = plano;
        this.dataInicio  = dataInicio;
        this.diaVencimento= diaVencimento;
        this.endereco = endereco;
        this.numero = numero;
        this.dataUltimoPagamento = dataUltimoPagamento;

    }

    public List<Pagamento> getHistoricoPagamentos() {
        return historicoPagamentos;
    }

    public void setHistoricoPagamentos(List<Pagamento> historicoPagamentos) {
        this.historicoPagamentos = historicoPagamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;

    }

    public LocalDate getDataUltimoPagamento() {
        return dataUltimoPagamento;
    }

    public void setDataUltimoPagamento(LocalDate dataUltimoPagamento) {
        this.dataUltimoPagamento = dataUltimoPagamento;
    }

    public boolean isVenceHoje(){
        int diaAtual= java.time.LocalDate.now().getDayOfMonth();
        return  this.diaVencimento==diaAtual;
    }

    public boolean isAtrasado() {
        LocalDate hoje = LocalDate.now();

        // Se nunca pagou, já está atrasado
        if (dataUltimoPagamento == null) {
            return hoje.getDayOfMonth() > this.diaVencimento;
        }


        // Se já pagou esse mês, não está atrasado
        if (dataUltimoPagamento.getMonth() == hoje.getMonth() &&
                dataUltimoPagamento.getYear() == hoje.getYear()) {
            return false;
        }

        // Se não pagou esse mês, verifica se o vencimento já passou
        return hoje.getDayOfMonth() > this.diaVencimento;
    }
}
