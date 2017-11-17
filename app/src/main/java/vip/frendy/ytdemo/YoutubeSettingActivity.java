package vip.frendy.ytdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.YTJSInterface;
import vip.frendy.ytplayer.YTWebView;
import vip.frendy.ytplayer.interfaces.IYTJSListener;

public class YoutubeSettingActivity extends Activity implements IYTJSListener {

	String TAG = "YoutubeActivity";
	YTWebView webView;
	SeekBar seekBar;
	float totalVideoDuration;
	final static int MAX = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube_setting);
		
		seekBar = (SeekBar) this.findViewById(R.id.seekBar1);
		seekBar.setMax(MAX);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int progress = seekBar.getProgress();
				double secs = (progress * totalVideoDuration)/1000;
				secs = Math.ceil(secs);
				Log.d(TAG, "onStopTrackingTouch :: progress = " + progress +  "-- secs = " + secs);
				webView.loadUrl("javascript:seekVideo('"+ secs +"')");
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.d(TAG, "onStartTrackingTouch :: progress = " + seekBar.getProgress() );
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}
		});
		
		loadVideo();
	}

	@SuppressLint("JavascriptInterface")
	private void loadVideo(){
		webView = (YTWebView) this.findViewById(R.id.webView);
		webView.init(this);
	}

	private void changeSlider(float time){
		float progress =  (time/totalVideoDuration) * 1000;
		final double d = Math.ceil(progress);
		Log.d(TAG, "changeSlider progress = " + d);
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				seekBar.setProgress((int)d);
			}
		});
	}

	private void modifySlider(final String flag){
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if(flag.equals("ENDED"))
					seekBar.setProgress(MAX);
			}
		});
	}

	private int mIndex = 0;
	private ArrayList<String> mVideoIds = new ArrayList<String>() {{
		//dmLSM9zM0ME - 59 secs video
		//M7lc1UVf-VE - iframe API video
		//H6SShCF58-U
		//3tmd-ClpJxA ：被禁止？
		add("dmLSM9zM0ME");
		add("M7lc1UVf-VE");
		add("H6SShCF58-U");
	}};

	public void load(View view) {
		String id = "H6SShCF58-U";
		webView.loadDefault(id);
	}

	public void mute(View view) {
		webView.mute();
	}

	public void volume(View view) {
		webView.setVolume(50);
	}


	@Override
	public void onYouTubeIframeAPIReady() {

	}

	@Override
	public void updateVideoDuration(String duration) {
		try {
			changeSlider(Float.parseFloat(duration));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTotalVideoDuration(String duration) {
		try {
			totalVideoDuration = Float.parseFloat(duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onVideoEnd() {
		modifySlider("ENDED");
	}

}
