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

      String url = "http://icecast.xs4all.nl:8000/AmorFM"; // your URL here

      if (mediaPlayer == null) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare(); // might take long! (for buffering, etc)
        mediaPlayer.start();
      }
      welcomeText.setText("Playing!");
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
