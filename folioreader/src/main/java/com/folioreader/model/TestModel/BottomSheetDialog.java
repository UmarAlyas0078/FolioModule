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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    BottomSheetListner bottomSheetListner;
    Context context;
    String data;
    String body;

    public BottomSheetDialog(Context context, String data, BottomSheetListner bottomSheetListner) {
        this.context = context;
        this.data = data;
        this.bottomSheetListner = bottomSheetListner;
    }

    public BottomSheetDialog(Context context, String data, String body, BottomSheetListner bottomSheetListner) {
        this.context = context;
        this.data = data;
        this.body = body;
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
        textView.setText(data);
        note.setText(body);
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
