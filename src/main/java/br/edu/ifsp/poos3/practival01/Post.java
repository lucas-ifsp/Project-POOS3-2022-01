package br.edu.ifsp.poos3.practival01;

import java.time.LocalDateTime;

public class Post {
    private String quote;
    private LocalDateTime timestap;
    private int claps;
    private int boos;
    private UserAccount user;

    public Post(UserAccount user, String quote) {
        this.user = user;
        this.quote = quote;
        this.timestap = LocalDateTime.now();
    }

    public String getPostAsString(){
        return "[" + timestap + "] " + user.getName() + " says \"" + quote + "\" | Claps: " + claps + " | Boos: " + boos;
    }

    public void clap(){
        claps++;
    }

    public void boo(){
        boos++;
    }
}
