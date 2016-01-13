package com.netradio.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.netradio.service.StreamService;

public class ActualThreadChecker implements Runnable {

    private String urls;

    private Long id;

    private URL url;

    private HttpURLConnection con;

    private StreamService srv;

    public ActualThreadChecker(final Long id, final String urls, final StreamService srv) {
        this.id = id;
        this.urls = urls;
        this.srv = srv;
    }

    public void setSrv(final StreamService srv) {
        this.srv = srv;
    }

    public boolean isActual() {
        boolean rez = false;
        try {
            url = new URL(this.urls);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (!con.getURL().getHost().isEmpty()) {
                con.connect();
                if (con.getResponseCode() == 200) {
                    if (con.getHeaderField("icy-pub") != null)
                        rez = true;
                } else {
                    if (con.getResponseCode() == -1)
                        rez = true;
                }
                con.disconnect();
            }
        } catch (IOException e) {
        }
        return rez;
    }

    @Override
    public void run() {
        srv.setActual(id, isActual());
    }
}
