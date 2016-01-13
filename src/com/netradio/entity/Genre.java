package com.netradio.entity;

public class Genre extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
