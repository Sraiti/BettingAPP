package com.example.bettingapp.ui.Contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bettingapp.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final Button sendEmail = root.findViewById(R.id.sendEmail);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+getResources().getString(R.string.Emailrecerver)));
                intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.Emailrecerver));
                intent.putExtra(Intent.EXTRA_SUBJECT, "");

                startActivity(intent);

            }
        });

        return root;
    }


}