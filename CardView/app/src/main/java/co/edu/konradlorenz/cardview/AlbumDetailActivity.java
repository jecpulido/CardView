package co.edu.konradlorenz.cardview;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AlbumDetailActivity extends AppCompatActivity {

    ArrayList<Song> listSong;
    RecyclerView recyclerSongs;
    TextView title;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        //Obtengo album seleccionado
        Album album = (Album)getIntent().getExtras().getSerializable("album");
        Toast.makeText(this,album.getName(),Toast.LENGTH_SHORT).show();

        initCollapsingToolbar(album);

        title = (TextView) findViewById(R.id.titlealbum);
        title.setText(album.getName());
        description= (TextView) findViewById(R.id.descriptionalbum);
        description.setText(album.getNumOfSongs() + " canciones");

        listSong = new ArrayList<>();
        recyclerSongs = (RecyclerView) findViewById(R.id.recycler_view_songs);
        recyclerSongs.setLayoutManager(new LinearLayoutManager(this));
        recyclerSongs.setItemAnimator(new DefaultItemAnimator());
        SongsAdapter adapter = new SongsAdapter(listSong);
        recyclerSongs.setAdapter(adapter);

        try {
            Glide.with(this).load(album.getThumbnail()).into((ImageView) findViewById(R.id.backdropsong));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BuscarCanciones(album.getNumOfSongs());
    }

    private void BuscarCanciones(int num) {
        for (int i = 1; i <=num;i++){
            listSong.add(new Song("Cancion "+i,(int)(Math.random() * 5) + 1+":"+(int)(Math.random()*(10-50+1)+50)));
        }
    }

    private void initCollapsingToolbar(Album album) {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbarsong);
        final Album albumDetail = album;
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbarsong);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(albumDetail.getName());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
