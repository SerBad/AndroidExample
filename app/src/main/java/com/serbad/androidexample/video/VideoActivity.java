package com.serbad.androidexample.video;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serbad.androidexample.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends Activity {
    private List<Data> list = new ArrayList<>();
    MyStandardGSYVideoPlayer videoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);
        list = getAssetsFile();

        initView();


    }

    private void initView() {
        videoPlayer = findViewById(R.id.video_view);

        Data data = list.get(2);

        videoPlayer.toPlay(data.videoUrl, true);
        videoPlayer.setThumb(data.animateUrl, data.width, data.height);
        videoPlayer.setThumbAspectRatio((float) data.width / (float) data.height);

//        videoPlayer.clearCurrentCache();
//        videoPlayer.startPlayLogic();
//        videoPlayer.setVideoAllCallBack(new  );
    }


    private List<Data> getAssetsFile() {
        InputStream inputStream = null;
        try {
            inputStream = this.getAssets().open("data.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }

            Gson gson = new Gson();
            List<Data> list = gson.fromJson(buffer.toString(), new TypeToken<List<Data>>() {
            }.getType());

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
