package com.pixelhubllc.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

public class DetailsActivity extends AppCompatActivity {
    private TextView wordTv;
    private TextView definitionTv;
    private TextView exampleTv;
    private TextView synonymsTv;
    private TextView antonymsTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DatabaseAccess databaseAccess;
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        wordTv = findViewById(R.id.details_word_Tv);
        definitionTv = findViewById(R.id.definition_tv);
        exampleTv = findViewById(R.id.example_tv);
        synonymsTv = findViewById(R.id.synonyms_tv);
        antonymsTv = findViewById(R.id.antonyms_tv);


        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("id", 0);
        Model model=databaseAccess.fetchdatabyId(id);

        wordTv.setText(model.getEn_words());
        definitionTv.setText(model.getEn_defination());
        exampleTv.setText(model.getExample());
        synonymsTv.setText(model.getSynonyms());
        antonymsTv.setText(model.getAntonyms());
    }
}
