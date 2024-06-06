/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SegurancaResidencial;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SistemaPrincipal {
    private static SistemaSeguranca sistemaSeguranca;
    private static ArrayList<Proprietario> p = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Proprietario proprietario = new Proprietario("Fenias", "846664928", "feniasmanhenge@gmail.com", "11111");
        Proprietario proprietario1 = new Proprietario("Gleny", "833647545", "glenymorais@gmail.com", "22222");
        Proprietario proprietario2 = new Proprietario("Tarcisio", "844725258", "tarcisiobotao@gmail.com", "33333");
        Proprietario proprietario3 = new Proprietario("Larsson", "843691537", "larssonsitoe@gmail.com", "44444");
        
        p.addAll(Arrays.asList(proprietario, proprietario1, proprietario2, proprietario3));
        
//        for (int i = 0; i < p.size(); i++) {
//            Proprietario get = p.get(i);
//            System.out.println(get.getNome());
//        }
        
        sistemaSeguranca = new SistemaSeguranca(p);

        carregarDados();

        if (!login(scanner)) {
            System.out.println("Autenticacao falhou, encerrando o programa!");
            return;
        }

        int opcao;
        do {
            System.out.println("\n1. Adicionar Fechadura");
            System.out.println("2. Registrar Tentativa de Acesso");
            System.out.println("3. Exibir Relatorio de Tentativas");
            System.out.println("4. Adicionar Usuario");
            System.out.println("5. Remover Usuario");
            System.out.println("6. Listar Usuarios");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    adicionarFechadura(scanner);
                    break;
                case 2:
                    registrarTentativa(scanner);
                    break;
                case 3:
                    exibirRelatorioTentativas();
                    break;
                case 4:
                    adicionarUsuario(scanner);
                    break;
                case 5:
                    removerUsuario(scanner);
                    break;
                case 6:
                    listarUsuarios();
                    break;
                case 7:
                    salvarDados();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
            }
        } while (opcao != 7);
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Nome do proprietario: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Codigo de administrador: ");
        String adminCode = scanner.nextLine().trim();

        return sistemaSeguranca.autenticarProprietario(nome, adminCode);
    }

    private static void adicionarFechadura(Scanner scanner) {
        System.out.print("ID da fechadura: ");
        String id = scanner.nextLine();
        System.out.print("Localizacao: ");
        String localizacao = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        Fechadura fechadura = new Fechadura(id, localizacao, pin);
        sistemaSeguranca.adicionarFechadura(fechadura);

        System.out.println("Fechadura adicionada com sucesso!");
    }

    private static void registrarTentativa(Scanner scanner) {
        System.out.print("ID da fechadura: ");
        String idFechadura = scanner.nextLine();
        System.out.print("Tentativa bem-sucedida? (true/false): ");
        boolean sucesso = scanner.nextBoolean();
        scanner.nextLine();  

        sistemaSeguranca.registrarTentativa(idFechadura, sucesso);

        System.out.println("Tentativa registrada!");
    }

    private static void exibirRelatorioTentativas() {
        System.out.println("Relatorio de Tentativas de Acesso:");
        for (Log log : sistemaSeguranca.getLogTentativas()) {
            System.out.println("Data/Hora: " + log.getDataHora() + ", Fechadura ID: " + log.getIdFechadura() + ", Sucesso: " + log.getSucesso());
        }
    }

    private static void adicionarUsuario(Scanner scanner) {
        System.out.print("Nome do usuario: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone do usuario: ");
        String telefone = scanner.nextLine();
        System.out.print("Email do usuario: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, telefone, email);
        sistemaSeguranca.adicionarUsuario(usuario);

        System.out.println("Usuario adicionado com sucesso!");
    }

    private static void removerUsuario(Scanner scanner) {
        System.out.print("Nome do usuario a ser removido: ");
        String nome = scanner.nextLine();

        sistemaSeguranca.removerUsuario(nome);

        System.out.println("Usuario removido com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("Lista de Usuarios:");
        for (Usuario usuario : sistemaSeguranca.getUsuarios()) {
            System.out.println("Nome: " + usuario.getNome() + ", Telefone: " + usuario.getTelefone() + ", Email: " + usuario.getEmail());
        }
    }

    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sistemaSeguranca.dat"))) {
            oos.writeObject(sistemaSeguranca);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sistemaSeguranca.dat"))) {
            sistemaSeguranca = (SistemaSeguranca) ois.readObject();
            System.out.println("Dados carregados com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum dado salvo encontrado, iniciando novo sistema.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}

