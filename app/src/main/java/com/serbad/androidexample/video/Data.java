package com.serbad.androidexample.video;

import java.io.Serializable;

public class Data implements Serializable {
    public String videoUrl;
    public String coverUrl;
    public String animateUrl;
    public int width;
    public int height;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAnimateUrl() {
        return animateUrl;
    }

    public void setAnimateUrl(String animateUrl) {
        this.animateUrl = animateUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
