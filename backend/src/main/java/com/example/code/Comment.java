package com.example.code;


import org.json.JSONObject;

import java.util.Arrays;

public class Comment {
    Commodity commodity;
    String text;
    String date;
    User user;
    int id;
    CommentVote[] commentVotes;
    int likeCount;
    int disLikeCount;


    public Comment(User user, Commodity commodity, String text, String date , int id ) {
        this.user = user;
        this.commodity = commodity;
        this.text = text;
        this.date = date;
        this.commentVotes = new CommentVote[0];
        this.id = id;
        this.likeCount = 0 ;
        this.disLikeCount = 0;
    }
    public CommentVote getUserVote(User user){
        for(CommentVote vote: this.commentVotes)
            if (user.username.equals(vote.user.username))
                return  vote;
        return null;
    }
    public boolean addVote(User user , int vote ){

        if (vote == -1 | vote == 0 || vote == 1) {
                CommentVote commentvote = new CommentVote(user, vote);
                this.commentVotes = Arrays.copyOf(this.commentVotes, this.commentVotes.length + 1);
                this.commentVotes[this.commentVotes.length - 1] = commentvote;
                if (vote == 1)
                    this.likeCount += 1;
                else if (vote == -1)
                    this.disLikeCount += 1;
                return true;
        }
       else {
           return false;
        }
    }
    public boolean updateVote(CommentVote commentvote , int vote ){

        if (vote == -1 | vote == 0 || vote == 1) {

            if (commentvote.vote == 1)
                this.likeCount -= 1;
            else if (commentvote.vote == -1)
                this.disLikeCount -= 1;

            if (vote == 1)
                this.likeCount += 1;
            else if (vote == -1)
                this.disLikeCount += 1;

            commentvote.vote = vote;
            return true;
        }
        else {
            return false;
        }
    }
    public JSONObject getInformation(){
        JSONObject data = new JSONObject();
        data.put("id",this.id);
        data.put("username",this.user.username);
        data.put("text",this.text);
        data.put("date" , this.date);
        data.put("likeCount",this.likeCount);
        data.put("disLikeCount",this.disLikeCount);
        return data;
    }

}
