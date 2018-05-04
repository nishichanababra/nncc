package app.citta.utilities.GeneralizedModules.Localizations.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.citta.utilities.GeneralizedModules.MainActivity.Activity.MainActivity;
import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;
import app.citta.utilities.Preferences.Preferences;
import app.citta.utilities.R;

public class LocalizationForLanguages extends AppCompatActivity {

    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tv_languageselected) TextView tv_selectedlanguage;
    List<String> languages;
    private Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);

        ButterKnife.bind(this);

        inittoolbar();

        bindSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    switch (position){
                        case 0:                     // ENGLISH
                            break;

                        case 1:
                            Config.getInstance().setLanguages(Config.ENGLISH,LocalizationForLanguages.this);
                            tv_selectedlanguage.setText(getResources().getString(R.string.selectedlanguageis) + " "+ getResources().getString(R.string.english));
                            Preferences.getInstance().addSharedPreference((Activity)context,getResources().getString(R.string.UserSelectedLanguage),Config.ENGLISH);
                            inittoolbar();
                            bindSpinner();
                            break;

                        case 2:                     // HINDI
                            Config.getInstance().setLanguages(Config.HINDI,LocalizationForLanguages.this);
                            tv_selectedlanguage.setText(getResources().getString(R.string.selectedlanguageis) + " " + getResources().getString(R.string.hindi));
                            Preferences.getInstance().addSharedPreference((Activity)context,getResources().getString(R.string.UserSelectedLanguage),Config.HINDI);
                            inittoolbar();
                            bindSpinner();
                            break;

                        case 3:                     // GUJARATI
                            Config.getInstance().setLanguages(Config.GUJARATI,LocalizationForLanguages.this);
                            tv_selectedlanguage.setText(getResources().getString(R.string.selectedlanguageis) + " " + getResources().getString(R.string.gujarati));
                            Preferences.getInstance().addSharedPreference((Activity)context,getResources().getString(R.string.UserSelectedLanguage),Config.GUJARATI);
                            inittoolbar();
                            bindSpinner();
                            break;
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void inittoolbar() {
        // Find the toolbar view inside the activity layout
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.back);
        mTitle.setText(getResources().getString(R.string.localization));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void bindSpinner(){
        languages = new ArrayList<>();
        languages.add(getString(R.string.chooselanguage));
        languages.add(getResources().getString(R.string.english));
        languages.add(getResources().getString(R.string.hindi));
        languages.add(getResources().getString(R.string.gujarati));

        ArrayAdapter<String> ada = new ArrayAdapter<>(LocalizationForLanguages.this, android.R.layout.simple_spinner_item, languages);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ada);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LocalizationForLanguages.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
