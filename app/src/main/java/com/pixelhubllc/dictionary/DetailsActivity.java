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

public class DetailsActivity extends AppCompatActivity {
    private TextView wordTv;
    private TextView definitionTv;
    private TextView exampleTv;
    private TextView synonymsTv;
    private TextView antonymsTv;
    private ImageView backBtn;
    private static final String TAG = "FragmentActivity";


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
        String wordActivityConfirm = mIntent.getStringExtra("wordActivity");
        String searchActivityConfirm = mIntent.getStringExtra("searchActivity");
        Model model=databaseAccess.fetchdatabyId(id);

        //when user click back button and to confirm which is her recent activity

        Log.d(TAG, "activity: " + wordActivityConfirm);
        Toast.makeText(this, wordActivityConfirm, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, searchActivityConfirm, Toast.LENGTH_SHORT).show();
//        String searchActivityConfirm= mIntent.getStringExtra("searchActivity");
//        Log.d(TAG, "onCreate: " + searchActivityConfirm.toString());

        wordTv.setText(model.getEn_words());
        definitionTv.setText(model.getEn_defination());
        exampleTv.setText(model.getExample());
        synonymsTv.setText(model.getSynonyms());
        antonymsTv.setText(model.getAntonyms());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
