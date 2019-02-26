package com.mandaliyamedicals.medical;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.AccessToken;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mandaliyamedicals.medical.adapters.DashboardBannersViewPagerAdapter;
import com.mandaliyamedicals.medical.adapters.DashboardOptionAdapter;
import com.mandaliyamedicals.medical.adapters.DashboardPopularAdapter;
import com.mandaliyamedicals.medical.adapters.DashboardSeasonalAdapter;
import com.mandaliyamedicals.medical.adapters.DashboardTopSellingAdapter;
import com.mandaliyamedicals.medical.adapters.ProductCategoryAdapter;
import com.mandaliyamedicals.medical.databinding.DashboardBinding;
import com.mandaliyamedicals.medical.generalHelper.AppUtil;
import com.mandaliyamedicals.medical.generalHelper.Constants;
import com.mandaliyamedicals.medical.generalHelper.DialogUtil;
import com.mandaliyamedicals.medical.generalHelper.GH;
import com.mandaliyamedicals.medical.generalHelper.L;
import com.mandaliyamedicals.medical.generalHelper.RVLayoutManager;
import com.mandaliyamedicals.medical.generalHelper.SP;
import com.mandaliyamedicals.medical.generalHelper.URLs;
import com.mandaliyamedicals.medical.interfaces.AlertDialogListener;
import com.mandaliyamedicals.medical.medicineReminder.activities.ReminderActivity;
import com.mandaliyamedicals.medical.models.LoginResultInfo;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardBanner;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardCategoryList;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardInfo;
import com.mandaliyamedicals.medical.models.dashbaord.PopularProduct;
import com.mandaliyamedicals.medical.models.dashbaord.SeasonalProductList;
import com.mandaliyamedicals.medical.models.dashbaord.TopSellingProduct;
import com.mandaliyamedicals.medical.retrofit.RetrofitBuilder;
import com.mandaliyamedicals.medical.retrofit.RetrofitCallback;
import com.mandaliyamedicals.medical.userActivities.AddressListActivity;
import com.mandaliyamedicals.medical.userActivities.AskPharmacistActivity;
import com.mandaliyamedicals.medical.userActivities.EditProfileActivity;
import com.mandaliyamedicals.medical.userActivities.FaqActivity;
import com.mandaliyamedicals.medical.userActivities.LabTest.Activities.LabOrderListActivity;
import com.mandaliyamedicals.medical.userActivities.LabTest.Activities.LabTestActivity;
import com.mandaliyamedicals.medical.userActivities.LoginActivity;
import com.mandaliyamedicals.medical.userActivities.NotificationActivity;
import com.mandaliyamedicals.medical.userActivities.OrderListActivity;
import com.mandaliyamedicals.medical.userActivities.PrivacyPolicyActivity;
import com.mandaliyamedicals.medical.userActivities.ProductCategoryActivity;
import com.mandaliyamedicals.medical.userActivities.ProductKitActivity;
import com.mandaliyamedicals.medical.userActivities.ProductSearchActivity;
import com.mandaliyamedicals.medical.userActivities.RefillOrderListActivity;
import com.mandaliyamedicals.medical.userActivities.RequestMedicineActivity;
import com.mandaliyamedicals.medical.userActivities.SettingActivity;
import com.mandaliyamedicals.medical.userActivities.TermConditionActivity;
import com.mandaliyamedicals.medical.userActivities.UploadPrescriptionActivity;
import com.mandaliyamedicals.medical.userActivities.UploadPrescriptionHistoryActivity;
import com.mandaliyamedicals.medical.userActivities.WalletActivity;
import com.mandaliyamedicals.medical.userActivities.WebViewActivity;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.searchDoctor.DoctorActivity;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.searchDoctor.DoctorAppointmentListActivity;
import com.mandaliyamedicals.medical.userFragments.DashboardBannerFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import uk.co.deanwild.materialshowcaseview.shape.CircleShape;

public class Dashboard extends CustomAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, GoogleApiClient.OnConnectionFailedListener {
    private static final String SHOWCASE_ID = "sequence example";
    private Context mContext;
    private DashboardBinding binding;
    private AlertDialogListener mAlertDialogListener = new AlertDialogListener() {
        @Override
        public void onAlertDialogEventChanged(boolean isPositive) {
            if (isPositive) {
                if (AppUtil.isConnected(mContext)) {
                    requestToLogoutUser();
                }
            }
        }
    };

    private Handler dashboardBannerHandler;
    private Runnable dashboardBannerThread;
    private int DASHBOARD_BANNER_CURRENT_PAGE;
    private int DASHBOARD_BANNER_SLIDER_PAGE;
    private ArrayList<Fragment> mDashboardBannerFragments;
    private List<DashboardBanner> mDashboardBannerInfoList;
    private List<TopSellingProduct> mTopSellingProductInfos;
    private List<PopularProduct> mPopularProductInfos;
    private List<DashboardCategoryList> mDashboardCategoryLists;
    private List<SeasonalProductList> mSeasonalProductInfos;
    private NavigationView navigationView;
    private TextView txtHeaderUserName;
    private TextView txtHeaderUserMobile;
    private TextView txtHeaderUserEmail;
    private ImageView imgHeaderUserProfile;
    private Boolean isFabOpen = true;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private DashboardOptionAdapter.OnMyOptionItemClickListener mDashboardOptionItemClickListener = new DashboardOptionAdapter.OnMyOptionItemClickListener() {

        @Override
        public void onMyOptionItemClicked(int position) {
            switch (position) {
                case 0:
                    navigateToRefillOrderPage();
                    break;
                case 1:
                    startActivity(new Intent(mContext, ReminderActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(mContext, AskPharmacistActivity.class));
                    break;
            }
        }
    };

    GoogleSignInClient googleSignInClient;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard);

       /* if(AccessToken.getCurrentAccessToken() == null){
            gotoLoginScreen();
        }*/
        implementToolbar(binding.includeDashboard.includeToolbar.mToolBar,
                binding.includeDashboard.includeToolbar.rvMedicineCart,
                binding.includeDashboard.includeToolbar.rvNotification,
                binding.includeDashboard.includeToolbar.txtMedicineCart,
                binding.includeDashboard.includeToolbar.imgSearchMedicine);
        setNavigationDrawerViewSupport();


        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        binding.includeDashboard.fb.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /* presentShowcaseSequence();*/
            requestToGetNotificationCount();
        }

        /*animateFAB();*/


        RVLayoutManager.setHorizaontaLayoutManager(mContext, binding.includeDashboard.rvSeasonalProducts);
        RVLayoutManager.setHorizaontaLayoutManager(mContext, binding.includeDashboard.rvTopSellingProducts);
        RVLayoutManager.setHorizaontaLayoutManager(mContext, binding.includeDashboard.rvPopularProducts);
        RVLayoutManager.setHorizaontaLayoutManager(mContext, binding.includeDashboard.rvCategoryList);
        RVLayoutManager.setHorizaontaLayoutManager(mContext, binding.includeDashboard.rvDashboardOptions);

        binding.includeDashboard.rvDashboardOptions.setAdapter(new DashboardOptionAdapter(mContext, mDashboardOptionItemClickListener));

        Bundle extras = getIntent().getExtras();
       /* if (extras != null) {
            if (getIntent().getStringExtra("feedback").equalsIgnoreCase("feedback")) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.feedback_email));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Feedback here...");


                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }*/
        if (AppUtil.isConnected(mContext)) {
            requestToGetDashbaordData();
        }

        binding.includeDashboard.vpMedicalOffers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                DASHBOARD_BANNER_CURRENT_PAGE = position;

                for (int i = 0; i < binding.includeDashboard.llOfferImageDots.getChildCount(); i++) {
                    if (i == position) {
                        binding.includeDashboard.llOfferImageDots.getChildAt(i).setSelected(true);
                    } else {
                        binding.includeDashboard.llOfferImageDots.getChildAt(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        binding.includeDashboard.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AppUtil.isConnected(mContext)) {
                    requestToGetDashbaordData();
                    requestToGetNotificationCount();

                    if (binding.includeDashboard.swipeRefreshLayout.isRefreshing()) {
                        binding.includeDashboard.swipeRefreshLayout.setRefreshing(false);
                    }
                } else {
                    if (binding.includeDashboard.swipeRefreshLayout.isRefreshing()) {
                        binding.includeDashboard.swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        });

        binding.includeDashboard.fbFacebook.setOnClickListener(this);
        binding.includeDashboard.llBlogs.setOnClickListener(this);
        binding.includeDashboard.llTestimonials.setOnClickListener(this);
        binding.includeDashboard.includeToolbar.llSearchMedicine.setOnClickListener(this);
        binding.includeDashboard.llUploadPrescription.setOnClickListener(this);
        binding.includeDashboard.fbDoctor.setOnClickListener(this);
        binding.includeDashboard.fbLabTest.setOnClickListener(this);
        binding.includeDashboard.fbReqMedicine.setOnClickListener(this);

    }

   /* private void gotoLoginScreen() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void presentShowcaseSequence() {
       /* new MaterialShowcaseView.Builder(this)
                .setTarget(binding.includeDashboard.llSearchMedicine)
                .setDismissText("GOT IT")
                .setContentText("This is some amazing feature you should know about")
                .setDelay(500) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
                .withRectangleShape()
                .show();*/


        ShowcaseConfig config = new ShowcaseConfig();
        config.setMaskColor(getColor(R.color.colorPrimaryInde));
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setConfig(config);

        config.setShape(new CircleShape());
        sequence.addSequenceItem(binding.includeDashboard.fbGoogle,
                "This is button three", "GOT IT");
        config.setShape(new CircleShape());
        sequence.addSequenceItem(binding.includeDashboard.fbFacebook,
                "This is button three", "GOT IT");

        sequence.start();
    }

    private void setNavigationDrawerViewSupport() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includeDashboard.includeToolbar.mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);

        navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        txtHeaderUserName = headerview.findViewById(R.id.txtHeaderUserName);
        txtHeaderUserMobile = headerview.findViewById(R.id.txtHeaderUserMobile);
        txtHeaderUserEmail = headerview.findViewById(R.id.txtHeaderUserEmail);
        imgHeaderUserProfile = headerview.findViewById(R.id.imgHeaderUserProfile);

       /* String profileUserID = returnValueFromBundle(LoginActivity.PROFILE_USER_ID);
        String profileName = returnValueFromBundle(LoginActivity.PROFILE_FIRST_NAME);
        String profileImageUrl = returnValueFromBundle(LoginActivity.PROFILE_IMAGE_URL);

        txtHeaderUserName.setText(profileName);
        txtHeaderUserEmail.setText(profileUserID);
        Picasso.with(mContext).load(profileImageUrl).into(imgHeaderUserProfile);*/

        //DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       /* Field mDragger = null;//mRightDragger for right obviously
        try {
            mDragger = binding.drawerLayout.getClass().getDeclaredField("mLeftDragger");
            mDragger.setAccessible(true);
            ViewDragHelper draggerObj = (ViewDragHelper) mDragger.get(binding.drawerLayout);
            Field mEdgeSize = draggerObj.getClass().getDeclaredField("mEdgeSize");
            mEdgeSize.setAccessible(true);
            int edge = mEdgeSize.getInt(draggerObj);
            mEdgeSize.setInt(draggerObj, edge * 5);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    private void requestToGetNotificationCount() {
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), SP.getPreferences(mContext, SP.USER_ID));


        showProgress(getString(R.string.requesting), false);

        Call<ResponseBody> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .notificationCount(userId);
        arrayListCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissProgress();
                try {
                    JSONObject jsonObject = new JSONObject(GH.RetrofitBufferReaderResponse(response));
                    if (jsonObject.has("status")) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            SP.savePreferences(mContext, SP.TOTAL_NOTIFICATION, jsonObject.getString("result"));
                            // L.showError("going to set notification count from requestToGetNotificationCount...");
                            setNotificationTotal(binding.includeDashboard.includeToolbar.txtNotification);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dismissProgress();
                    DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissProgress();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final int id = item.getItemId();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemClock.sleep(220);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    switch (id) {
                        case R.id.nav_my_kit:
                            startActivity(new Intent(mContext, ProductKitActivity.class));
                            break;
                        case R.id.nav_search_and_buy_medicine:
                            navigateToSearchPage();
                            break;
                        case R.id.nav_otc_and_wellness:
                            startActivity(new Intent(mContext, ProductCategoryActivity.class));
                            break;
                        case R.id.nav_upload_prescription:
                            navigateToUploadPrescriptionPage();
                            break;
                        case R.id.nav_my_book_lab:
                            startActivity(new Intent(mContext, LabOrderListActivity.class));
                            break;
                        case R.id.nav_my_book_doctor:
                            startActivity(new Intent(mContext, DoctorAppointmentListActivity.class));
                            break;
                        case R.id.nav_my_orders:
                            startActivity(new Intent(mContext, OrderListActivity.class));
                            break;
                        case R.id.nav_address:
                            startActivity(new Intent(mContext, AddressListActivity.class)
                                    .putExtra("is_from_address_selction", ""));
                            break;
                        case R.id.nav_my_prescriptions:
                            startActivity(new Intent(mContext, UploadPrescriptionHistoryActivity.class)
                                    .putExtra("select_prescription", "select_prescription"));
                            break;
                        case R.id.nav_notifications:
                            startActivity(new Intent(mContext, NotificationActivity.class));
                            break;
                        case R.id.nav_setting:
                            startActivity(new Intent(mContext, SettingActivity.class));
                            break;
                        case R.id.nav_wallet:
                            startActivity(new Intent(mContext, WalletActivity.class));
                            break;
                        case R.id.nav_request:
                            startActivity(new Intent(mContext, RequestMedicineActivity.class));
                            break;
                        case R.id.nav_tnc:
                            startActivity(new Intent(mContext, TermConditionActivity.class));
                            break;
                        case R.id.nav_faq:
                            startActivity(new Intent(mContext, FaqActivity.class));
                            break;
                        case R.id.nav_policy:
                            startActivity(new Intent(mContext, PrivacyPolicyActivity.class));
                            break;

                        case R.id.nav_feedback:

                            Intent myIntent = new Intent(Intent.ACTION_SEND);
                            PackageManager pm = getPackageManager();
                            Intent tempIntent = new Intent(Intent.ACTION_SEND);
                            tempIntent.setType("*/*");
                            List<ResolveInfo> resInfo = pm.queryIntentActivities(tempIntent, 0);
                            for (int i = 0; i < resInfo.size(); i++) {
                                ResolveInfo ri = resInfo.get(i);
                                if (ri.activityInfo.packageName.contains("android.gm")) {
                                    myIntent.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
                                    myIntent.setAction(Intent.ACTION_SEND);
                                    myIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedback_email)});
                                    myIntent.setType("message/rfc822");
                                    myIntent.putExtra(Intent.EXTRA_TEXT, "Feedback here....");
                                    myIntent.putExtra(Intent.EXTRA_SUBJECT, "Enter your subject here..");
                                }
                            }

                            startActivity(myIntent);
                            break;
                        case R.id.nav_rating:
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                            break;

                        case R.id.nav_share:
                            String shareBody = "Here is the share content body " + "https://play.google.com/store/apps/details?id=" + getPackageName();
                            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
                            break;
                        case R.id.nav_doctor:
                            startActivity(new Intent(mContext, DoctorActivity.class));
                            break;
                        case R.id.nav_profile:
                            startActivity(new Intent(mContext, EditProfileActivity.class));
                            break;
                        default:
                            break;
                    }
                }
            }
        }).start();

        if (id == R.id.nav_logout) {
            if (AppUtil.isConnected(mContext)) {
                DialogUtil.showAlertDialogForEvent(getString(R.string.logout), getString(R.string.are_you_sure_you_want_to_logout_from_this_app), getString(R.string.yes), getString(R.string.no), mContext, mAlertDialogListener);
            }
        }
        if (id == R.id.nav_change_language) {
            AppUtil.changeLocalization(Dashboard.this);
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
        private String returnValueFromBundle(String key){
             Bundle bundle = getIntent().getExtras();
             String returnValue = bundle.get(key).toString();
             return returnValue;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbDoctor:
                startActivity(new Intent(mContext, DoctorActivity.class));
                break;
            case R.id.fbLabTest:
                startActivity(new Intent(mContext, LabTestActivity.class));
                break;
            case R.id.fbReqMedicine:
                startActivity(new Intent(mContext, RequestMedicineActivity.class));
                break;
            case R.id.llUploadPrescription:
                startActivity(new Intent(mContext, UploadPrescriptionActivity.class)
                        .putExtra("is_from_product_cart", "")
                        .putExtra("type", "prescription")
                        .putExtra("is_prescription_required", "yes"));
                break;
            case R.id.llSearchMedicine:
                startActivity(new Intent(mContext, ProductSearchActivity.class).putExtra("RefillMedicine", "").putExtra("otc", ""));
                break;
            case R.id.llBlogs:
                startActivity(new Intent(mContext, WebViewActivity.class)
                        .putExtra("url", getString(R.string.url_blogs))
                        .putExtra("link", getString(R.string.term_conditions))
                );
                break;
            case R.id.llTestimonials:
                startActivity(new Intent(mContext, WebViewActivity.class)
                        .putExtra("url", getString(R.string.url_testimonials))
                        .putExtra("link", getString(R.string.term_conditions))
                );
                break;
            case R.id.fb:

                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.call_us))));

                break;
            case R.id.fbFacebook:
               /* String smsNumber = "9595575566"; //without '+'
                try {
                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi");
                    sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                } catch (Exception e) {
                    Toast.makeText(this, "Whatsapp not install", Toast.LENGTH_SHORT).show();
                }*/
                break;
            default:
                break;
        }
    }
       /* PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "Here is the share content body " + "https://play.google.com/store/apps/details?id=" + getPackageName();

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }*/

    private void animateFAB() {

        if (isFabOpen) {
            binding.includeDashboard.fb.startAnimation(rotate_backward);
            binding.includeDashboard.fbGoogle.startAnimation(fab_close);
            binding.includeDashboard.fbFacebook.startAnimation(fab_close);

            binding.includeDashboard.fbGoogle.setClickable(false);
            binding.includeDashboard.fbFacebook.setClickable(false);
            isFabOpen = false;

        } else {

            binding.includeDashboard.fbGoogle.setVisibility(View.VISIBLE);
            binding.includeDashboard.fbFacebook.setVisibility(View.VISIBLE);

            binding.includeDashboard.fb.startAnimation(rotate_forward);
            binding.includeDashboard.fbGoogle.startAnimation(fab_open);
            binding.includeDashboard.fbFacebook.startAnimation(fab_open);
            binding.includeDashboard.fbGoogle.setClickable(true);
            binding.includeDashboard.fbFacebook.setClickable(true);
            isFabOpen = true;
        }
    }

    private void navigateToSearchPage() {
        startActivity(new Intent(mContext, ProductSearchActivity.class).putExtra("RefillMedicine", "").putExtra("otc", ""));
    }

    private void navigateToUploadPrescriptionPage() {
        startActivity(new Intent(mContext, UploadPrescriptionActivity.class)
                .putExtra("is_from_product_cart", "")
                .putExtra("type", "prescription")
                .putExtra("is_prescription_required", "yes")
        );
    }

    private void navigateToRefillOrderPage() {
        startActivity(new Intent(mContext, RefillOrderListActivity.class));
    }

    private void requestToLogoutUser() {
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), SP.getPreferences(mContext, SP.USER_ID));

        showProgress(getString(R.string.requesting), true);
        Call<ArrayList<LoginResultInfo>> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext).logoutUser(userId);
        arrayListCall.enqueue(new RetrofitCallback<ArrayList<LoginResultInfo>>(mContext) {
            @Override
            public void onSuccess(ArrayList<LoginResultInfo> models) {
                dismissProgress();
                String struserLat = SP.getPreferences(mContext, SP.USER_LAT);
                String struserLong = SP.getPreferences(mContext, SP.USER_LONG);
                SP.removeAllSharedPreferences(mContext);
                SP.savePreferences(mContext, SP.USER_LAT, struserLat);
                SP.savePreferences(mContext, SP.USER_LONG, struserLong);

                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }

            @Override
            public void onFail(String message) {
                dismissProgress();
                DialogUtil.showMessageDialog(mContext, message);
            }
        });
    }

    private void requestToGetDashbaordData() {
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), URLs.DASHBOARD_KEY);
        Call<DashboardInfo> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .dashboardData(key);

        arrayListCall.enqueue(new RetrofitCallback<DashboardInfo>(mContext) {
            @Override
            public void onSuccess(DashboardInfo models) {

                mDashboardBannerInfoList = models.getDashboardBanner();
                if (mDashboardBannerInfoList.size() > 0) {

                    binding.includeDashboard.llOfferView.setVisibility(View.VISIBLE);
                    binding.includeDashboard.vpMedicalOffers.setVisibility(View.VISIBLE);
                    binding.includeDashboard.llOfferImageDots.setVisibility(View.VISIBLE);
                    setViewPagerDashboardBanner();
                }
                mDashboardCategoryLists = models.getDashboardCategoryList();
                if (mDashboardCategoryLists.size() > 0) {
                    binding.includeDashboard.llProductCategories.setVisibility(View.VISIBLE);
                    binding.includeDashboard.rvCategoryList.setAdapter(new ProductCategoryAdapter(mContext, mDashboardCategoryLists, "", ""));
                }

                mPopularProductInfos = models.getPopularProduct();
                if (mPopularProductInfos.size() > 0) {
                    binding.includeDashboard.PopularBottom.setVisibility(View.VISIBLE);
                    binding.includeDashboard.llPopularProducts.setVisibility(View.VISIBLE);
                    binding.includeDashboard.rvPopularProducts.setAdapter(new DashboardPopularAdapter(mContext, mPopularProductInfos));
                }

                mTopSellingProductInfos = models.getTopSellingProduct();
                if (mTopSellingProductInfos.size() > 0) {
                    binding.includeDashboard.TopSellingBottom.setVisibility(View.VISIBLE);
                    binding.includeDashboard.llTopSellingProducts.setVisibility(View.VISIBLE);
                    binding.includeDashboard.rvTopSellingProducts.setAdapter(new DashboardTopSellingAdapter(mContext, mTopSellingProductInfos));
                }

                mSeasonalProductInfos = models.getSeasonalProductList();
                if (mSeasonalProductInfos.size() > 0) {
                    binding.includeDashboard.SeasonalBottom.setVisibility(View.VISIBLE);
                    binding.includeDashboard.llSeasonalProducts.setVisibility(View.VISIBLE);
                    binding.includeDashboard.rvSeasonalProducts.setAdapter(new DashboardSeasonalAdapter(mContext, mSeasonalProductInfos));
                }
            }

            @Override
            public void onFail(String message) {
            }
        });
    }

    private void setViewPagerDashboardBanner() {
        binding.includeDashboard.llOfferView.setVisibility(View.VISIBLE);
        createNewFragmentForDashboardBanners();
        showPointsBelowDashboardBannerImage();
        binding.includeDashboard.vpMedicalOffers.setAdapter(new DashboardBannersViewPagerAdapter(getSupportFragmentManager(), mDashboardBannerFragments));
        autoSwipeDashboardBanners();
    }

    private void showPointsBelowDashboardBannerImage() {
        if (binding.includeDashboard.llOfferImageDots != null)
            binding.includeDashboard.llOfferImageDots.removeAllViews();
        for (int i = 0; i < mDashboardBannerInfoList.size(); i++) {
            TextView linearLayout = new TextView(mContext);
            LinearLayout.LayoutParams paramsForLLBackGrounds = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen._8sdp), (int) getResources().getDimension(R.dimen._8sdp));
            paramsForLLBackGrounds.setMargins(6, 0, 6, 0);
            linearLayout.setLayoutParams(paramsForLLBackGrounds);
            linearLayout.setPadding(20, 10, 20, 10);
            linearLayout.setBackgroundResource(R.drawable.selector_dashboard_banners_dots);
            if (i == 0) {
                linearLayout.setSelected(true);
            } else {
                linearLayout.setSelected(false);
            }
            linearLayout.requestLayout();
            binding.includeDashboard.llOfferImageDots.addView(linearLayout);
            binding.includeDashboard.llOfferImageDots.requestLayout();
        }
    }

    private void autoSwipeDashboardBanners() {
        DASHBOARD_BANNER_SLIDER_PAGE = mDashboardBannerFragments.size();
        dashboardBannerHandler = new Handler();
        dashboardBannerThread = new Runnable() {
            public void run() {
                if (DASHBOARD_BANNER_CURRENT_PAGE == DASHBOARD_BANNER_SLIDER_PAGE) {
                    DASHBOARD_BANNER_CURRENT_PAGE = 0;
                }
                binding.includeDashboard.vpMedicalOffers.setCurrentItem(DASHBOARD_BANNER_CURRENT_PAGE++, true);
            }
        };
        Timer swipeHospitalImageTimer = new Timer();
        swipeHospitalImageTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                dashboardBannerHandler.post(dashboardBannerThread);
            }
        }, 5000, 5000);
    }

    private void createNewFragmentForDashboardBanners() {
        mDashboardBannerFragments = new ArrayList<>();
        final int fragmentArraySize = mDashboardBannerInfoList.size();
        for (int i = 0; i < fragmentArraySize; i++) {
            mDashboardBannerFragments.add(new DashboardBannerFragment(mDashboardBannerInfoList, i));
        }
    }

    private void setNavigationHeaderView() {
        try {
            txtHeaderUserName.setText(SP.getPreferences(mContext, SP.USER_FIRST_NAME) + " " + SP.getPreferences(mContext, SP.USER_LAST_NAME));
            txtHeaderUserEmail.setText(SP.getPreferences(mContext, SP.USER_EMAIL));
            txtHeaderUserMobile.setText(SP.getPreferences(mContext, SP.COUNTRY_CODE) + SP.getPreferences(mContext, SP.USER_MOBILE));

            if(!SP.getPreferences(mContext,SP.USER_FIRST_NAME).equalsIgnoreCase("")){
                txtHeaderUserName.setVisibility(View.VISIBLE);
            }
            if(!SP.getPreferences(mContext,SP.USER_EMAIL).equalsIgnoreCase("")) {
                txtHeaderUserEmail.setVisibility(View.VISIBLE);
            }
            /*Uri uri=Uri.parse(Uri.parse(SP.getPreferences(mContext,SP.USER_PROFILE)).getPath());*/
            if(Hawk.contains(Constants.IMG))
            {
                /*Picasso.with(mContext).load((Uri) Hawk.get(Constants.IMG)).into(imgHeaderUserProfile);*/
                Glide.with(mContext).load(new File((String) Hawk.get(Constants.IMG))).diskCacheStrategy(DiskCacheStrategy.RESULT).error(R.mipmap.icon_user_thumb).into(imgHeaderUserProfile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResumeCalled() {
        setCartTotal(binding.includeDashboard.includeToolbar.txtMedicineCart);
        setNotificationTotal(binding.includeDashboard.includeToolbar.txtNotification);
        if(AppUtil.isConnected(mContext))
        {
            requestToGetNotificationCount();
        }
        if (navigationView != null) {
            setNavigationHeaderView();
            isFabOpen = true;
          /*  binding.includeDashboard.fbGoogle.setVisibility(View.GONE);
            binding.includeDashboard.fbFacebook.setVisibility(View.GONE);
            binding.includeDashboard.fb.startAnimation(rotate_backward);*/
          /*  animateFAB();*/

        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}