package com.netradio.entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String passwd;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getPasswd() {
        return passwd;
    }

    public final void setPasswd(final String passwd) {
        this.passwd = passwd;
    }

    @Override
    public final String toString() {
        return "user {id:" + getId() + " name:" + getName() + "}";
    }
}
