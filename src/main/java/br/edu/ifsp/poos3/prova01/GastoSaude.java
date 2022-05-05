package br.edu.ifsp.poos3.prova01;

public class GastoSaude extends Gasto{

    public static final double DEDUCAO_MAX_SAUDE = 1_500.00;
    public String registroConselho;

    public GastoSaude(int id, String descricao, String cnpj, double valor, String registroConselho) {
        super(id, descricao, cnpj, valor);
        this.registroConselho = registroConselho;
    }

    public String getRegistroConselho() {
        return registroConselho;
    }

    public void setRegistroConselho(String registroConselho) {
        this.registroConselho = registroConselho;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Registro no Conselho: %s ", registroConselho) + " | Tipo: Saúde";
    }
}
