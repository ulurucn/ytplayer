package vip.frendy.ytdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.YTWebView;
import vip.frendy.ytplayer.interfaces.IYTJSListener;

public class YoutubeActivity extends Activity implements IYTJSListener {

	String TAG = "YoutubeActivity";
	YTWebView webView;
	SeekBar seekBar;
	float totalVideoDuration;
	final static int MAX = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube);
		
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

	private void loadVideo(){
		webView = (YTWebView) this.findViewById(R.id.webView);
		webView.init(this);

//		Map<String, String> extraHeaders = new HashMap<>();
//		extraHeaders.put("Referer", "https://youtube.com");
//		webView.loadUrl("https://www.youtube.com/embed/3tmd-ClpJxA?autoplay=1", extraHeaders);
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
		add("3tmd-ClpJxA");
	}};

	public void load(View view) {
		String id = "H6SShCF58-U";
		webView.loadDefault(id);
	}

	public void playNext(View view) {
		String id = mVideoIds.get((mIndex ++) % mVideoIds.size());
		webView.loadVideoById(id);
	}

	public void cueNext(View view) {
		String id = mVideoIds.get((mIndex ++) % mVideoIds.size());
		webView.cueVideoById(id);
	}

	public void play(View view) {
		webView.playVideo();
	}
	
	public void pause(View view) {
		webView.pauseVideo();
	}
	
	public void stop(View view) {
		webView.stopVideo();
	}

	public void clear(View view) {
		webView.clearVideo();
	}

	public void gotoListActivity(View view) {
		startActivity(new Intent(this, YoutubeListActivity.class));
	}

	public void gotoSettingActivity(View view) {
		startActivity(new Intent(this, YoutubeSettingActivity.class));
	}

	public void gotoPlayerActivity(View view) {
		startActivity(new Intent(this, YoutubePlayerActivity.class));
	}

	public void gotoFragmentActivity(View view) {
		startActivity(new Intent(this, DemoListActivity.class));
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
		Log.i(TAG, "** onVideoEnd");

		modifySlider("ENDED");
	}

	@Override
	public void onVideoPlaying() {
		Log.i(TAG, "** onVideoPlaying");
	}

	@Override
	public void onVideoPaused() {
		Log.i(TAG, "** onVideoPaused");
	}

	@Override
	public void onVideoBuffering() {
		Log.i(TAG, "** onVideoBuffering");
	}

	@Override
	public void onVideoCued() {
		Log.i(TAG, "** onVideoCued");
	}

}
