package com.example.noelsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_answer_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setBackgroundColor(getResources().getColor(R.color.green));

            }
        });

        findViewById(R.id.flashcard_answer_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setBackgroundColor(getResources().getColor(R.color.green));
                findViewById(R.id.flashcard_answer_2).setBackgroundColor(getResources().getColor(R.color.red));

            }
        });

        findViewById(R.id.flashcard_answer_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setBackgroundColor(getResources().getColor(R.color.green));
                findViewById(R.id.flashcard_answer_3).setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setBackgroundColor(getResources().getColor(R.color.teal_200));
                findViewById(R.id.flashcard_answer_2).setBackgroundColor(getResources().getColor(R.color.teal_200));
                findViewById(R.id.flashcard_answer_3).setBackgroundColor(getResources().getColor(R.color.teal_200));
            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_hint).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.flashcard_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_hint).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.toggle_choices_invisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer_2).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer_3).setVisibility(View.INVISIBLE);
                findViewById(R.id.toggle_choices_invisibility).setVisibility(View.INVISIBLE);
                findViewById(R.id.toggle_choices_visibility).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer_1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer_2).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer_3).setVisibility(View.VISIBLE);
                findViewById(R.id.toggle_choices_invisibility).setVisibility(View.VISIBLE);
                findViewById(R.id.toggle_choices_visibility).setVisibility(View.INVISIBLE);
            }
        });

    }
}