package com.example.finalodevvideo.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalodevvideo.databinding.FragmentAboutBinding;
import com.example.finalodevvideo.R;
import com.example.finalodevvideo.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button emailButton = binding.mail;
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_SEND);
                i1.setType("text/plain");
                i1.putExtra(Intent.EXTRA_EMAIL, new String[]{"fadime.dogrulj@gmail.com"});
                if (i1.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(i1);
                }
            }
        });

        Button linkedinButton = binding.linkedin;
        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkedinURL = "https://www.linkedin.com/in/fadimedogrul/";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(linkedinURL));
                startActivity(i2);
            }
        });

        Button githubButton = binding.github;
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String githubURL = "https://github.com/fvdime";
                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse(githubURL));
                startActivity(i3);
            }
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}