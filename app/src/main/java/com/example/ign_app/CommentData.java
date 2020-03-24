package com.example.ign_app;

public class CommentData {
    private String contentId, comment;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentData(String contentId, String comment) {
        this.contentId = contentId;
        this.comment = comment;
    }
}
