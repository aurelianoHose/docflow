package com.netradio.web.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netradio.service.StreamService;

@Controller
public class ImageController {

    @Autowired
    private StreamService srv;

    private InputStream getNotImage(final HttpServletRequest req) {
        InputStream is = null;

        String name = req.getServletContext().getRealPath("/images/zzz.jpg");

        try {
            is = new FileInputStream(name);
        } catch (FileNotFoundException e) {
        }

        return is;
    }

    @RequestMapping("/image")
    public void image(final Long id, final HttpServletRequest req,
            final HttpServletResponse res) {

        InputStream is = srv.getImage(id);

        if (is == null) {
            is = getNotImage(req);
        }

        if (is == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            try {
                res.addHeader("Content-Type", "image/jpg");
                OutputStream os = res.getOutputStream();
                IOUtils.copy(is, os);
                os.flush();
            } catch (IOException e) {
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
