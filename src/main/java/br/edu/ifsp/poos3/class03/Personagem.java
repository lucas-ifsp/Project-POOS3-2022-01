package br.edu.ifsp.poos3.class03;

public class Personagem {
    private String nome;
    private int idade;
    private Dublador dublador;

    public static int shared;
    public static final int sharedButConstant  = 10;

    /*public Personagem(){

    }*/

    public Personagem(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = Math.max(idade, 0);
    }

    public Dublador getDublador() {
        return dublador;
    }

    public void setDublador(Dublador dublador) {
        this.dublador = dublador;
    }
}
