package com.netradio.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.netradio.entity.Stream;
import com.netradio.entity.StreamView;

public interface StreamService {

    Stream getStream(Long id);

    List<Stream> getStreams();

    void saveStream(Stream flow, MultipartFile file, Long[] gids);

    void deleteStream(Long id);

    InputStream getImage(Long id);

    Integer countStreamViews(String name, String type, boolean act, boolean flag2, Long id);

    List<StreamView> getStreamViews(Integer page, boolean act);

    List<StreamView> getStreamViewsF(final String name, final Integer page, String type, final boolean act, final Long usr);

    List<StreamView> getStreamViews(String name, Integer page, String type, boolean act);

    List<StreamView> getFlowsByGenres(Long[] gids);

    void setActual(Long id, boolean act);

    void setFavorite(Long uid, Long fid);

    void delFavorite(Long uid, Long fid);

    List<Long> getFavorites(Long uid);

    StreamView getStreamInfo(Long id);
}
