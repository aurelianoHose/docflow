package com.netradio.dao.mybatis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.StreamViewMapper;
import com.netradio.entity.StreamView;

public class StreamViewDaoImpl extends SqlSessionDaoSupport implements StreamViewMapper {

    private Configuration conf;

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public List<StreamView> findAll(final Integer page, final Integer count,
            final boolean act) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("count", count);
        map.put("act", act);
        return getSqlSession().selectList("StreamView.findAll", map);
    }

    @Override
    public List<StreamView> find(final String name, String type,
            final Integer page, final Integer count, final boolean act) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (type.equals("genres")) {
            map.put("genre", name);
        } else {
            map.put("name", "%" + name + "%");
        }
        map.put("page", page);
        map.put("count", count);
        map.put("act", act);
        return getSqlSession().selectList("StreamView.find", map);
    }

    @Override
    public Integer count(final String name, String type, final boolean act,
            boolean flag2, Long id) {
        Map<String, Object> map = new HashMap<String, Object>();

        String param;

        if (name == null) {
            param = "";
        } else {
            param = name.trim();
        }
        map.put("act", act);

        if ((type != null) && type.equals("genres")) {
            map.put("genre", param);
        } else {
            map.put("name", "%" + param + "%");
        }

        Integer count = 0;

        if (flag2) {
            map.put("usr", id);
            count = getSqlSession().selectOne("StreamView.countFavorites", map);
        } else {
            count = getSqlSession().selectOne("StreamView.count", map);
        }

        return new BigDecimal(count).divide(
                new BigDecimal(conf.getInt("countStreams")), RoundingMode.UP)
                .intValue();

    }

    @Override
    public List<StreamView> findByGenres(Long[] gids) {
        Map<String, Long[]> map = new HashMap<String, Long[]>();
        map.put("gids", gids);
        return getSqlSession().selectList("StreamView.findByGenres", map);
    }

    @Override
    public List<StreamView> findByFavorite(Long id, String name, String type,
            Integer page, Integer count, boolean act) {
        Map<String, Object> map = new HashMap<String, Object>();
        if ((type != null) && (name != null))
            if (type.equals("genres")) {
                map.put("genre", name);
            } else {
                map.put("name", "%" + name + "%");
            }
        map.put("usr", id);
        map.put("page", page);
        map.put("count", count);
        map.put("act", act);
        return getSqlSession().selectList("StreamView.findByFavorites", map);
    }

    @Override
    public StreamView findStreamInfoById(Long id) {
        return getSqlSession().selectOne("StreamView.findSreamInfoById", id);
    }
}
