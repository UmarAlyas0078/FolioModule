package com.folioreader.model.TestModel;

public class NoteModel {
    private String mTitle;
    private String mBody;

    public NoteModel(String mTitle, String mBody) {
        this.mTitle = mTitle;
        this.mBody = mBody;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }
}
