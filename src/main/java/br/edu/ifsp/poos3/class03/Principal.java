package br.edu.ifsp.poos3.class03;

public class Principal {

    public static void main(String[] args) {
        //NomeDaClasse nomeDaVariavel = new NomeDoConstrutor(Parametros);
        Personagem personagem = new Personagem("Homer Simpson", 40);
        personagem.setIdade(-90);
        System.out.println(personagem.getNome());
        System.out.println(personagem.getIdade());
        System.out.println(personagem.getDublador());

        Dublador dublador = new Dublador("Carlos A. Vasconcellos", 123);
        System.out.println(dublador.nome);
        System.out.println(dublador.registroProfissional);
        System.out.println(dublador.personagem);

        personagem.setDublador(dublador);
        dublador.setPersonagem(personagem);

        System.out.println(personagem.getDublador().nome);
        dublador.nome = "Jão";
        System.out.println(personagem.getDublador().nome);

        System.out.println(dublador.personagem.getNome());

        Personagem personagem2 = new Personagem("Teste", 0);

        personagem2.shared = 1;
        System.out.println(personagem2.shared);
        System.out.println(personagem.shared);
        personagem.shared = 10;
        System.out.println(personagem2.shared);

        Personagem.shared = 1000;
        System.out.println(personagem2.shared);

       // personagem.sharedButConstant = 1000;
        System.out.println(Personagem.sharedButConstant);
    }

}
