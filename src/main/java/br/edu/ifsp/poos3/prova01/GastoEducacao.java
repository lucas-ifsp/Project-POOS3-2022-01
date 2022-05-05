package br.edu.ifsp.poos3.prova01;

public class GastoEducacao extends Gasto{

    public static final double DEDUCAO_MAX_EDUCACAO = 2_000.00;

    public String nomeInstituicao;

    public GastoEducacao(int id, String descricao, String cnpj, double valor, String nomeInstituicao) {
        super(id, descricao, cnpj, valor);
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Instituição: %s ", nomeInstituicao) + " | Tipo: Educação";
    }
}
