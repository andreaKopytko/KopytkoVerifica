package it.marconi.kopytkoverifica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.marconi.kopytkoverifica.domains.Film;
import it.marconi.kopytkoverifica.services.FilmServices;

@Controller
@RequestMapping("/")
public class FilmController {

    FilmServices FilmService;

    // view Home Page
    @GetMapping("/films")
    public ModelAndView viewHomePage() {
        return new ModelAndView("homepage");
    }

    // view di aggiunta di un nuovo Film
    @GetMapping("/films/nuovo")
    public ModelAndView viewNewFilm() {
        return new ModelAndView("FilmForm");
    }

    /**
     * inserimento di un nuovo Film e reindirizzamento a view del Film aggiunto
     */
    @PostMapping("/films/nuovo")
    public ModelAndView NewFilmHandler(
        @ModelAttribute Film nuovoFilm,         // @Valid
        BindingResult br    
    ){
        // ricarica il form di inserimento in caso di errore
        if(br.hasErrors()) {
            return new ModelAndView("FilmForm");
        }

        FilmService.addFilm(nuovoFilm);
        return new ModelAndView("redirect:/contacts?codice=" + nuovoFilm.getCodice());
    }

    @GetMapping(params = "Codice")
    public ModelAndView showFilm(@RequestParam("Codice") String codice) {
        
        // scorre la collezione, controlle se i codici corrispondono e spedisce le informazioni
        for(Film f : FilmService.getCollezione()) {
            if(f.getCodice().equals(codice)) {
                return new ModelAndView("FilmDetail").addObject(f);
            }
        }

        // placeholder
        return new ModelAndView("homepage");
    }
}
