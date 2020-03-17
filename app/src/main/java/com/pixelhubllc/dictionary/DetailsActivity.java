package com.pixelhubllc.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    private TextView wordTv;
    private TextView definitionTv;
    private TextView exampleTv;
    private TextView synonymsTv;
    private TextView antonymsTv;
    private ImageView backBtn;
    private static final String TAG = "FragmentActivity";
    private ArrayList<Model> histories;


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
        backBtn =findViewById(R.id.details__back_arrow);


        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("id", 0);
        Model model=databaseAccess.fetchdatabyId(id);

        //this is for history table
      /*  String enWords = model.getEn_words();

        databaseAccess.insertHistory(id, enWords);*/

        wordTv.setText(model.getEn_words());
        definitionTv.setText(model.getEn_defination());
        exampleTv.setText(model.getExample());
        synonymsTv.setText(model.getSynonyms());
        antonymsTv.setText(model.getAntonyms());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }
}
