package com.example.myrecipe;

import java.io.File;

public class recipess {
    private String recipename;
    private File File;

    public recipess(String recipename, File uri) {
        this.recipename = recipename;
        this.File = File;
    }

    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public File getUrl() {
        return File;
    }

    public void setUrl(File File) {
        this.File = File;
    }
}
