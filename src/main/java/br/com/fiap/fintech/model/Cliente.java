package br.com.fiap.fintech.model;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private String dtDeCadastro;
    private String nome;
    private String email;
    private String cpf;
    private String rg;
    private LocalDate dtNascimento;

    public Cliente(int idCliente, String nome, int cpf, int idConta) {
    }

    public Cliente(String nome, String email, String cpf, String rg, LocalDate dtNascimento, int idCliente) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.dtNascimento = dtNascimento;
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String dtDeCadastro, String nome, String email, String cpf, String rg, LocalDate dtNascimento) {
        this.idCliente = idCliente;
        this.dtDeCadastro = dtDeCadastro;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.dtNascimento = dtNascimento;
    }

    public Cliente(int idCliente, String nome, String cpf, String email, LocalDate dtNascimento) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dtNascimento = dtNascimento;
    }

    public Cliente(int idCliente, String nome, String cpf, String email, Date dtNascimento, int idConta) {
    }

    public Cliente(int idCliente, LocalDate dtDeCadastro, String nome, String email, String cpf, String rg, LocalDate dtNascimento) {
    }

    public Cliente(int idCliente, String nome, String cpf, String email, Date dtNascimento) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dtNascimento = dtNascimento.toLocalDate();
    }

    public Cliente(int idCliente, String dtDeCadastro, String nome, String cpf, String email, Date dtNascimento) {
        this.idCliente = idCliente;
        this.dtDeCadastro = dtDeCadastro;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dtNascimento = dtNascimento.toLocalDate();
    }

    public Cliente(int idCliente, String dtDeCadastro, String nome, String cpf, String rg, String email, Date dtNascimento) {
        this.idCliente = idCliente;
        this.dtDeCadastro = dtDeCadastro;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.dtNascimento = dtNascimento.toLocalDate();
    }

    public Cliente(int idCliente, String nome, String email, String cpf, String rg, LocalDate dtNascimento) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.dtNascimento = dtNascimento;
    }

    public Cliente(int idCliente, String nome, String cpf) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDtDeCadastro() {
        return dtDeCadastro;
    }

    public void setDtDeCadastro(String dtDeCadastro) {
        this.dtDeCadastro = dtDeCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}