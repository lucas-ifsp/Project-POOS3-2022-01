package br.edu.ifsp.poos3.practival01;

public class UserAccount {
    public static final int MAX_POSTS = 100;
    public static final int MAX_FOLLOWERS = 100;
    public static final int MAX_POSTS_TIMELINE = 10;

    private String name;
    private final String email;

    private final UserAccount[] followers;
    private int numberOfFollowers;

    private final Post[] posts;
    private int numberOfPosts;

    private final Post[] timeline;
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
            follower.updateTimeline(post);
        }
    }

    public void updateTimeline(Post post){
        timeline[numberOfPostInTimeline % MAX_POSTS_TIMELINE] = post;
        numberOfPostInTimeline ++;
    }

    public void deletePost(int postIndex){
        if(postIndex < 0 || postIndex >= numberOfPosts) return;
        for (int i = postIndex; i < numberOfPosts - 1; i++) {
            posts[i] = posts[i+1];
        }
        posts[numberOfPosts - 1] = null;
        numberOfPosts--;
    }

    public void clapPost(int postIndex){
        if(postIndex < 0 || postIndex >= numberOfPosts) return;
        posts[postIndex].clap();
    }

    public void booPost(int postIndex){
        if(postIndex < 0 || postIndex >= numberOfPosts) return;
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
                for(int j = i; j < numberOfFollowers - 1; j++){
                    followers[j] = followers[j+1];
                }
                followers[numberOfFollowers - 1] = null;
                numberOfFollowers --;
            }
        }
    }

    public String getMyPostAsString(){
        String output = "";
        for (int i = 0; i < numberOfPosts; i++)
            output += posts[i].getPostAsString() + "\n";
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
        for (int i = 0; i < numberOfFollowers; i++)
           output += followers[i].getName() + "\n";
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
