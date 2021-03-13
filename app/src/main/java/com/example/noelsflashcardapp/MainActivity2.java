package com.example.noelsflashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String question = getIntent().getStringExtra("question"); // 'string1' needs to match the key we used when we put the string in the Intent
        String hint = getIntent().getStringExtra("hint");
        String correctAnswer = getIntent().getStringExtra("correctAnswer"); // 'string1' needs to match the key we used when we put the string in the Intent
        String incorrectAnswer_1 = getIntent().getStringExtra("incorrectAnswer_1");
        String incorrectAnswer_2 = getIntent().getStringExtra("incorrectAnswer_2"); // 'string1' needs to match the key we used when we put the string in the Intent

        ((EditText) findViewById(R.id.question_edit_text)).setText(question);
        ((EditText) findViewById(R.id.correct_answer_edit_text)).setText(correctAnswer);
        ((EditText) findViewById(R.id.incorrect_answer_1_edit_text)).setText(incorrectAnswer_1);
        ((EditText) findViewById(R.id.incorrect_answer_2_edit_text)).setText(incorrectAnswer_2);
        ((EditText) findViewById(R.id.hint_edit_text)).setText(hint);

        findViewById(R.id.page_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.page_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.question_edit_text)).getText().toString(); // 'string1' needs to match the key we used when we put the string in the Intent
                String hint = ((EditText) findViewById(R.id.hint_edit_text)).getText().toString();
                String correctAnswer = ((EditText) findViewById(R.id.correct_answer_edit_text)).getText().toString(); // 'string1' needs to match the key we used when we put the string in the Intent
                String incorrectAnswer_1 = ((EditText) findViewById(R.id.incorrect_answer_1_edit_text)).getText().toString();
                String incorrectAnswer_2 = ((EditText) findViewById(R.id.incorrect_answer_2_edit_text)).getText().toString(); // 'string1' needs to match the key we used when we put the string in the Intent

                if( question.isEmpty() || hint.isEmpty() || correctAnswer.isEmpty() || incorrectAnswer_1.isEmpty() || incorrectAnswer_2.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "At least one field is empty!", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("question", question); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("hint", hint); // puts another string into the Intent, with the key as 'string2
                data.putExtra("correctAnswer", correctAnswer);
                data.putExtra("incorrectAnswer_1", incorrectAnswer_1);
                data.putExtra("incorrectAnswer_2", incorrectAnswer_2);
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish();
            }
        });

    }



}