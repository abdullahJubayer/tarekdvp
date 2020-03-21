package com.pixelhubllc.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pixelhubllc.dictionary.adapter.SearchSuggestionAdapter;
import com.pixelhubllc.dictionary.database.DatabaseAccess;
import com.pixelhubllc.dictionary.model.Model;

import java.util.ArrayList;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {
    private TextView wordTv;
    private TextView definitionTv;
    private TextView exampleTv;
    private TextView synonymsTv;
    private TextView antonymsTv;
    private ImageView backBtn;
    private ImageView bookmarkBtn;
    private static final String TAG = "FragmentActivity";
    private ArrayList<Model> histories;
    private DatabaseAccess databaseAccess;
    private Model model;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        histories = databaseAccess.getSearchHistory();
//        databaseAccess.close();

        ArrayList<String> word=new ArrayList<>();
        for (Model model:histories){
            word.add(model.getEn_words());
        }

        Log.d(TAG, "onCreate: " + histories.toString());

        wordTv = findViewById(R.id.details_word_Tv);
        definitionTv = findViewById(R.id.definition_tv);
        exampleTv = findViewById(R.id.example_tv);
        synonymsTv = findViewById(R.id.synonyms_tv);
        antonymsTv = findViewById(R.id.antonyms_tv);
        backBtn =findViewById(R.id.details__back_arrow);
        bookmarkBtn = findViewById(R.id.details_favourite_iv);


        Intent mIntent = getIntent();
        final int id = mIntent.getIntExtra("id", 0);
        String name = mIntent.getStringExtra("class");
        model=databaseAccess.fetchdatabyId(id);

        //this is for history table
        if (name != null && name.equals("SearchSuggestionAdapter")){
            String enWords = model.getEn_words();
            long result=databaseAccess.insertHistory(id, enWords);
            Log.e("Status",result+"");
        }


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

        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enWords = model.getEn_words();
                long result=databaseAccess.insertBookmark(id, enWords);
                Toast.makeText(DetailsActivity.this, "Bookmark added", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView btnSpeak = findViewById(R.id.listen_icon);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(DetailsActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        // TODO Auto-generated method stub
                        if(status == TextToSpeech.SUCCESS){
                            int result=tts.setLanguage(Locale.getDefault());
                            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                                Log.e("error", "This Language is not supported");
                            }
                            else{
                                String enWords = model.getEn_words();
                                tts.speak(enWords, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                        else
                            Log.e("error", "Initialization Failed!");
                    }
                });
            }
        });
    }
}
