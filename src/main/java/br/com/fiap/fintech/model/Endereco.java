package br.com.fiap.fintech.model;

public class Endereco {

    private int idEndereco;
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String logradouro;
    private Cliente idCliente;

    public Endereco() {
    }

    public Endereco(int idEndereco, String pais, String estado, String cidade, String cep, String logradouro, int clienteIdCliente) {
        this.idEndereco = idEndereco;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.logradouro = logradouro;
        this.idCliente = idCliente;
    }

    public Endereco(String pais, String estado, String cidade, String cep, String logradouro, int clienteIdCliente) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.logradouro = logradouro;
        this.idCliente = idCliente;
    }

    public Endereco(int idEndereco, String pais, String estado, String cidade, String cep, String logradouro) {
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
}
