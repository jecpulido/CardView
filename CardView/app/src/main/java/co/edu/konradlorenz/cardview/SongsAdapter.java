package co.edu.konradlorenz.cardview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolderSongs> {

    ArrayList<Song> listSongs;

    public SongsAdapter(ArrayList<Song> listSongs) {
        this.listSongs = listSongs;
    }

    @Override
    public ViewHolderSongs onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card,null,false);
        return new ViewHolderSongs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSongs holder, int position) {
        holder.title.setText(listSongs.get(position).getTitulo());
        holder.duration.setText(listSongs.get(position).getDuracion());
    }

    @Override
    public int getItemCount() {
        return listSongs.size();
    }

    public class ViewHolderSongs extends RecyclerView.ViewHolder {

        TextView title;
        TextView duration;

        public ViewHolderSongs(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            duration= (TextView) itemView.findViewById(R.id.txtDuration);
        }
    }
}
