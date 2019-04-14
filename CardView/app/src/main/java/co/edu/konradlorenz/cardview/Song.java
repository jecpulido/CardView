package co.edu.konradlorenz.cardview;

import android.widget.TextView;

public class Song {

    private String titulo;
    private String duracion;

    public Song(String titulo, String duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
