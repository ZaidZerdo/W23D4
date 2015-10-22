package zaid.bitcamp.ba.w23d4;

import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Zaid on 10/22/2015.
 *
 * Model that describes a song.
 */
public class SongModel {
    /**
     * UUID is useful, but not really used in this example.
     */
    private UUID id;

    // Attributes of the song model.
    private String songName;
    private String artistName;
    private Integer year;

    /**
     * Constructs the given song out of the given parameters.
     *
     * Don't let Editable confuse you. It has some extra
     * methods that the String class does not.
     *
     * In order to make a String from an Editable use toString.
     */
    public SongModel(Editable songName, Editable artistName, Editable year) {
        id = UUID.randomUUID();
        this.songName = songName.toString();
        this.artistName = artistName.toString();
        try {
            this.year = Integer.parseInt(year.toString());
        } catch (NumberFormatException ex) {
            Log.e("Exception", "String is not number.");
            this.year = -1;
        }
    }

    /**
     * Returns the name of the song as a String.
     *
     * @return The name of the song.
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Returns the name of the artist as a String.
     *
     * @return The name of the artist.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Since year is an Integer object,
     * first convert it to a String and then return it.
     *
     * @return The year of the song.
     */
    public String getYear() {
        return year.toString();
    }
}
