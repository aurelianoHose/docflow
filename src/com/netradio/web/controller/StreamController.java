package com.netradio.web.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.netradio.entity.Codec;
import com.netradio.entity.Genre;
import com.netradio.entity.Stream;
import com.netradio.entity.StreamView;
import com.netradio.entity.User;
import com.netradio.service.CodecService;
import com.netradio.service.GenreService;
import com.netradio.service.StreamService;
import com.netradio.service.UserService;

@Controller
@RequestMapping("/stream")
public class StreamController {

    private Validator validator = new FlowValidator();

    @Autowired
    private CodecService codecSrv;

    @Autowired
    private UserService userSrv;

    @Autowired
    private StreamService srv;

    @Autowired
    private GenreService genreSrv;

    @Autowired
    private Configuration conf;
    
    @Autowired
    private StreamQuickSearchState state;

    public void setState(StreamQuickSearchState state) {
        this.state = state;
    }

    private boolean flag = true;
    private boolean flag2 = false;

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    @RequestMapping("/list")
    public String flows(
            @RequestParam(value = "page", defaultValue = "0") final Integer page,
            final Principal p, final Model model) {
        List<StreamView> streams;
        Long usr = ((User) userSrv.getUser(p.getName())).getId();
        state.setPage(page);
        model.addAttribute("_page_", state.getPage());
        model.addAttribute("_count_", srv.countStreamViews(state.getSearch(),
                state.getType(), flag, flag2, usr));

        if (state.getSearch() != null) {
            model.addAttribute("search", state.getSearch());
        }

        model.addAttribute("favorites", srv.getFavorites(usr));

        if (flag2) {
            streams = srv.getStreamViewsF(state.getSearch(), page,
                    state.getType(), flag, usr);
        } else {
            streams = srv.getStreamViews(state.getSearch(), page,
                    state.getType(), flag);
        }

        if (!streams.isEmpty()) {
            model.addAttribute("streams", streams);
        }
        model.addAttribute("countStreams", conf.getInt("countStreams"));
        return "stream/streams";
    }

    @RequestMapping("/listA")
    public String flows(
            @RequestParam(value = "fl", required = true) final boolean fl) {
        flag = fl;
        flag2 = false;
        return "redirect:/stream/list.html";
    }

    @RequestMapping("/listB")
    public String flowsb(
            @RequestParam(value = "fl", required = true) final boolean fl) {
        flag = fl;
        flag2 = true;
        return "redirect:/stream/list.html";
    }

    @RequestMapping("/stream")
    public String flow(
            @RequestParam(value = "id", required = false, defaultValue = "0") final Long id,
            final Model model) {
        Stream stream = srv.getStream(id);
        List<Genre> genres = null;
        String genresStr = "";
        if (stream == null) {
            stream = new Stream();
        } else {
            genres = genreSrv.getGenres(stream.getId());
            for (Genre genre : genres) {
                genresStr += genre.getName() + ", ";
            }
        }

        model.addAttribute("genres", genresStr);
        model.addAttribute("streamModel", stream);
        List<Codec> codecs = codecSrv.getCodecs();
        model.addAttribute("codecs", codecs);
        return "stream/stream";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final Principal p,
            @ModelAttribute("streamModel") final Stream stream,
            final BindingResult result,
            @RequestParam(value = "file") final MultipartFile file,
            @RequestParam(value = "genres") final String genres,
            final Model model) {

        validator.validate(stream, result);

        String[] genresNames = genres.toUpperCase().split(",");
        Set<Long> gids = new HashSet<Long>();
        for (int i = 0; i < genresNames.length; i++) {
            if (genreSrv.getGenre(genresNames[i]) != null) {
                gids.add(genreSrv.getGenre(genresNames[i]).getId());
            }
        }
        if (result.hasErrors()) {
            List<Codec> codecs = codecSrv.getCodecs();
            model.addAttribute("codecs", codecs);
            model.addAttribute("genres", genres);
            return "stream/stream";
        } else {
            stream.setIdCreator(userSrv.getUser(p.getName()).getId());
            srv.saveStream(stream, file, gids.toArray(new Long[gids.size()]));
            return "redirect:/stream/list.html";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(
            @RequestParam(value = "id", required = false) final Long id) {
        if (id != null) {
            srv.deleteStream(id);
        }
        return "redirect:/stream/list.html";
    }

    @RequestMapping(value = "/setFavorite", method = RequestMethod.GET)
    public String setFavorite(
            @RequestParam(value = "fid", required = true) final Long fid,
            Principal p) {
        if (fid != null) {
            Long uid = ((User) userSrv.getUser(p.getName())).getId();
            srv.setFavorite(uid, fid);
        }
        return "redirect:/stream/list.html";
    }

    @RequestMapping(value = "/delFavorite", method = RequestMethod.GET)
    public String delFavorite(
            @RequestParam(value = "fid", required = true) final Long fid,
            Principal p) {
        if (fid != null) {
            Long uid = ((User) userSrv.getUser(p.getName())).getId();
            srv.delFavorite(uid, fid);
        }
        return "redirect:/stream/list.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(
            @RequestParam(value = "search", required = false) final String search) {
        if (search == null || search.trim().isEmpty()) {
            state.setSearch(null);
            state.setType("name");
        } else {
            state.setSearch(search);
            state.setPage(null);
            state.setType("name");
        }
        return "redirect:/stream/list.html";
    }

    @RequestMapping(value = "/searchbygenre", method = RequestMethod.GET)
    public String searchByGenre(
            @RequestParam(value = "search", required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            state.setSearch(null);
            state.setType("name");
        } else {
            state.setSearch(search);
            state.setPage(null);
            state.setType("genres");
        }
        return "redirect:/stream/list.html";
    }

    @Component
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public static class StreamQuickSearchState extends QuickSearchState {
        private static final long serialVersionUID = 1L;
    }

    public static class FlowValidator implements Validator {

        @Override
        public boolean supports(final Class<?> clazz) {
            return Stream.class.equals(clazz);
        }

        @Override
        public void validate(final Object obj, final Errors errors) {
            // Stream stream = (Stream) obj;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "link",
                    "flow.link.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bitrade",
                    "flow.bitrade.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codecId",
                    "flow.codec.empty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flow",
                    "flow.flow.empty");

            /*
             * if (flow.getFlow().equals("fflow")) { errors.rejectValue("flow",
             * "negativevalue"); }
             */
        }
    }

}
