package com.folioreader.model.TestModel;

import java.util.ArrayList;

public class GlobalArray {
    public static ArrayList<NoteModel> noteModels = new ArrayList<>();

    public static ArrayList<NoteModel> getNoteModels() {
        return noteModels;
    }
}
