package com.example.fight_corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class dodon_class extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodon_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }


    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Wear Mask");
        m.setDescription("Wear a mask. The patient should wear a facemask around you and others.\n" +
                " If they can’t (for example, because it causes trouble breathing), \n" +
                "you should wear a mask when you are in the same room as the patient. \n" +
                "Also wear a disposable facemask and gloves when you have contact with the patient’s body fluids,\n" +
                " such as saliva, sputum, nasal mucus, vomit, urine, blood and stool. Throw out disposable facemasks and gloves after using them.");
        m.setImg(R.drawable.mask);
        models.add(m);

        m = new Model();
        m.setTitle("Take Rest");
        m.setDescription("Help the patient to rest at home. Help with basic needs in the \n" +
                "home, and provide support for getting groceries, prescriptions, and other personal needs.");
        m.setImg(R.drawable.rest);
        models.add(m);

        m = new Model();
        m.setTitle("Don't Touch");
        m.setDescription("Avoid touching your eyes, nose, and mouth with unwashed hands.");
        m.setImg(R.drawable.touch);
        models.add(m);

        m = new Model();
        m.setTitle("Wash Hands");
        m.setDescription("TWash your hands often. Use soap and water for at least 20 seconds.\n" +
                " Or use an alcohol-based hand sanitizer that contains at least 60% alcohol.\n" +
                " For visible soiling, soap and water are best.");
        m.setImg(R.drawable.wash);
        models.add(m);

        m = new Model();
        m.setTitle("Safe Distance");
        m.setDescription("Stay physically away from the patient as much as possible. \n" +
                "Household members should stay in another room or be separated from the patient as much as possible, \n" +
                "keep the door to the patient’s room closed, and use a separate bedroom and bathroom, if available.\n" +
                " Visitors should not be allowed and patients should stay away from pets.");
        m.setImg(R.drawable.safedistance);
        models.add(m);

        m = new Model();
        m.setTitle("Flat Curve");
        m.setDescription("The flattenning of curve will improve the current condition \n" +
                "and can help in focusing the health of poor people.");
        m.setImg(R.drawable.curveflat);
        models.add(m);

        m = new Model();
        m.setTitle("Google");
        m.setDescription("To prevent the spread of COVID-19:\n" +
                "• Clean your hands often. Use soap and water, or an alcohol-based hand rub.\n" +
                "• Maintain a safe distance from anyone who is coughing or sneezing.\n" +
                "• Don’t touch your eyes, nose or mouth.\n" +
                "• Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze.\n" +
                "• Stay home if you feel unwell.\n" +
                "• If you have a fever, cough and difficulty breathing, seek medical attention. Call in advance.\n" +
                "• Follow the directions of your local health authority.");
        m.setImg(R.drawable.google);
        models.add(m);

        return models;

    }
}
