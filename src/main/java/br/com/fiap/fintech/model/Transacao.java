package br.com.fiap.fintech.model;

import java.sql.Date;
import java.time.LocalDate;

public class Transacao {

    private int idTransacao;
    private String tipoTransacao;
    private double valor;
    private LocalDate dtTransacao;
    private Conta idConta;

    public Transacao() {
    }

    public Transacao(int idTransacao, String tipoTransacao, double valor, LocalDate dtTransacao, int contaIdConta) {
        this.idTransacao = idTransacao;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dtTransacao = dtTransacao;
        this.idConta = idConta;
    }

    public Transacao(String tipoTransacao, double valor, LocalDate dtTransacao, int contaIdConta) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dtTransacao = dtTransacao;
        this.idConta = idConta;
    }

    public Transacao(int idTransacao, String tipoTransacao, int valor, String email, Date dtTransacao, int idConta) {
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(LocalDate dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public Conta getIdConta() {
        return idConta;
    }

    public void setIdConta(Conta idConta) {
        this.idConta = idConta;
    }
}
