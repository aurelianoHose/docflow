package com.netradio.entity;

import java.io.Serializable;
import java.util.Date;

public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long idCreator;

    private Date added;

    private Date modified;

    private String userName;

    private boolean del;

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public Long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(final Long idCreator) {
        this.idCreator = idCreator;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(final Date added) {
        this.added = added;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(final Date modified) {
        this.modified = modified;
    }

    public boolean getDel() {
        return del;
    }

    public void setDel(final boolean del) {
        this.del = del;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
