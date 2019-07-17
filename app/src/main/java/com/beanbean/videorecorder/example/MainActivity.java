package com.beanbean.videorecorder.example;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.beanbean.cameralibrary.JCameraView;
import com.beanbean.cameralibrary.listener.ClickListener;
import com.beanbean.cameralibrary.listener.JCameraListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    JCameraView jCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permission={
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        };
        ActivityCompat.requestPermissions(this, permission, 11);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11){
            initView();
        }
    }

    private void initView() {
        jCameraView = findViewById(R.id.jcameraview);
        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        //设置只能录像或只能拍照或两种都可以（默认两种都可以）
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER);
        //设置视频质量
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        //右边按钮点击事件
        jCameraView.setRightClickListener(new ClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {

            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                Log.i(TAG, "recordSuccess: url=" + url);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (jCameraView != null)
            jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (jCameraView != null)
            jCameraView.onPause();
    }
}
