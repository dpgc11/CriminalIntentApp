package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageDialog extends DialogFragment {

    private ImageView imageView;
    private static String imagePath;
    private static Context con;

    public static void getImage(String path, Context context){
        imagePath = path;
        con = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_image_dialog, container, false);
        imageView = (ImageView) view.findViewById(R.id.iv_image_dialog);
        Bitmap bitmap = PictureUtils.getScaledBitmap(imagePath,getActivity());
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

}