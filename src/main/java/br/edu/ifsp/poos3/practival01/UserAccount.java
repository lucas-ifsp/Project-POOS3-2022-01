package br.edu.ifsp.poos3.practival01;

public class UserAccount {
    public static final int MAX_POSTS = 100;
    public static final int MAX_FOLLOWERS = 100;
    public static final int MAX_POSTS_TIMELINE = 10;

    private String name;
    private String email;

    private UserAccount[] followers;
    private int numberOfFollowers;

    private Post[] posts;
    private int numberOfPosts;

    private Post[] timeline;
    private int numberOfPostInTimeline;

    public UserAccount(String name, String email) {
        this.name = name;
        this.email = email;
        followers = new UserAccount[MAX_FOLLOWERS];
        posts = new Post[MAX_POSTS];
        timeline = new Post[MAX_POSTS_TIMELINE];
    }

    public void publish(String quote){
        final Post post = new Post(this, quote);
        posts[numberOfPosts++] = post;
        for (UserAccount follower : followers) {
            if (follower != null) {
                follower.updateTimeline(post);
            }
        }
    }

    public void updateTimeline(Post post){
        timeline[numberOfPostInTimeline % MAX_POSTS_TIMELINE] = post;
        numberOfPostInTimeline ++;
    }

    public void clapPost(int postIndex){
        if(postIndex < 0 || postIndex >= MAX_POSTS) return;
        if(posts[postIndex] == null) return;
        posts[postIndex].clap();
    }

    public void booPost(int postIndex){
        if(postIndex < 0 || postIndex >= MAX_POSTS) return;
        if(posts[postIndex] == null) return;
        posts[postIndex].boo();
    }

    public void acceptFollower(UserAccount follower){
        if (follower == null) return;
        if(numberOfFollowers == MAX_FOLLOWERS) return;
        followers[numberOfFollowers++] = follower;
    }

    public void blockFollower(UserAccount follower){
        if (follower == null) return;
        for (int i = 0; i < numberOfFollowers; i++) {
            if(follower.equals(followers[i])){
                followers[i] = null;
            }
        }
    }

    public String getMyPostAsString(){
        String output = "";
        for (int i = 0; i < numberOfPosts; i++) {
            final Post post = posts[i];
            if (post != null) {
                output += post.getPostAsString() + "\n";
            }
        }
        return output;
    }

    public String getTimelineAsString(){
        String output = "";
        for (int i = 0; i < MAX_POSTS_TIMELINE; i++) {
            final Post post = timeline[i];
            if (post != null) {
                output += post.getPostAsString() + "\n";
            }
        }
        return output;
    }

    public String getFollowersAsString(){
        String output = "";
        for (int i = 0; i < numberOfFollowers; i++) {
            final UserAccount follower = followers[i];
            if (follower != null) {
                output += follower.getName() + "\n";
            }
        }
        return output;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
