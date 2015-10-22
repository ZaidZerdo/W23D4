package zaid.bitcamp.ba.w23d4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity of the Android application.
 *
 * It displays the input fields, as well as the list.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * All data about the songs is inside this list.
     */
    private static List<SongModel> songs = new ArrayList<>();

    // Input fields
    private EditText songInput;
    private EditText artistInput;
    private EditText yearInput;

    // Button which triggers the adding process
    private Button addButton;

    // RecyclerView and its adapter
    private RecyclerView recyclerView;
    private SongAdapter adapter;

    /**
     * This is ran when the activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all the needed components used in the layout
        songInput = (EditText) findViewById(R.id.songName);
        artistInput = (EditText) findViewById(R.id.artistName);
        yearInput = (EditText) findViewById(R.id.year);
        addButton = (Button) findViewById(R.id.addSong);
        recyclerView = (RecyclerView) findViewById(R.id.songList);

        // Use the listener provided in the Click class
        addButton.setOnClickListener(new Click());

        // RecyclerView will add components like a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Make the adapter and set it to our recycler view
        adapter = new SongAdapter();
        recyclerView.setAdapter(adapter);
    }

    /**
     * Listener used for the button.
     *
     * It will do the following things:
     *  - Get all the data from the layout fields
     *  - Make an object out of it
     *  - Add the object (song) to the list
     *  - Notify the adapter that things changed
     *  - Clear the input fields
     */
    private class Click implements View.OnClickListener {

        /**
         * This method will be ran when an item
         * that has this listener is clicked.
         *
         * @param v View that is clicked.
         */
        @Override
        public void onClick(View v) {
            // Get all the data from the layout fields
            Editable artistName = artistInput.getText();
            Editable songName = songInput.getText();
            Editable year = yearInput.getText();

            // Make an object out of it
            SongModel song = new SongModel(artistName, songName, year);

            // Add the object (song) to the list
            songs.add(0, song);

            // Notify the adapter that things changed
            adapter.notifyDataSetChanged();

            // Clear the input fields
            songInput.setText("");
            artistInput.setText("");
            yearInput.setText("");
        }
    }

    /**
     * Class that describes how every single item in the list
     * should look like.
     *
     * The song will contain a view that holds the artist's
     * name, the song's name and the year.
     *
     * The class also holds a reference to the song placed
     * in that particular holder. We could use that later.
     */
    private class SongHolder extends RecyclerView.ViewHolder {

        // Fields that will hold the data of the song.
        private TextView artistView;
        private TextView songView;
        private TextView yearView;

        /**
         * Reference to the song used in this particular view.
         */
        private SongModel song;

        /**
         * Constructor that constructs the wanted holder by
         * using a given view.
         *
         * The view holds everything that describes a simple view,
         * but does not contain the three text views that we need,
         * so we add references to the needed text views.
         *
         * @param itemView
         */
        public SongHolder(View itemView) {
            // Calls the method from the super class.
            // It will prepare everything for you.
            super(itemView);

            // Use the view in order to find components from the layout.
            // How does it know that the itemView has those components??
            // Because the view is inflated with the song_item_layout!
            // Check the adapter class below.
            artistView = (TextView) itemView.findViewById(R.id.artistTitle);
            songView = (TextView) itemView.findViewById(R.id.songTitle);
            yearView = (TextView) itemView.findViewById(R.id.songYear);
        }
    }

    /**
     * Adapter that will connect the data to the RecyclerView.
     *
     * This adapter uses the static list provided in the main class.
     */
    private class SongAdapter extends RecyclerView.Adapter<SongHolder> {

        /**
         * Method that creates an EMPTY holder.
         *
         * It will first make a layout inflater which will be
         * used to create views.
         *
         * We then make the view using the layout inflater and
         * specify according to which layout it will be constructed.
         *
         * At the end make a SongHolder out of the view, which
         * the constructor above will do.
         */
        @Override
        public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // We need a layout inflater to make views
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            // We need a view in order to make a holder
            View view = layoutInflater.inflate(R.layout.song_item_layout, parent, false);

            // We make the holder out of the view
            return new SongHolder(view);
        }

        /**
         * Method that adds the data/information to an empty holder.
         *
         * It receives the position and an empty holder which will
         * be filled with the object (song) at the given position.
         *
         * @param holder An empty holder that needs to be filled.
         * @param position The index of the needed song.
         */
        @Override
        public void onBindViewHolder(SongHolder holder, int position) {
            // Find the song first
            SongModel song = songs.get(position);

            // Fill the holder with data!
            holder.songView.setText(song.getSongName());
            holder.artistView.setText(song.getArtistName());
            holder.yearView.setText(song.getYear());

            // Give it a reference to the song
            // Could be used later
            holder.song = song;
        }

        /**
         * Method that provides the adapter information about the
         * number of items that should be present in the RecyclerView.
         *
         * @return Integer that states how many items are there.
         */
        @Override
        public int getItemCount() {
            return songs.size();
        }
    }
}
