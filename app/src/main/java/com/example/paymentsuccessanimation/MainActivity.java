package com.example.paymentsuccessanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button paymentButton;
    private ImageView successImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paymentButton = findViewById(R.id.paymentButton);
        successImageView = findViewById(R.id.successImageView);

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performPayment();
            }
        });
    }

    private void performPayment() {
        paymentButton.setEnabled(false);
        successImageView.setVisibility(View.VISIBLE);

        // Create animators for scale, rotation, and alpha effects
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(successImageView, "scaleX", 0f, 1f);
        scaleXAnimator.setDuration(1000);
        scaleXAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(successImageView, "scaleY", 0f, 1f);
        scaleYAnimator.setDuration(1000);
        scaleYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(successImageView, "rotation", 0f, 360f);
        rotationAnimator.setDuration(1000);
        rotationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(successImageView, "alpha", 0f, 1f);
        alphaAnimator.setDuration(1000);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Create an animation set and add the animators
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, rotationAnimator, alphaAnimator);


        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                // Animation completed, reset the UI
                successImageView.setVisibility(View.VISIBLE);
                paymentButton.setEnabled(true);
            }
        });

        // Start the animation
        animatorSet.start();
    }
}
