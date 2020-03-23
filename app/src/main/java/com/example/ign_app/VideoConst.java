package com.example.ign_app;

public class VideoConst {

    String contentId, title, description, imgUrl, videoUrl;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public VideoConst(String contentId, String title, String description, String imgUrl, String videoUrl) {
        this.contentId = contentId;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.videoUrl = videoUrl;
    }
}
