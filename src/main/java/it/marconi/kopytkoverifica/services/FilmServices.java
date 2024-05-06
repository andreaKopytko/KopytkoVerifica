package it.marconi.kopytkoverifica.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import it.marconi.kopytkoverifica.domains.Film;

@Service
public class FilmServices {

    // Collezione dei Film
    private ArrayList<Film> collezione = new ArrayList<Film>();

    //collezione.add(new Film("432", "1984", "azione", 1984, 5));


    public ArrayList<Film> getCollezione() {
        return collezione;
    }

    /**
     * Aggiunge un nuovo Film alla collezione
     * @param newFilm   - Film nuovo
     */
    public void addFilm(Film newFilm) {
        collezione.add(newFilm);
    }

    /**
     * Svuota l'arraylist dei film
     */
    public void clearFilmList() {
        collezione.clear();
    }

    /**
     * Rimuove un film dalla collezione
     * @param codice    - codice del film da togliere
     */
    public void removeFilm(String codice) {

        for(int i = collezione.size() - 1; i >= 0; i--) {
            if(collezione.get(i).getCodice().equals(codice)) {
                collezione.remove(i);
            }
        }
    }
}
