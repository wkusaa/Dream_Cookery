package com.example.dream_cookery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainClassView extends AppCompatActivity {

    private ImageView menuIcon;
    private TextView westernText, malaysianText, chineseText, dessertText;
    ClassViewFragment classViewFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    //Menu Fragment
    private boolean isMenuFragmentDisplayed = false;
    private boolean isClassViewFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_view);

        //fragment
        classViewFragment = new ClassViewFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flFragment, classViewFragment).addToBackStack(null).commit();

        Bundle bundle = new Bundle();
        bundle.putString("mainText", "Western");

        classViewFragment.setArguments(bundle);
        isClassViewFragmentDisplayed = true;


        westernText = findViewById(R.id.western_text);
        malaysianText = findViewById(R.id.malaysian_text);
        chineseText = findViewById(R.id.chinese_text);
        dessertText = findViewById(R.id.dessert_text);

        menuIcon = findViewById(R.id.menuIcon);

        westernText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isClassViewFragmentDisplayed && !isMenuFragmentDisplayed)
                {
                    openWesternClassViewFragment();
                }

            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!isMenuFragmentDisplayed) {
                    openMenuFragment();

                }

                else
                    closeMenuFragment();
            }
        });

        if(savedInstanceState != null)
        {
            isMenuFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if(!isMenuFragmentDisplayed)
        {
            isClassViewFragmentDisplayed = false;
        }

        if(isClassViewFragmentDisplayed && isMenuFragmentDisplayed)
        {
            isMenuFragmentDisplayed = false;
        }


    }

    public void openWesternClassViewFragment() {
        /*Intent western = new Intent(this, WesternClass.class);
        startActivity(western);*/
        ClassViewFragment classViewFragment = new ClassViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.flFragment, classViewFragment).addToBackStack(null).commit();
        Bundle bundle = new Bundle();
        bundle.putString("mainText", "Western");

        classViewFragment.setArguments(bundle);
        isClassViewFragmentDisplayed = true;
    }

    public void openMenuFragment()
    {
        MenuFragment menuFragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.flFragment, menuFragment).addToBackStack(null).commit();
        isMenuFragmentDisplayed = true;
    }

    public void closeMenuFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MenuFragment menuFragment = (MenuFragment) fragmentManager.findFragmentById(R.id.flFragment);
        if(menuFragment != null)
        {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(menuFragment).commit();
        }
        isMenuFragmentDisplayed = false;

    }

    public void onSavedInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isMenuFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }


}