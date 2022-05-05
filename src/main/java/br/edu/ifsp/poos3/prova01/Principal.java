package br.edu.ifsp.poos3.prova01;

import java.util.List;
import java.util.Scanner;

public class Principal {

    public static final int SAUDE = 1;
    public static final int EDUCACAO = 2;

    private final DeclaracaoDAO dao;
    private final Scanner scanner;
    private int idSequence = 0;

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.gerenciar();
    }

    public Principal() {
        this.dao = new DeclaracaoDAO();
        this.scanner = new Scanner(System.in);
    }

    public void gerenciar() {
        int opcao = -SAUDE;
        while (opcao != 0) {
            opcao = menu();
            switch (opcao) {
                case 1 -> criarDeclaracoes();
                case 2 -> editarDeclaracoes();
                case 3 -> removerDeclaracoes();
                case 4 -> cadastrarGasto();
                case 5 -> editarGasto();
                case 6 -> removerGasto();
                case 7 -> exibirDeclaracoes();
            }
        }
    }

    private int menu() {
        System.out.println("1. Criar Declarações");
        System.out.println("2. Editar Declarações");
        System.out.println("3. Remover Declarações");
        System.out.println("4. Cadastrar Gasto Dedutível");
        System.out.println("5. Editar Gasto Dedutível");
        System.out.println("6. Remover Gasto Dedutível");
        System.out.println("7. Exibir declarações");
        System.out.println("0. Sair");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void criarDeclaracoes() {
        if (dao.buscarTodos().size() > 0) {
            System.out.println("Declarações já cadastradas!");
            return;
        }

        System.out.print("Ganho tributável: ");
        double ganho = Double.parseDouble(scanner.nextLine());
        System.out.print("Valor pago na fonte: ");
        double pago = Double.parseDouble(scanner.nextLine());

        Declaracao simplificada = new DeclaracaoSimplificada(1, ganho, pago);
        Declaracao completa = new DeclaracaoCompleta(2, ganho, pago);
        dao.salvar(simplificada);
        dao.salvar(completa);
    }

    private void editarDeclaracoes() {
        final List<Declaracao> declaracoes = dao.buscarTodos();
        if (declaracoes.isEmpty()) {
            System.out.println("Não há declarações para editar");
            return;
        }

        System.out.print("Ganho tributável: ");
        double ganho = Double.parseDouble(scanner.nextLine());
        System.out.print("Valor pago na fonte: ");
        double pago = Double.parseDouble(scanner.nextLine());

        declaracoes.forEach(declaracao -> {
            declaracao.setGanhoTributavel(ganho);
            declaracao.setValorPago(pago);
            dao.atualizar(declaracao);
        });
    }

    private void removerDeclaracoes() {
        final List<Declaracao> declaracoes = dao.buscarTodos();
        if (declaracoes.isEmpty()) {
            System.out.println("Não há declarações para remover");
            return;
        }
        dao.remover(1);
        dao.remover(2);
    }

    private void cadastrarGasto() {
        final DeclaracaoCompleta declaracao = obterDeclaracaoCompleta();
        if (declaracao == null) {
            System.out.println("Antes de cadastrar gastos dedutíveis crie a declaração.");
            return;
        }
        System.out.print("[1] Saúde ou [2] Educação: ");
        final int tipo = Integer.parseInt(scanner.nextLine());
        final Gasto gasto;
        try {
            gasto = lerGastoDoConsole(idSequence, tipo);
            declaracao.addGasto(gasto);
            dao.atualizar(declaracao);
            idSequence++;
        } catch (GastoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private DeclaracaoCompleta obterDeclaracaoCompleta(){
        return (DeclaracaoCompleta) dao.buscarTodos().stream()
                .filter(d -> d instanceof DeclaracaoCompleta)
                .findFirst()
                .orElse(null);
    }

    public Gasto lerGastoDoConsole(int id, int tipo) throws GastoInvalidoException {
        if(tipo != SAUDE && tipo != EDUCACAO)
            throw new GastoInvalidoException("Código de gasto inválido: " + tipo);

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        if (tipo == SAUDE) {
            System.out.print("Registro no Conselho: ");
            String registro = scanner.nextLine();
            return new GastoSaude(id, descricao, cnpj, valor, registro);
        }
        System.out.print("Instituição: ");
        String instituicao = scanner.nextLine();
        return new GastoEducacao(id, descricao, cnpj, valor, instituicao);
    }

    private void editarGasto() {
        final DeclaracaoCompleta declaracao = obterDeclaracaoCompleta();
        if (declaracao == null) {
            System.out.println("Antes de editar gastos dedutíveis crie a declaração.");
            return;
        }

        final Gasto gasto = obterGasto(declaracao);
        if(gasto == null){
            System.out.println("Gasto não encontrado.");
            return;
        }
        final int tipo = gasto instanceof GastoSaude ? SAUDE : EDUCACAO;
        try {
            Gasto novoGasto = lerGastoDoConsole(gasto.getId(), tipo);
            declaracao.addGasto(novoGasto);
            dao.atualizar(declaracao);
        } catch (GastoInvalidoException e) {
            System.out.println(e.getMessage());
        }

    }

    private Gasto obterGasto(DeclaracaoCompleta declaracao){
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        return declaracao.getGasto(id).orElse(null);
    }

    private void removerGasto() {
        final DeclaracaoCompleta declaracao = obterDeclaracaoCompleta();
        if (declaracao == null) {
            System.out.println("Antes de remover gastos dedutíveis crie a declaração.");
            return;
        }

        final Gasto gasto = obterGasto(declaracao);
        if(gasto == null){
            System.out.println("Gasto não encontrado.");
            return;
        }

        declaracao.removeGasto(gasto);
        dao.atualizar(declaracao);
    }

    private void exibirDeclaracoes() {
        dao.buscarTodos().forEach(System.out::println);
    }
}
