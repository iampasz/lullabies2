package com.appsforkids.pasz.lullabies.RealmObjects;

import io.realm.RealmObject;

public class AudioFile extends RealmObject {

    int id;
    public int nameSong;
    public int authorSong;
    Boolean status = false;
    int position;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameSong(int nameSong) {
        this.nameSong = nameSong;
    }

    public int getId() {
        return id;
    }

    public int getNameSong() {
        return nameSong;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setAuthorSong(int authorSong) {
        this.authorSong = authorSong;
    }

    public int getAuthorSong() {
        return authorSong;
    }
}
