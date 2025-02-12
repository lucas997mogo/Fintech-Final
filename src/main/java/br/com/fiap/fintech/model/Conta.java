package br.com.fiap.fintech.model;

import java.sql.Date;
import java.time.LocalDate;

public class Conta {

    private int idConta;
    private String tipoConta;
    private double saldo;
    private LocalDate dtDeAbertura;
    private LocalDate dtDeFechamento;
    private Cliente idCliente;

    public Conta() {
    }

    public Conta(int idConta, String tipoConta, double saldo, LocalDate dtDeAbertura, LocalDate dtDeFechamento, int clienteIdCliente) {
        this.idConta = idConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dtDeAbertura = dtDeAbertura;
        this.dtDeFechamento = dtDeFechamento;
        this.idCliente = idCliente ;
    }

    public Conta(String tipoConta, double saldo, LocalDate dtDeAbertura, LocalDate dtDeFechamento, int clienteIdCliente) {
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dtDeAbertura = dtDeAbertura;
        this.dtDeFechamento = dtDeFechamento;
        this.idCliente = idCliente;
    }

    public Conta(String tipoConta, double saldo, LocalDate dtDeAbertura, int clienteIdCliente) {
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dtDeAbertura = dtDeAbertura;
        this.idCliente = idCliente;
    }

    public Conta(int idConta, String tipoConta, int saldo, int clienteidCliente) {
        this.idConta = idConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public Conta(int idConta, String tipoConta, int saldo, Date dtDeAbertura, int idCliente) {
    }

    public Conta(int idConta, String tipoConta, double saldo, LocalDate dtDeAbertura, int clienteId) {
        this.idConta = idConta;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dtDeAbertura = dtDeAbertura;
        this.idCliente = idCliente;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDtDeAbertura() {
        return dtDeAbertura;
    }

    public void setDtDeAbertura(LocalDate dtDeAbertura) {
        this.dtDeAbertura = dtDeAbertura;
    }

    public LocalDate getDtDeFechamento() {
        return dtDeFechamento;
    }

    public void setDtDeFechamento(LocalDate dtDeFechamento) {
        this.dtDeFechamento = dtDeFechamento;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public void setClienteId(int idCliente) {

    }
}
