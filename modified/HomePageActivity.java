package app.citta.sales365cloud;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.view.PagerAdapter;

import com.company.adapter.DataProvider;
import com.company.adapter.RecyclerAdapter;

import java.util.ArrayList;

import app.citta.sales365cloud.commons.baseclasses.BaseActivity;

public class HomePageActivity extends BaseActivity implements DataAdapter.DataAdapterClicks {

    private final String TAG = "HomePageActivity";
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts = {R.layout.help_screen1,
            R.layout.help_screen2,
            R.layout.help_screen3,
            R.layout.help_screen4};


    private ImageView img_addtocart;
    private RecyclerView recyclerView;
    private ImageView imgsearch;

    private boolean scrollStarted, checkDirection;

    private ViewPager viewPager;
    private float thresholdOffset = 0.5f;


    private String product_names[] = {"circle table lamp", "Long Canvas Lamp", "Pot Flowers", "Round Lamp"};
    private int img_recycler[] = {R.drawable.night_lamp, R.drawable.stand_lamp, R.drawable.lamp, R.drawable.round_lamp};
    private String price[] = {" 500", " 650", " 700", " 350"};
    private String description[] = {" 500", " 650", " 700", " 350"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initUIControls();

        setUIData();

        registerForListener();

        // adding bottom dots
        addBottomDots(0);

        setViewPager();

        prepareData();


    }

    private void prepareData() {

        ArrayList<DataProvider> arrayList = new ArrayList<>();
        for (int i = 0; i < product_names.length; i++) {
            DataProvider dataProvider = new DataProvider();
            dataProvider.setImgrecycler(img_recycler[i]);
            dataProvider.setName(product_names[i]);
            dataProvider.setDescription(description[i]);
            dataProvider.setPrice(price[i]);

            arrayList.add(dataProvider);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Log.d(TAG, "HomePageActivity: prepareData");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        Log.d(TAG, "HomePageActivity: " + arrayList.size());
        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, GridLayoutManager.VERTICAL));
        recyclerView.setNestedScrollingEnabled(true);
    }

    private void setViewPager() {
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkDirection) {
                    if (thresholdOffset > positionOffset) {
                        Log.d(TAG, "going right " + position);
                        if (position == 3) {
                            navigateToActivity(HomePageActivity.class, true);
                        }
                    } else {
                        Log.d(TAG, "going left " + position);

                    }
                    checkDirection = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                } else {
                    scrollStarted = false;
                }
            }
        });


    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }//end of addBottomDots

    void initUIControls() {
        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.layoutDots);
        img_addtocart = findViewById(R.id.img_addtocart);
        imgsearch = findViewById(R.id.imgsearch);


    }//end of initUIControls

    @Override
    protected void initTopBarComponents() {
        img_addtocart.setVisibility(View.VISIBLE);
    }

    @Override
    void registerForListener() {
        img_addtocart.setOnClickListener(this);
        imgsearch.setOnClickListener(this);
    }

    void setUIData() {

    }//end of setUIData

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }//end of getItem

    @Override
    public void onItemViewClick(String img, String name, String description) {

        DataProvider dataProvider1=this.dataProviders.get(position);
        Intent intent = new Intent(ctx,ImageDescription.class);
        intent.putExtra("img",dataProvider1.getImage_id());
        intent.putExtra("name",dataProvider1.getName());
        intent.putExtra("description",dataProvider1.getDescription());
        this.ctx.startActivity(intent);
    }


    private class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {

        }

        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);

            return view;
        }//end of instantiateItem


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_addtocart:

                break;

            case R.id.imgsearch:
                navigateToActivity(SearchProductActivity.class, false);
                break;
        }
    }



}
