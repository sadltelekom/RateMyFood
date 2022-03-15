package com.zeegermans.RateMyFood.model;

public class Picture {
    private long id;
    private String link;

    public Picture(String link) {
        this.link = link;
    }

    public Picture(long id, String link) {
        this.id = id;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
