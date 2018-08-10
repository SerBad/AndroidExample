package com.serbad.androidexample.video;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Data implements Serializable {
    public String videoUrl;
    public String coverUrl;
    public String animateUrl;

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
}
