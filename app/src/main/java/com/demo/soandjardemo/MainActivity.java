package com.demo.soandjardemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.myUtil.recordingmp3library.Mp3Recorder;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Mp3Recorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recorder = new Mp3Recorder();
    }

    public void start(View view){
        try {
            String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File directory = new File(externalPath + "/" + "AudioRecorder");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File mp3File = new File(directory, "新的文件999.mp3");

            recorder.startRecording(mp3File);
        } catch(IOException e) {
            Log.d("MainActivity", "Start error");
        }
    }
    public void stop(View view){
        try {
            recorder.stopRecording();
        } catch(IOException e) {
            Log.d("MainActivity", "Stop error");
        }
    }
}
