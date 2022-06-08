package br.edu.ifsp.poos3.practical02;

import java.util.Collection;
import java.util.Scanner;

public class Principal {

    private final Scanner scanner;

    public static void main(String[] args) {
        Principal principal = new Principal();
        int opcao = -1;
        while (opcao != 0){
            opcao = principal.menu();
            switch (opcao){
                case 1 -> principal.adicionar();
                case 2 -> principal.editar();
                case 3 -> principal.remover();
                case 4 -> principal.listarFuncionario();
                case 5 -> principal.listarFuncionarios();
            }
        }
    }

    public Principal() {
        populate();
        this.scanner = new Scanner(System.in);
    }

    public int menu(){
        System.out.println("\n---- Menu ----");
        System.out.println("1. Adicionar ");
        System.out.println("2. Editar");
        System.out.println("3. Remover");
        System.out.println("4. Listar Funcionário");
        System.out.println("5. Listar Funcionários");
        System.out.println("0. Sair");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void adicionar(){
        System.out.print("\nCPF: ");
        final String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        final String nome = scanner.nextLine();
        System.out.print("Idade: ");
        final int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Sexo (true, false): ");
        final boolean sexo = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Valor vendido: ");
        final double valorVendido = Double.parseDouble(scanner.nextLine());
        System.out.print("CPF do Responsável: ");
        final String cpfResponsavel = scanner.nextLine();

        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario responsavel = dao.buscar(cpfResponsavel);

        if(responsavel instanceof Revendedor revendedor){ // instanceof pattern matching - Java 16+
            responsavel = new Consultor(revendedor);
        }

        final Revendedor revendedor = new Revendedor(cpf, nome, idade, sexo, valorVendido, (Consultor) responsavel);

        dao.salvar(revendedor);
        dao.atualizar(responsavel);
    }

    public void editar(){
        System.out.print("\nCPF: ");
        final String cpf = scanner.nextLine();

        FuncionarioDAO dao = new FuncionarioDAO();
        final Funcionario emEdicao = dao.buscar(cpf);

        if(emEdicao == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Nome: ");
        emEdicao.setNome(scanner.nextLine());
        System.out.print("Idade: ");
        emEdicao.setIdade(Integer.parseInt(scanner.nextLine()));
        System.out.print("Sexo (true, false): ");
        emEdicao.setSexo(Boolean.parseBoolean(scanner.nextLine()));
        System.out.print("Valor vendido: ");
        emEdicao.setValorVendido(Double.parseDouble(scanner.nextLine()));
        System.out.print("CPF do Responsável: ");
        final String cpfResponsavel = scanner.nextLine();

        final Funcionario potencialResponsavel = dao.buscar(cpfResponsavel);

        if(potencialResponsavel instanceof Revendedor) {
            System.out.println("Revendedor não pode ser responsável");
            return;
        }

        Consultor novoResponsavel = (Consultor) potencialResponsavel;
        Consultor antigoResponsavel = emEdicao.getResponsavel();
        emEdicao.setResponsavel(novoResponsavel);
        novoResponsavel.addSubordinado(emEdicao);
        antigoResponsavel.removeSubordinado(emEdicao);

        dao.atualizar(emEdicao);
    }

    public void remover(){
        FuncionarioDAO dao = new FuncionarioDAO();

        System.out.print("\nCPF: ");
        final String cpf = scanner.nextLine();
        final Funcionario funcionario = dao.buscar(cpf);

        if(funcionario == null){
            System.out.println("Funcionário não encontrado");
            return;
        }
        if(funcionario instanceof Consultor consultor){ // instanceof pattern matching - Java 16+
            consultor.promoveSubordinados();
        }
        final Consultor responsavel = funcionario.getResponsavel();
        if(responsavel != null) {
            responsavel.removeSubordinado(funcionario);
            dao.atualizar(responsavel);
        }
        dao.deletar(cpf);
    }

    public void listarFuncionario(){
        System.out.print("\nCPF: ");
        final String cpf = scanner.nextLine();
        FuncionarioDAO dao = new FuncionarioDAO();
        final Funcionario funcionario = dao.buscar(cpf);
        System.out.println(funcionario);
    }

    public void listarFuncionarios(){
        FuncionarioDAO dao = new FuncionarioDAO();
        final Collection<Funcionario> funcionarios = dao.buscarTodos();
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    private static void populate() {
        Consultor huffman = new Consultor("12312312312", "David A. Huffman", 74, false, 7000.00, null);
        Consultor ada = new Consultor("32132132131", "Augusta Ada Byron", 36, true, 3000.00, huffman);
        Consultor dijkstra = new Consultor("21321321313", "Edsger Dijkstra", 72, false, 1520.00, huffman);
        Consultor turing = new Consultor("45645645646", "Alan Mathison Turing", 41, false, 780.00, ada);
        Revendedor neumann = new Revendedor("65465465464", "John von Neumann", 53, false, 300.00, turing);
        Revendedor knuth = new Revendedor("90219021902", "Donald Ervin Knuth", 80, false, 432.00, turing);
        Revendedor hopper = new Revendedor("54654654654", "Grace Murray Hopper", 85, true, 432.00, dijkstra);

        FuncionarioDAO dao = new FuncionarioDAO();
        dao.salvar(huffman);
        dao.salvar(ada);
        dao.salvar(dijkstra);
        dao.salvar(turing);
        dao.salvar(neumann);
        dao.salvar(knuth);
        dao.salvar(hopper);
    }
}
