package br.edu.ifsp.poos3.practical01;

public class Principal {
    public static void main(String[] args) {
        UserAccount isa = new UserAccount("Isabela", "isa@aluno.ifsp.edu.br" );
        UserAccount luiz = new UserAccount("Luiz", "luiz@aluno.ifsp.edu.br" );
        UserAccount maria = new UserAccount("Maria", "maria@aluno.ifsp.edu.br" );
        UserAccount ingrid = new UserAccount("Ingrid", "ingrid@aluno.ifsp.edu.br" );
        UserAccount jean = new UserAccount("Jean", "jean@aluno.ifsp.edu.br" );

        jean.acceptFollower(isa);
        jean.acceptFollower(luiz);
        jean.acceptFollower(maria);
        jean.acceptFollower(ingrid);

        jean.publish("Raça Curinthia");

        System.out.println("Jean Posts: ");
        System.out.println(jean.getMyPostAsString());

        System.out.println("Jean Followers: ");
        System.out.println(jean.getFollowersAsString());

        System.out.println("Jean Timeline: ");
        System.out.println(jean.getTimelineAsString());

        maria.acceptFollower(ingrid);
        maria.acceptFollower(isa);
        maria.acceptFollower(jean);

        maria.publish("Tell don't ask");
        maria.clapPost(0);
        maria.booPost(0);

        jean.blockFollower(maria);
        jean.publish("Vai IFSP!");

        System.out.println("Jean Posts: ");
        System.out.println(jean.getMyPostAsString());

        System.out.println("Jean Followers: ");
        System.out.println(jean.getFollowersAsString());

        System.out.println("Jean Timeline: ");
        System.out.println(jean.getTimelineAsString());

        System.out.println("Maria's Timeline: ");
        System.out.println(maria.getTimelineAsString());

        maria.publish("1");
        maria.publish("2");
        maria.publish("3");
        maria.publish("4");
        maria.publish("5");
        maria.publish("6");
        maria.publish("7");
        maria.publish("8");

        System.out.println("Isa's Timeline: ");
        System.out.println(isa.getTimelineAsString());

        System.out.println("Marias's posts  ");
        System.out.println(maria.getMyPostAsString());

        System.out.println("Marias's posts after removing index 0: ");
        maria.deletePost(0);
        System.out.println(maria.getMyPostAsString());

        System.out.println("Marias's posts after after removing index 4: ");
        maria.deletePost(4);
        System.out.println(maria.getMyPostAsString());

        System.out.println("Marias's posts after removing index 7: ");
        maria.deletePost(6);
        System.out.println(maria.getMyPostAsString());

        maria.booPost(5);
        System.out.println(maria.getMyPostAsString());

    }
}
