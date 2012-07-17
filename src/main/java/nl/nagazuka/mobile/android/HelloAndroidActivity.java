package nl.nagazuka.mobile.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.media.AudioManager;

public class HelloAndroidActivity extends Activity implements MediaPlayer.OnPreparedListener {
  
    private static String TAG = "hindustani-radio";
    private static MediaPlayer mediaPlayer = null;
    private static TextView welcomeText = null;
    private static String UJALA_URL = "http://live1.xs4all.nl/ujala/";
    private static String AMORFM_URL = "http://icecast.xs4all.nl:8000/AmorFM"; 
    private static String SUNRISEFM_URL = "http://server10.suristream.com:8006";
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
        if (welcomeText == null) {
          welcomeText = (TextView) findViewById(R.id.welcome_text);
        }
    }

    public void onPlay(final View view) throws Exception {
      welcomeText.setText("Loading...");

      if (mediaPlayer == null) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(AMORFM_URL);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
        welcomeText.setText("Loading...");
      }
    }

    public void onStop(final View view) {
      welcomeText.setText("Stopping...");
      if (mediaPlayer != null) {
        mediaPlayer.stop();
      }
      welcomeText.setText("Stopped!");
    }

    public void onPrepared(MediaPlayer mp) {
      if (mp != null) {
        mp.start();
        welcomeText.setText("Playing!");
      }
    }

}
