package com.netradio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import net.sourceforge.cobertura.CoverageIgnore;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MultipartFile;

import com.netradio.dao.GenreDao;
import com.netradio.dao.StreamDao;
import com.netradio.dao.StreamViewMapper;
import com.netradio.entity.Stream;
import com.netradio.entity.StreamView;

public class StreamServiceImpl implements StreamService {

    private String prefix;

    private StreamDao streamDao;

    private StreamViewMapper svmapper;

    private GenreDao genreDao;

    private Configuration conf;

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @CoverageIgnore
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public void setStreamDao(final StreamDao dao) {
        this.streamDao = dao;
    }

    public void setSvmapper(final StreamViewMapper mapper) {
        this.svmapper = mapper;
    }

    @Override
    public Stream getStream(final Long id) {
        if (id == null) {
            return null;
        } else {
            return streamDao.read(id);
        }
    }

    @Override
    public List<Stream> getStreams() {
        return streamDao.findAll();
    }

    @CoverageIgnore
    private void copy(final Stream stream, final MultipartFile file)
            throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = file.getInputStream();
            File tmp = new File(prefix + File.separatorChar + stream.getId()
                    + ".dat");

            if (tmp.exists()) {
                tmp.delete();
            }

            out = new FileOutputStream(tmp);

            int count = 0;
            long size = file.getSize();

            while (count < size) {
                count += IOUtils.copy(in, out);
            }

            out.flush();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }

    public void saveStream(final Stream stream, final MultipartFile file) {

        if (file.getSize() != 0) {
            stream.setIcon(file.getOriginalFilename());
        }

        if (stream.getId() == null) {
            streamDao.create(stream);
        } else {
            streamDao.update(stream);
        }

        if (file.getSize() != 0) {
            try {
                copy(stream, file);
            } catch (IOException e) {
                throw new DataAccessException(null, e) {
                    private static final long serialVersionUID = 1L;
                };
            }
        }
    }

    @Override
    public void saveStream(Stream stream, MultipartFile file, Long[] gids) {
        saveStream(stream, file);
        genreDao.deleteStreamGenre(stream.getId());
        if (gids != null) {
            for (Long gid : gids) {
                genreDao.addStreamGenre(gid, stream.getId());
            }
        }
    }

    @Override
    public void deleteStream(final Long id) {
        streamDao.delete(id);
    }

    public InputStream getImage(final Long id) {
        File tmp = new File(prefix + File.separatorChar + id + ".dat");

        InputStream is = null;

        if (tmp.exists()) {
            try {
                is = new FileInputStream(tmp);
            } catch (Exception e) {
            }
        }

        return is;
    }

    public Integer countStreamViews(final String name, final String type,
            final boolean act, boolean flag2, Long id) {
        return svmapper.count(name, type, act, flag2, id);
    }

    public List<StreamView> getStreamViews(final Integer page, final boolean act) {
        return svmapper.findAll(page, conf.getInt("countStreams"), act);
    }

    public List<StreamView> getStreamViewsF(final String name,
            final Integer page, final String type, final boolean act,
            final Long usr) {
        return svmapper.findByFavorite(usr, name, type, page,
                conf.getInt("countStreams"), act);
    }

    public List<StreamView> getStreamViews(final String name,
            final Integer page, final String type, final boolean act) {
        if (name == null) {
            return getStreamViews(page, act);
        } else {
            return svmapper.find(name, type, page, conf.getInt("countStreams"),
                    act);
        }
    }

    @Override
    public void setActual(final Long id, final boolean act) {
        if (id != null)
            streamDao.setActual(id, act);
    }

    @Override
    public List<StreamView> getFlowsByGenres(Long[] gids) {
        return svmapper.findByGenres(gids);
    }

    @Override
    public void setFavorite(Long uid, Long fid) {
        try {
            if (uid != null && fid != null)
                if (getStream(fid) != null) {
                    streamDao.setFavorite(uid, fid);
                }
        } catch (DuplicateKeyException e) {
        }
    }

    @Override
    public void delFavorite(Long uid, Long fid) {
        if (uid != null && fid != null)
            if (getStream(fid) != null) {
                streamDao.delFavorite(uid, fid);
            }
    }

    @Override
    public List<Long> getFavorites(Long uid) {
        return streamDao.getFavorites(uid);
    }

    @Override
    public StreamView getStreamInfo(Long id) {
        return svmapper.findStreamInfoById(id);
    }
}
