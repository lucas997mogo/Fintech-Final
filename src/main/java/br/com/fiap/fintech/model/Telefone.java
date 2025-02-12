package br.com.fiap.fintech.model;

public class Telefone {

    private int idTelefone;
    private String ddi;
    private String ddd;
    private String numero;
    private String tipo;
    private Cliente idCliente;

    public Telefone() {
    }

    public Telefone(int idTelefone, String ddi, String ddd, String numero, String tipo, int clienteIdCliente) {
        this.idTelefone = idTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        this.idCliente = idCliente;
    }

    public Telefone(String ddi, String ddd, String numero, String tipo, int clienteIdCliente) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        this.idCliente = idCliente;
    }

    public Telefone(int idTelefone, String ddi, String ddd, String numero, String tipo) {
        this.idTelefone = idTelefone;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
}
