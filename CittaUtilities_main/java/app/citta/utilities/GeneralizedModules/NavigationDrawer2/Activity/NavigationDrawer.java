package app.citta.utilities.GeneralizedModules.NavigationDrawer2.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.citta.utilities.utilities.Config;
import app.citta.utilities.GeneralizedModules.NavigationDrawer.Fragments.FragmentDrawer;
import app.citta.utilities.GeneralizedModules.NavigationDrawer.Fragments.HomeFragment;
import app.citta.utilities.R;

/**
 * Created by ws-16 on 7/17/2017.
 */

public class NavigationDrawer extends AppCompatActivity implements
        FragmentDrawer.FragmentDrawerListener {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        initDrawer();
        displayViewStudent(0);
    }

    /*
     *   Initialize navigation drawer components
     * */
    public void initDrawer() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FragmentDrawer drawerFragmentSTUDENT = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragmentSTUDENT.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragmentSTUDENT.setDrawerListener(this);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (Config.getInstance().isInternetAvailable(NavigationDrawer.this)) {
            displayViewStudent(position);
        } else {
            Config.getInstance().GlobalInternetDialog( NavigationDrawer.this);
        }
    }

    /* ***********************************  BINDING ITEMS OF NAVIGATION DRAWER FOR STUDENT  ************************************* */
    private void displayViewStudent(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = "Home";
                break;

            case 1:
                fragment = new HomeFragment();
                title = "Profile";
                break;

            case 2:
                fragment = new HomeFragment();
                title = "Timetable";
                break;

            case 3:
                fragment = new HomeFragment();
                title = "Exam Schedule";
                break;

            case 4:
                fragment = new HomeFragment();
                title = "Exam Result";
                break;

            case 5:
                fragment = new HomeFragment();
                title = "Attendance";
                break;

            case 6:
                fragment = new HomeFragment();
                title = "Product History";
                break;

            case 7:
                fragment = new HomeFragment();
                title = "Change Password";
                break;

            case 8:
                AlertDialog.Builder builder = new AlertDialog.Builder(NavigationDrawer.this);
                builder.setMessage(getResources().getString(R.string.logout));

                String positiveText = getString(android.R.string.ok);
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                String negativeText = getString(android.R.string.cancel);
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
            }
        }
    }
}
