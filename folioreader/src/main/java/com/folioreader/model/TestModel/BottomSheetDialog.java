package com.folioreader.model.TestModel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.folioreader.R;
import com.folioreader.model.HighlightImpl;
import com.folioreader.ui.fragment.FolioPageFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    BottomSheetListner bottomSheetListner;
    Context context;
    String data;
    String body;
    String mEdit;

    public BottomSheetDialog(Context context, String data,String mEdit, BottomSheetListner bottomSheetListner) {
        this.context = context;
        this.data = data;
        this.mEdit = mEdit;
        this.bottomSheetListner = bottomSheetListner;
    }

    public BottomSheetDialog(Context context, String data, String body,String mEdit, BottomSheetListner bottomSheetListner) {
        this.context = context;
        this.data = data;
        this.body = body;
        this.mEdit = mEdit;
        this.bottomSheetListner = bottomSheetListner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.botom_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_show_message);
        final EditText note = view.findViewById(R.id.add_note);
        Button button = view.findViewById(R.id.button1);
        TextView button2 = view.findViewById(R.id.tv_cancel);
        textView.setText(data);
        note.setText(body);
        button2.setText(mEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetListner.BottomSheetClick(note.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bottomSheetListner = (BottomSheetListner) context;
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
    }

    public interface BottomSheetListner {
        void BottomSheetClick(String s);
    }
}
