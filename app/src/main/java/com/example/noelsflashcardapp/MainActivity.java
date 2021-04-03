package com.example.noelsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    int ADD_CARD_REQUEST_CODE = 100;
    int EDIT_CARD_REQUEST_CODE = 200;
    Flashcard cardToEdit;


    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

        allFlashcards = flashcardDatabase.getAllCards();


        if (allFlashcards != null && allFlashcards.size() > 0){
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_hint)).setText(allFlashcards.get(0).getHint());
            ((TextView) findViewById(R.id.flashcard_answer_1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.flashcard_answer_2)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.flashcard_answer_3)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        findViewById(R.id.flashcard_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (allFlashcards.size() == 0) {
                    return;
                }

                // advance our pointer index so we can show the next card
                int prevDisplayIndex = currentCardDisplayedIndex;
                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);

                while(currentCardDisplayedIndex==prevDisplayIndex){
                    currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);

                }

                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);
                allFlashcards = flashcardDatabase.getAllCards();

                // set the question and answer TextViews with data from the database
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                        ((TextView) findViewById(R.id.flashcard_hint)).setText(flashcard.getHint());
                        ((TextView) findViewById(R.id.flashcard_answer_1)).setText(flashcard.getAnswer());
                        ((TextView) findViewById(R.id.flashcard_answer_2)).setText(flashcard.getWrongAnswer1());
                        ((TextView) findViewById(R.id.flashcard_answer_3)).setText(flashcard.getWrongAnswer2());
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
            }
        });

        findViewById(R.id.flashcard_trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                if (allFlashcards.size() == 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText("No flashcards left. Create a new one!");
                    ((TextView) findViewById(R.id.flashcard_hint)).setText("Press + to create a new flashcard!");
                    ((TextView) findViewById(R.id.flashcard_answer_1)).setText("No flashcards left. Create a new one!");
                    ((TextView) findViewById(R.id.flashcard_answer_2)).setText("Press + to create a new flashcard!");
                    ((TextView) findViewById(R.id.flashcard_answer_3)).setText("No flashcards left. Create a new one!");

                    findViewById(R.id.flashcard_answer_1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer_2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer_3).setVisibility(View.INVISIBLE);
                    findViewById(R.id.page_edit).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_next).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_trash).setVisibility(View.INVISIBLE);

                } else {

                    currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);
                    Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);
                    ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                    ((TextView) findViewById(R.id.flashcard_hint)).setText(flashcard.getHint());
                    ((TextView) findViewById(R.id.flashcard_answer_1)).setText(flashcard.getAnswer());
                    ((TextView) findViewById(R.id.flashcard_answer_2)).setText(flashcard.getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer_3)).setText(flashcard.getWrongAnswer2());
                }
            }
        });

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
                // findViewById(R.id.flashcard_hint).setVisibility(View.VISIBLE);
                // findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                View questionSideView = findViewById(R.id.flashcard_question);
                View answerSideView = findViewById(R.id.flashcard_hint);

// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
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

        findViewById(R.id.page_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                MainActivity.this.startActivityForResult(intent, ADD_CARD_REQUEST_CODE);
            }
        });

        findViewById(R.id.page_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < allFlashcards.size(); i++){
                    if(allFlashcards.get(i).getQuestion().equals(((TextView) findViewById(R.id.flashcard_question)).getText().toString())){
                        cardToEdit = allFlashcards.get(i);
                    }
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("question", ((TextView) findViewById(R.id.flashcard_question)).getText().toString()); // puts one string into the Intent, with the key as 'string1'
                intent.putExtra("hint", ((TextView) findViewById(R.id.flashcard_hint)).getText().toString()); // puts another string into the Intent, with the key as 'string2
                intent.putExtra("correctAnswer", ((TextView) findViewById(R.id.flashcard_answer_1)).getText().toString());
                intent.putExtra("incorrectAnswer_1", ((TextView) findViewById(R.id.flashcard_answer_2)).getText().toString());
                intent.putExtra("incorrectAnswer_2", ((TextView) findViewById(R.id.flashcard_answer_3)).getText().toString());
                setResult(RESULT_OK, intent); // set result code and bundle data for response
                MainActivity.this.startActivityForResult(intent, EDIT_CARD_REQUEST_CODE);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            // grab the data passed from AddCardActivity
            // set the TextViews to show the new question and answer
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String hint = data.getExtras().getString("hint");
            String correctAnswer = data.getExtras().getString("correctAnswer"); // 'string1' needs to match the key we used when we put the string in the Intent
            String incorrectAnswer_1 = data.getExtras().getString("incorrectAnswer_1");
            String incorrectAnswer_2 = data.getExtras().getString("incorrectAnswer_2"); // 'string1' needs to match the key we used when we put the string in the Intent

            flashcardDatabase.insertCard(new Flashcard(question, hint, correctAnswer, incorrectAnswer_1, incorrectAnswer_2));
            allFlashcards = flashcardDatabase.getAllCards();

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer_1)).setText(correctAnswer);
            ((TextView) findViewById(R.id.flashcard_answer_2)).setText(incorrectAnswer_1);
            ((TextView) findViewById(R.id.flashcard_answer_3)).setText(incorrectAnswer_2);
            ((TextView) findViewById(R.id.flashcard_hint)).setText(hint);

            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card added successfully",
                    Snackbar.LENGTH_SHORT)
                    .show();

        } else if (requestCode == EDIT_CARD_REQUEST_CODE && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String hint = data.getExtras().getString("hint");
            String correctAnswer = data.getExtras().getString("correctAnswer"); // 'string1' needs to match the key we used when we put the string in the Intent
            String incorrectAnswer_1 = data.getExtras().getString("incorrectAnswer_1");
            String incorrectAnswer_2 = data.getExtras().getString("incorrectAnswer_2"); // 'string1' needs to match the key we used when we put the string in the Intent
            // grab the data passed from AddCardActivity
            // set the TextViews to show the EDITED question and answer

            cardToEdit.setQuestion(question);
            cardToEdit.setHint(hint);
            cardToEdit.setAnswer(correctAnswer);
            cardToEdit.setWrongAnswer1(incorrectAnswer_1);
            cardToEdit.setWrongAnswer2(incorrectAnswer_2);

            flashcardDatabase.updateCard(cardToEdit);

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer_1)).setText(correctAnswer);
            ((TextView) findViewById(R.id.flashcard_answer_2)).setText(incorrectAnswer_1);
            ((TextView) findViewById(R.id.flashcard_answer_3)).setText(incorrectAnswer_2);
            ((TextView) findViewById(R.id.flashcard_hint)).setText(hint);

            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card edited successfully",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }


        if(allFlashcards.size() == 1){
            findViewById(R.id.flashcard_answer_1).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer_2).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer_3).setVisibility(View.VISIBLE);
            findViewById(R.id.page_edit).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_next).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_trash).setVisibility(View.VISIBLE);
        }


    }
}

