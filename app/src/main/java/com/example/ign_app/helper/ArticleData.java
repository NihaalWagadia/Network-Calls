package com.example.ign_app.helper;

public class ArticleData {

    private String contentId, headline, description, urlImage, authorName, authorImage, slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.contentId = slug;
    }


    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public ArticleData(String contentId, String headline, String description, String urlImage, String authorName, String authorImage, String slug) {
        this.contentId = contentId;
        this.headline = headline;
        this.description = description;
        this.urlImage = urlImage;
        this.authorName = authorName;
        this.authorImage = authorImage;
        this.slug = slug;

    }
}
