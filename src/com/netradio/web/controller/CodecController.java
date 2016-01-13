package com.netradio.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netradio.entity.Codec;
import com.netradio.service.CodecService;

@Controller
@RequestMapping("/codec")
public class CodecController {

    @Autowired
    private CodecService srv;

    @RequestMapping("/list")
    public String codecs(final Model model) {
        List<Codec> codecs = srv.getCodecs();
        model.addAttribute("codecs", codecs);
        return "codec/codecs";
    }

    @RequestMapping("/codec")
    public String codec(
            @RequestParam(value = "id", required = false, defaultValue = "0") final Long id,
            final Model model) {
        Codec codec = srv.getCodec(id);
        if (codec != null) {
            model.addAttribute("codec", codec);
        }
        return "codec/codec";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @RequestParam(value = "id", required = false) final Long id,
            @RequestParam(value = "name", required = true) final String name) {
        Codec codec = new Codec();
        if (id != null) {
            codec.setId(id);
        }
        codec.setName(name);
        srv.saveCodec(codec);
        return "redirect:/codec/list.html";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(
            @RequestParam(value = "id", required = false) final Long id) {
        if (id != null) {
            srv.deleteCodec(id);
        }
        return "redirect:/codec/list.html";
    }

}
