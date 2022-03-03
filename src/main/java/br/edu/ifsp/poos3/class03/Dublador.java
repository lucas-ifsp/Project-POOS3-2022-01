package br.edu.ifsp.poos3.class03;

public class Dublador {
    public String nome;
    public int registroProfissional;
    public Personagem personagem;

    public Dublador(String nome, int registroProfissional) {
        this.nome = nome;
        this.registroProfissional = registroProfissional;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

}
