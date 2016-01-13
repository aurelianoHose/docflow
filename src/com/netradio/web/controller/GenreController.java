package com.netradio.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netradio.entity.Genre;
import com.netradio.service.GenreService;
import com.netradio.service.UserService;

@Controller
public class GenreController {

    @Autowired
    @Qualifier("genreValidator")
    private Validator validator;

    @Autowired
    private GenreService srv;

    @Autowired
    private UserService s;

    @Autowired
    private GenreQuickSearchState state;

    public void setState(GenreQuickSearchState state) {
        this.state = state;
    }

    @RequestMapping("/genres")
    public String genres(final Model model) {
        List<Genre> genres;
        if (state.getSearch() == null) {
            genres = srv.getGenres();
        } else {
            genres = srv.getGenres(state.getSearch());
            model.addAttribute("search", state.getSearch());
        }
        model.addAttribute("genres", genres);
        return "genre/genres";
    }

    @RequestMapping(value = "/genre", method = RequestMethod.GET)
    public String genre(final Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/genre";
    }

    @RequestMapping(value = "/genre", method = RequestMethod.GET, params = { "id" })
    public String genre(
            @RequestParam(value = "id", required = true) final Long id,
            final Model model) {
        Genre genre = srv.getGenre(id);
        if (genre != null) {
            model.addAttribute("genre", genre);
        }
        return "genre/genre";
    }

    @RequestMapping(value = "/savegenre", method = RequestMethod.POST)
    public String save(final Principal p, @ModelAttribute("genre") final Genre genre,
            final BindingResult result) {
        validator.validate(genre, result);

        if (result.hasErrors()) {
            return "genre/genre";
        } else {
            if (p.getName().isEmpty()) {
                genre.setIdCreator(new Long(0));
            } else {
                genre.setIdCreator(s.getUser(p.getName()).getId());
            }
            srv.saveGenre(genre);
            return "redirect:/genres.html";
        }
    }

    @RequestMapping(value = "/deletegenre", method = RequestMethod.GET)
    public String delete(
            @RequestParam(value = "id", required = false) final Long id) {
        if (id != null) {
            srv.deleteGenre(id);
        }
        return "redirect:/genres.html";
    }

    @RequestMapping(value = "/searchGenres", method = RequestMethod.POST)
    public String search(
            @RequestParam(value = "search", required = false) final String search) {
        if (search == null || search.trim().isEmpty()) {
            state.setSearch(null);
        } else {
            state.setSearch(search);
        }
        return "redirect:/genres.html";
    }

    @Component
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public static class GenreQuickSearchState extends QuickSearchState {

        private static final long serialVersionUID = 1L;
    }

    @Component("genreValidator")
    public static class GenreValidator implements Validator {

        @Autowired
        private GenreService srv;

        @Override
        public boolean supports(final Class<?> clazz) {
            return Genre.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {
            Genre genre = (Genre) obj;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                    "genre.wrong");
            if (genre.getId() == null) {
                List<Genre> list = new ArrayList<Genre>();
                if (genre.getName() != null) {
                    if ((list = srv.getGenres(genre.getName())) != null) {
                        for (Genre genre2 : list) {
                            if (genre2.getName().equalsIgnoreCase(
                                    genre.getName())) {
                                errors.rejectValue("name", "genre.exists");
                                break;
                            }
                        }
                    }

                }
            }
        }

    }
}
