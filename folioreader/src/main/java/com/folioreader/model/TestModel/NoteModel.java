package com.folioreader.model.TestModel;

public class NoteModel {
    private String mTitle;
    private String mBody;
    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;
    private long mhighlightId;

    public NoteModel(String mTitle, String mBody, long mhighlightId) {
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mhighlightId = mhighlightId;
    }

    public long getMhighlightId() {
        return mhighlightId;
    }

    public void setMhighlightId(long mhighlightId) {
        this.mhighlightId = mhighlightId;
    }

    public int getmLeft() {
        return mLeft;
    }

    public void setmLeft(int mLeft) {
        this.mLeft = mLeft;
    }

    public int getmTop() {
        return mTop;
    }

    public void setmTop(int mTop) {
        this.mTop = mTop;
    }

    public int getmRight() {
        return mRight;
    }

    public void setmRight(int mRight) {
        this.mRight = mRight;
    }

    public int getmBottom() {
        return mBottom;
    }

    public void setmBottom(int mBottom) {
        this.mBottom = mBottom;
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
