package com.example.mother_portal.modules;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mother_portal.R;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoAndDoc extends AppCompatActivity {

    private TextView tvTitle, tvSteps;
    private VideoView videoView;
    private WebView webViewDoc;
    private Button btnStep, btnDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_and_doc);

        tvTitle = findViewById(R.id.tvTitle);
        tvSteps = findViewById(R.id.tvSteps);
        videoView = findViewById(R.id.videoView);
        webViewDoc = findViewById(R.id.webViewDoc);
        btnStep = findViewById(R.id.btnStep);
        btnDoc = findViewById(R.id.btnDoc);

        // Get the title passed from previous activity
        String title = getIntent().getStringExtra("title");
        tvTitle.setText(title);

        // Fetch video URL from API based on title
        fetchVideoUrl(title);

        // Button click listeners
        btnStep.setOnClickListener(v -> {
            showSteps(title);
            // Toggle button colors
            btnStep.setBackgroundTintList(getColorStateList(R.color.activeButton));
            btnDoc.setBackgroundTintList(getColorStateList(R.color.inactiveButton));
            btnStep.setTextColor(getColor(R.color.buttonText));
            btnDoc.setTextColor(getColor(R.color.buttonText));
        });

        btnDoc.setOnClickListener(v -> {
            showDoc(title);
            // Toggle button colors
            btnDoc.setBackgroundTintList(getColorStateList(R.color.activeButton));
            btnStep.setBackgroundTintList(getColorStateList(R.color.inactiveButton));
            btnDoc.setTextColor(getColor(R.color.buttonText));
            btnStep.setTextColor(getColor(R.color.buttonText));
        });

    }

    // Fetch video URL from API
    private void fetchVideoUrl(String title) {
        String apiUrl = "https://la764x5fih.execute-api.us-east-2.amazonaws.com/video-url";

        try {
            JSONObject payload = new JSONObject();
            payload.put("course_name", title); // You can change based on your API requirement
            payload.put("module_name", title);

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, apiUrl, payload,
                    response -> {
                        try {
                            String videoUrl = response.getString("video_url");
                            playVideo(videoUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> error.printStackTrace());

            queue.add(request);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Play video in VideoView with MediaController
    private void playVideo(String videoUrl) {
        videoView.setVideoURI(Uri.parse(videoUrl));
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();
    }

    // Show steps
    private void showSteps(String title) {
        String steps = getStepsForTitle(title);
        tvSteps.setText(steps);
        tvSteps.setVisibility(View.VISIBLE);
        webViewDoc.setVisibility(View.GONE);
    }

    // Show doc in WebView (inside app)
    private void showDoc(String title) {
        String docUrl = getDocUrlForTitle(title);
        if (docUrl != null) {
            WebSettings webSettings = webViewDoc.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webViewDoc.setWebViewClient(new WebViewClient());
            webViewDoc.loadUrl(docUrl);
            webViewDoc.setVisibility(View.VISIBLE);
            tvSteps.setVisibility(View.GONE);
        }
    }

    // Map title to document URL
    private String getDocUrlForTitle(String title) {
        switch (title) {
            case "How to create EC2 Instance?":
                return "https://documents.softmania.in/external/manual/splunk-enterprise-installation/article/create-linux-ec2-instances?p=8925397ddf335351a1488377eefdb7c5f503122ba192f28b39fd9fc30f3ab690";
            default:
                return null;
        }
    }

    // Map title to steps
    private String getStepsForTitle(String title) {
        switch (title) {
            case "How to create EC2 Instance?":
                return "Step 1: Login to AWS Console\nStep 2: Navigate to EC2 Dashboard\nStep 3: Launch Instance\nStep 4: Configure Instance Details\nStep 5: Review and Launch";
            default:
                return "No steps available";
        }
    }



}
