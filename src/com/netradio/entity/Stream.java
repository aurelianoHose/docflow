package com.netradio.entity;

import java.math.BigDecimal;

public class Stream extends Entity {

    private static final long serialVersionUID = 1L;

    private String flow;

    private String link;

    private BigDecimal bitrade;

    private Long codecId;

    private String icon;

    private boolean actual;

    public final String getFlow() {
        return flow;
    }

    public final void setFlow(final String flow) {
        this.flow = flow;
    }

    public final String getLink() {
        return link;
    }

    public final void setLink(final String link) {
        this.link = link;
    }

    public final BigDecimal getBitrade() {
        return bitrade;
    }

    public final void setBitrade(final BigDecimal bitrade) {
        this.bitrade = bitrade;
    }

    public final Long getCodecId() {
        return codecId;
    }

    public final void setCodecId(final Long codecId) {
        this.codecId = codecId;
    }

    public final String getIcon() {
        return icon;
    }

    public final void setIcon(final String icon) {
        this.icon = icon;
    }

    public boolean getActual() {
        return actual;
    }

    public void setActual(final boolean actual) {
        this.actual = actual;
    }
}
