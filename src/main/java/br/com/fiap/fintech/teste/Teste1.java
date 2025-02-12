package br.com.fiap.fintech.teste;

import br.com.fiap.fintech.dao.ClienteDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;
import br.com.fiap.fintech.model.Cliente;
import br.com.fiap.fintech.model.Conta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Teste1 {
    public static void main(String[] args) {

        ClienteDao dao = DaoFactory.getClienteDao();

//        Conta conta = new Conta(101, "Corrente", 1000.00, LocalDate.of(2023, 6, 1), null, 0);
////        Cliente cliente = new Cliente(0, LocalDate.of(2024, 11, 05), conta, "Carlos", "carlao@gmail.com", "12345678", "4500000", LocalDate.of(2000, 02, 01));
//        try {
//            dao.cadastrarCliente(cliente);
//            System.out.println("cliente cadastrado.");
//        } catch (DBException e) {
//            e.printStackTrace();
//        }



    }
}

//(id_cliente, dt_de_cadastro, conta_id_conta, nome, email, cpf, rg, dt_nascimento