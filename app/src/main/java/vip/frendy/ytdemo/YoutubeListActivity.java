package vip.frendy.ytdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.ArrayList;

import vip.frendy.ytplayer.YTWebView;
import vip.frendy.ytplayer.interfaces.IYTJSListener;

public class YoutubeListActivity extends Activity implements IYTJSListener {

	String TAG = "YoutubeActivity";
	YTWebView webView;
	SeekBar seekBar;
	float totalVideoDuration;
	final static int MAX = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube_list);
		
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

	public void cueList(View view) {
		String list = mVideoIds.toString();
		list = list.substring(1, list.length()-1);
		webView.cuePlaylist(list);
	}

	public void loadList(View view) {
		String list = mVideoIds.toString();
		list = list.substring(1, list.length()-1);
		webView.loadPlaylist(list);
	}

	public void nextVideo(View view) {
		webView.nextVideo();
	}

	public void previousVideo(View view) {
		webView.previousVideo();
	}

	public void playVideoAt(View view) {
		int index = 0;
		webView.playVideoAt(index);
	}

	public void loop(View view) {
		webView.setLoop(true);
	}

	public void shuffle(View view) {
		webView.setShuffle(true);
	}


	@Override
	public void onYouTubeIframeAPIReady() {

	}

	@Override
	public void updateVideoDuration(float duration) {
		try {
			changeSlider(duration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTotalVideoDuration(float duration) {
		try {
			totalVideoDuration = duration;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReady() {

	}

	@Override
	public void onVideoUnStarted() {

	}

	@Override
	public void onVideoEnd() {
		modifySlider("ENDED");
	}

	@Override
	public void onVideoPlaying() {

	}

	@Override
	public void onVideoPaused() {

	}

	@Override
	public void onVideoBuffering() {

	}

	@Override
	public void onVideoCued() {

	}

	@Override
	public void onVideoStateCheckResult(int state, float current, float total) {

	}

	@Override
	public void onApiChange() {

	}

	@Override
	public void onError(int error) {

	}

	@Override
	public void onPlaybackRateChange(double rate) {

	}

	@Override
	public void onPlaybackQualityChange(int playbackQuality) {

	}

}
