package com.serbad.androidexample.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.serbad.androidexample.R;
import com.shuyu.gsyvideoplayer.utils.NetworkUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class MyStandardGSYVideoPlayer extends StandardGSYVideoPlayer {

    private VideoLoadingView loadingHorizon;
    private ImageView startView;
    private DynamicRelativeLayout dynamicLayout;
    private boolean showUserLoading = true;

    public MyStandardGSYVideoPlayer(Context context) {
        super(context);
    }

    public MyStandardGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {


        //默认不显示该库的滑动界面改变进度，声音
        setIsTouchWiget(false);

        super.init(context);

        loadingHorizon = findViewById(R.id.loading_horizon);
        startView = findViewById(R.id.start_view);
        dynamicLayout = findViewById(R.id.dynamic_layout);

        loadingHorizon.setVisibility(showUserLoading?VISIBLE:GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_layout_standard;
    }

    //设置封面，取得自定义布局里面的SimpleDraweeView
    public void setThumb(String url, int width, int height) {
        if (TextUtils.isEmpty(url))
            return;
        if (mThumbImageViewLayout != null) {
            SimpleDraweeView simpleDraweeView = mThumbImageViewLayout.findViewById(R.id.thumb_view);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setResizeOptions(new ResizeOptions(width, height))
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setAutoPlayAnimations(true)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
            this.setThumbImageView(simpleDraweeView);
        }
    }

    //设置图片的显示比例
    public void setThumbAspectRatio(float aspectR) {
        if (mThumbImageView instanceof SimpleDraweeView) {
//            if (aspectR >1 ) {
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) loadingHorizon.getLayoutParams();
//                layoutParams.setMargins(0, 0, 0, 0);
//                loadingHorizon.setLayoutParams(layoutParams);
//            }


            ((SimpleDraweeView) mThumbImageView).setAspectRatio(aspectR);
            ViewGroup.LayoutParams layoutParams = mThumbImageView.getLayoutParams();
            layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
            layoutParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
            mThumbImageView.setLayoutParams(layoutParams);
        }
    }

    public void setThumb(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled())
            return;
        if (mThumbImageView instanceof SimpleDraweeView) {
            ((SimpleDraweeView) mThumbImageView).setImageBitmap(bitmap);
        }
    }

    @Override
    protected void showWifiDialog() {
        startPlayLogic();
        Toast.makeText(mContext, "当前网络环境为非wifi环境，请注意流量消耗", Toast.LENGTH_SHORT).show();
    }


    public boolean toPlay(String url, boolean loop) {
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(getContext().getApplicationContext(), getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!NetworkUtils.isConnected(getContext())) {
            Toast.makeText(getContext().getApplicationContext(), getResources().getString(R.string.no_network), Toast.LENGTH_SHORT).show();
            return false;
        }
        setUp(url, true, null);
        setLooping(loop);
        startPlayLogic();
        return true;
    }

    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        loadingHorizon.setVisibility(showUserLoading?VISIBLE:GONE);
    }

    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        loadingHorizon.setVisibility(GONE);
    }

    @Override
    protected void touchSurfaceMove(float deltaX, float deltaY, float y) {
        //置空滑动事件的处理方法
    }

    @Override
    protected void touchDoubleUp() {
        //重写该方法是为了去掉双击事件的处理
    }

    @Override
    protected void onClickUiToggle() {
        if (mCurrentState == CURRENT_STATE_PAUSE) {
            onVideoResume();
            startView.setVisibility(GONE);
        } else {
            onVideoPause();
            startView.setVisibility(VISIBLE);
            mCurrentState = CURRENT_STATE_PAUSE;
        }
    }

    public void setAspect(float aspect) {
        if (dynamicLayout != null) {
            dynamicLayout.setAspectRatio(aspect);
        }
        setThumbAspectRatio(aspect);
    }

    public void setUp(String url) {
        setUp(url, true, "");
    }

    //关于比例这一块，是可以优化掉的
    public void setAspect(float screenAspect, float videoAspect) {
        if (screenAspect > videoAspect && videoAspect <= 9 / 18f) {
            int screenHeightPixels = LocalDisplay.SCREEN_HEIGHT_PIXELS;
            int screenWidthPixels = LocalDisplay.SCREEN_WIDTH_PIXELS;
            int realWidth = (int) (screenHeightPixels * videoAspect);
            if (realWidth < screenWidthPixels) {
                realWidth = screenWidthPixels;
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(realWidth, screenHeightPixels);
            int marginWidth = (realWidth - LocalDisplay.SCREEN_WIDTH_PIXELS) / 2;
            layoutParams.setMargins(-marginWidth, 0, -marginWidth, 0);
            dynamicLayout.setLayoutParams(layoutParams);
        } else {
            dynamicLayout.setAspectRatio(videoAspect);
            setThumbAspectRatio(videoAspect);
        }
    }

    public int getCurrentPosition() {
        int duration = 0;
        try {
            duration = (int) getGSYVideoManager().getCurrentPosition();
        } catch (Exception e) {
            e.printStackTrace();
            return duration;
        }
        return duration;
    }

    public void setShowUserLoading(boolean showUserLoading) {
        this.showUserLoading = showUserLoading;
    }

//      videoplayer.setShowPlayJustClick(true);
//        videoplayer.setAspect(C.VERTICAL_ASPECT);

//        jcVideoPlayer.setShowPlayJustClick(true);
//        jcVideoPlayer.setCanTouch(false);
//        jcVideoPlayer.setUserLoadingProgressBar(false);
//        jcVideoPlayer.setShowLoading(false);
//
//        jcCartoon.setShowPlayJustClick(true);
//        jcCartoon.setCanTouch(true);
//        jcCartoon.setUserLoadingProgressBar(false);
//        jcCartoon.setShowLoading(false);


//    onProgressChange 回调的方法，貌似是用来处理歌词字幕的

}
