package com.example.app;

import com.example.dao.ClienteDAO;
import com.example.dao.PedidoDAO;
import com.example.model.Cliente;
import com.example.model.Pedido;
import com.example.service.ViaCepService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Env env = new Env();
        EntityManagerFactory emf = JPAUtil.getFactory(env);
        ClienteDAO clienteDAO = new ClienteDAO(emf);
        PedidoDAO pedidoDAO = new PedidoDAO(emf);
        ViaCepService via = new ViaCepService();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--- Menu ---");
            System.out.println("1. Criar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Deletar Cliente");
            System.out.println("5. Criar Pedido");
            System.out.println("6. Listar Pedidos");
            System.out.println("7. Atualizar Pedido");
            System.out.println("8. Deletar Pedido");
            System.out.println("9. Sair");
            System.out.print("Escolha: ");
            String opt = sc.nextLine();
            try {
                switch (opt) {
                    case "1":
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("CEP (apenas numeros): ");
                        String cep = sc.nextLine();
                        String endereco = via.fetchAddressByCep(cep);
                        if (endereco == null) endereco = "";
                        Cliente c = new Cliente(nome, email, cep, endereco);
                        clienteDAO.save(c);
                        System.out.println("Cliente criado com id: " + c.getId());
                        break;
                    case "2":
                        List<Cliente> clientes = clienteDAO.findAll();
                        clientes.forEach(x -> System.out.println(x.getId() + " - " + x.getNome() + " (" + x.getEmail() + ") CEP:" + x.getCep() + " End:" + x.getEndereco()));
                        break;
                    case "3":
                        System.out.print("Id do cliente: ");
                        Long idUp = Long.parseLong(sc.nextLine());
                        Cliente toUpdate = clienteDAO.findById(idUp);
                        if (toUpdate == null) { System.out.println("Cliente nao encontrado"); break; }
                        System.out.print("Novo nome (enter para manter): ");
                        String nn = sc.nextLine(); if (!nn.isBlank()) toUpdate.setNome(nn);
                        System.out.print("Novo email (enter para manter): ");
                        String ne = sc.nextLine(); if (!ne.isBlank()) toUpdate.setEmail(ne);
                        clienteDAO.update(toUpdate);
                        System.out.println("Atualizado.");
                        break;
                    case "4":
                        System.out.print("Id do cliente a deletar: ");
                        Long idDel = Long.parseLong(sc.nextLine());
                        clienteDAO.delete(idDel);
                        System.out.println("Deletado (se existia).");
                        break;
                    case "5":
                        System.out.print("Id do cliente para o pedido: ");
                        Long cid = Long.parseLong(sc.nextLine());
                        Cliente cliente = clienteDAO.findById(cid);
                        if (cliente == null) { System.out.println("Cliente nao encontrado"); break; }
                        System.out.print("Descricao: ");
                        String desc = sc.nextLine();
                        System.out.print("Valor: ");
                        Double val = Double.parseDouble(sc.nextLine());
                        Pedido p = new Pedido(desc, val, cliente);
                        pedidoDAO.save(p);
                        System.out.println("Pedido criado id: " + p.getId());
                        break;
                    case "6":
                        List<Pedido> pedidos = pedidoDAO.findAll();
                        pedidos.forEach(px -> System.out.println(px.getId() + " - " + px.getDescricao() + " R$" + px.getValor() + " Cliente:" + (px.getCliente()!=null?px.getCliente().getNome():"-")));
                        break;
                    case "7":
                        System.out.print("Id do pedido: ");
                        Long pid = Long.parseLong(sc.nextLine());
                        Pedido pu = pedidoDAO.findById(pid);
                        if (pu == null) { System.out.println("Pedido nao encontrado"); break; }
                        System.out.print("Nova descricao (enter para manter): ");
                        String nd = sc.nextLine(); if (!nd.isBlank()) pu.setDescricao(nd);
                        System.out.print("Novo valor (enter para manter): ");
                        String nv = sc.nextLine(); if (!nv.isBlank()) pu.setValor(Double.parseDouble(nv));
                        pedidoDAO.update(pu);
                        System.out.println("Pedido atualizado.");
                        break;
                    case "8":
                        System.out.print("Id do pedido a deletar: ");
                        Long pd = Long.parseLong(sc.nextLine());
                        pedidoDAO.delete(pd);
                        System.out.println("Deletado (se existia).");
                        break;
                    case "9":
                        System.out.println("Saindo...");
                        emf.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
