package com.serbad.androidexample.fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.serbad.androidexample.R;

public class FrescoActivity extends Activity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        simpleDraweeView = findViewById(R.id.thumb_view);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse("http://qncdn.fotoplace.cc/tv/180622/177109666700443648/4cd7fbc38e126cb2ef4b2e47374dbf87.webp"))
                    .setResizeOptions(new ResizeOptions(480, 540))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
        simpleDraweeView.setAspectRatio(480f/540f);
    }
}
