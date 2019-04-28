package com.example.minggupertama;

public class Gallery {
    String name;
    String file;
    String id;

    public Gallery(){}

    public Gallery(String name, String file, String id) {
        this.name = name;
        this.file = file;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
