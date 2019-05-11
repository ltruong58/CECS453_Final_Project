package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    final int SPLASH_TIMEOUT_SECONDS = 2;
    final int SPLASH_TIME_OUT = 1000 * SPLASH_TIMEOUT_SECONDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbHelper = new DBHelper(this);
        dbHelper.resetDB();
        initExQuestionsToDB(); //Sets up the database each time the app starts with example questions.

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME_OUT); // Goes instantly to LoginActivity after 2 seconds.
    }

    void initExQuestionsToDB() { //Set up example questions, 6 examples for every level of difficulty.
        dbHelper.insertQuestion("Which fictional city is the home of Batman?","Gotham City", "New York City", "The Capitol", "Emerald City",1);
        dbHelper.insertQuestion("What's the total number of dots on a pair of dice?","42", "48", "40", "35",1);
        dbHelper.insertQuestion("Traditionally, how many Wonders of the World are there?","Seven", "Five", "Ten", "Thirteen",1);
        dbHelper.insertQuestion("Which of these celestial bodies is NOT considered a planet?","Pluto", "Mars", "Jupiter", "Venus",1);
        dbHelper.insertQuestion("According to the old proverb, to which European capital city do all roads lead?","Rome", "Venice", "Milan", "Florence",1);
        dbHelper.insertQuestion("What color is the circle of the japanese flag?","Red", "Blue", "Green", "Yellow",1);
        dbHelper.insertQuestion("What is the capital of France?","Paris", "Nice", "Lyon", "Bordeaux",2);
        dbHelper.insertQuestion("What is the chemical name for salt?","Sodium chloride (NaCl)", "Sulphuric acid (H2SO4)", "Sodium hydroxide (NaOH)", "Hydrochloric acid (HCl)",2);
        dbHelper.insertQuestion("What sign of the zodiac is represented by the ram?","Aries", "Scorpio", "Libra", "Sagittarius",2);
        dbHelper.insertQuestion("Which Roman emperor supposedly fiddled while Rome burned?","Nero", "Titus", "Caligulas", "Augustus",2);
        dbHelper.insertQuestion("Spinach is high in which nutrient?","Iron", "Sodium", "Calcium", "Omega - 3",2);
        dbHelper.insertQuestion("What is a Geiger Counter used to detect?","Radiation", "Salinity", "Alcohol levels in blood", "Air contamination",2);
        dbHelper.insertQuestion("Who was known as the Maid of Orleans?","Joan Of Arc", "Queen Elizabeth I", "Queen Elizabeth II", "Catherine The Great",3);
        dbHelper.insertQuestion("Babe Ruth is associated with which sport?","Baseball", "Soccer", "Tennis", "Curling",3);
        dbHelper.insertQuestion("In which sport would you perform the Fosbury Flop?","High Jump", "Diving", "Snowboarding", "Ski-jumps",3);
        dbHelper.insertQuestion("What is the only U.S. state that borders one other?","Maine", "Rhode Island", "Washington", "Florida",3);
        dbHelper.insertQuestion("Ganymede is the largest moon in the solar system. To which planet does it belong to?","Jupiter", "Mars", "Neptune", "Mercury",3);
        dbHelper.insertQuestion("By which name is the actor and singer Gordon Matthew Thomas Summer known for?","Sting", "Prince", "Bono", "Father John Misty",3);

        dbHelper.insertQuestion("really really really really really long question", "the right one", "the first one", "the second one", "the third one", 1);
    }

}
