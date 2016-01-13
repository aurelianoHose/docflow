package com.netradio.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.netradio.dao.RoleDao;
import com.netradio.entity.Role;

public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {

    @Override
    public List<Role> findAll() {
        return getSqlSession().selectList("Role.findAll");
    }

    @Override
    public List<Role> findByUserId(final Long id) {
        return getSqlSession().selectList("Role.findByUserId", id);
    }

    @Override
    public void addUserRole(final Long uid, final Long rid) {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("uid", uid);
        map.put("rid", rid);
        getSqlSession().insert("Role.add", map);
    }

    @Override
    public void deleteUserRoles(final Long uid) {
        getSqlSession().delete("Role.delete", uid);
    }

}
