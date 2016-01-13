package com.netradio.entity;

public class Option extends Entity {

    private static final long serialVersionUID = 1L;

    private String key;

    private String value;

    private Long idType;

    public Long getIdType() {
        return idType;
    }

    public void setIdType(final Long idType) {
        this.idType = idType;
    }

    public Option(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    public Option() {
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
