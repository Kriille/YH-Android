package com.example.kristoffer.assignment3a;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class mainActivity extends ListActivity implements View.OnClickListener {

    public final static String TRACK = "track";
    public final static String ACTION = "action";
    // Const Variables
    final static int PLAY = 1;
    final static int PAUSE = 2;
    final static int PLAY_SONG = 3;
    boolean isPaused = false;
    ImageButton playBtn;
    ImageButton nextBtn;
    ImageButton prevBtn;
    private int currentTrack = 0;
    private boolean isPlaying = false;

    // Created method called when activity is under creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkIfStorageAvailable()) {
            Log.d("MusicActivity", "Ja, den är tillgänglig!");

            ArrayList<Track> trackList = getPlaylist();
            // Creating an custom array adapter the fast but VERY insufficient way which works with small lists, using android layout and the playlist.
            ArrayAdapter<Track> musicAdapter = new ArrayAdapter<Track>(this, android.R.layout.simple_list_item_1, trackList);

            setListAdapter(musicAdapter);

            //buttons
            playBtn = (ImageButton) findViewById(R.id.playBtn);
            nextBtn = (ImageButton) findViewById(R.id.nextBtn);
            prevBtn = (ImageButton) findViewById(R.id.prevBtn);

            //buttons listeners
            playBtn.setOnClickListener(this);
            nextBtn.setOnClickListener(this);
            prevBtn.setOnClickListener(this);
        }

    }

    // Querying all music from Audio media folder and converting it from cursor to ArrayList<Track>
    private ArrayList<Track> getPlaylist() {
        Cursor musicResult = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DATA},
                MediaStore.Audio.Media.IS_MUSIC + " > 0 ",
                null,
                null
        );

        ArrayList<Track> trackList = new ArrayList<Track>();

        if (musicResult.getCount() > 0) {
            musicResult.moveToFirst();
            Track prev = null;
            do {
                Track track = new Track(
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                        musicResult.getString(musicResult.getColumnIndex(MediaStore.Audio.Media.DATA))
                );

                if (prev != null) //here prev song linked to current one. To simple play them in list
                    prev.setNext(track);

                prev = track;
                trackList.add(track);
            }
            while (musicResult.moveToNext());

            prev.setNext(trackList.get(0)); //play in cycle;
        }
        Log.d("MusicActivity", "" + musicResult.getCount());
        musicResult.close();

        return trackList;
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intentService = new Intent(this, MusicService.class);
        currentTrack = position;
        intentService.putExtra(TRACK, currentTrack).putExtra(ACTION, PLAY_SONG);
        startService(intentService);

        playBtn.setImageResource(R.drawable.ic_action_pause);
        isPlaying = true;
    }


    private boolean checkIfStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intentService = new Intent(this, MusicService.class);
        switch (view.getId()){
            // Play or pause.
            case R.id.playBtn:
                if(isPlaying){
                    // Just pause playing
                    intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PAUSE);
                    // Show pause button.
                    playBtn.setImageResource(R.drawable.ic_action_play);
                    isPlaying = false;
                    isPaused = true;
                    startService(intentService);
                }

                else {
                    if (isPaused) {
                        // Just start playing
                        intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY);
                        // Show pause button.
                        playBtn.setImageResource(R.drawable.ic_action_pause);
                        isPlaying = true;
                        startService(intentService);
                    }
                    else {
                        // Just start playing
                        intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY_SONG);
                        // Show pause button.
                        playBtn.setImageResource(R.drawable.ic_action_pause);
                        isPlaying = true;
                        startService(intentService);
                    }
                }
                break;

            case R.id.nextBtn:
                if (currentTrack != -1) {
                    currentTrack++;
                    if (currentTrack < TrackListHelper.numberOfTracks) {
                        intentService.putExtra(TrackListHelper.TRACK, currentTrack);
                        intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY_SONG);
                    }
                    else {
                        currentTrack = 0;
                        intentService.putExtra(TrackListHelper.TRACK, currentTrack);
                        intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY_SONG);
                    }
                }
                break;

            case R.id.prevBtn:
                if (currentTrack > 0) {
                    currentTrack--;
                    intentService.putExtra(TrackListHelper.TRACK, currentTrack);
                    intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY_SONG);
                }
                else {
                    currentTrack = 0;
                    intentService.putExtra(TrackListHelper.TRACK, currentTrack);
                    intentService.putExtra(TrackListHelper.ACTION, TrackListHelper.PLAY_SONG);
                }

                break;
        }
        startService(intentService);
    }
}
