package app.citta.retail365cloud.activities.Reports;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.abstractclasses.BaseActivity;
import app.citta.retail365cloud.adapters.DateDialogAdapter;
import app.citta.retail365cloud.adapters.SalesReportAdapter;
import app.citta.retail365cloud.apimodels.Obj;
import app.citta.retail365cloud.apimodels.SalesReportObject;
import app.citta.retail365cloud.apimodels.SalesReportResponse;
import app.citta.retail365cloud.fragment.DateRangePickerFragment;
import app.citta.retail365cloud.models.DateDialogModel;
import app.citta.retail365cloud.retrofit.RetrofitAPIClient;
import app.citta.retail365cloud.retrofit.WebServicesAPI;
import app.citta.retail365cloud.utils.AppPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesReportActivity extends BaseActivity implements
        DateDialogAdapter.OnItemClickListener, DateRangePickerFragment.OnDateRangeSelectedListener {

    private Context mContext = SalesReportActivity.this;

    private RecyclerView recyclerView, recycler_View;

    private ImageView img_nodata;

    private TextView tv_party_title, tv_customer_title, tv_branch_title, txtstartdate, tv_check;

    private Spinner sp_party, sp_customer, sp_branch;

    private LinearLayoutManager mLayoutManager;

    private ArrayList<DateDialogModel> dateDialogModels = new ArrayList<>();

    private DateDialogAdapter dateDialogAdapter;


    private ArrayList<SalesReportObject> salesReportObjects = new ArrayList<>();
    private Obj object;

    private SalesReportAdapter salesReportAdapter;
    //private SalesReportListAdapter salesReportListAdapter;

    private String companyId, branchId, f_toDate = "", f_fromDate = "", fromDate = "", toDate = "", customername = "", startdateorgFormat, enddateorgFormat;

    private Button btnClearFilter, btnApplyFilter;
    private Dialog dialogFilter;
    private TextInputEditText tedtFromdate, tedtTodate;

    private DateRangePickerFragment dateRangePickerFragment;
    private Dialog dialog;
    private Menu menu;
    private MenuItem filter, edit, save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        prepareDateDialogModels();
    }

    /*
     * Method to initialize the views
     *
     * */
    @SuppressLint("SetTextI18n")
    private void initView() {

        // Getting values from SharedPreference
        companyId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_company_id));
        branchId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_branch_id));

        f_fromDate = subtractDaysInDate(7); // F_FromDate-Format = dd-MM-yyyy
        f_toDate = dateConversion(RETROFIT_DATE_FORMAT, CURRENT_DATE_FORMAT, getCurrentDate(RETROFIT_DATE_FORMAT)); //F_ToDate = CURRENT_DATE_FORMAT

        txtstartdate = findViewById(R.id.tv_date);
        txtstartdate.setText(f_fromDate + " to " + f_toDate);

        recyclerView = findViewById(R.id.recycler_view);
        img_nodata = findViewById(R.id.nodata);

        salesReportAdapter = new SalesReportAdapter(salesReportObjects, mContext);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dateRangePickerFragment = DateRangePickerFragment.newInstance((DateRangePickerFragment.OnDateRangeSelectedListener) mContext, false);

//        txtstartdate.setOnClickListener(v -> dateRangePickerFragment.show(getSupportFragmentManager(), "datePicker"));

        getSalesReport();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.menu_filter, menu);
        this.menu = menu;
        filter = menu.findItem(R.id.filter);
       /* edit = menu.findItem(R.id.edit);
        edit.setVisible(false);
        edit = menu.findItem(R.id.edit);
        edit.setVisible(false);
        save = menu.findItem(R.id.save);
        save.setVisible(false);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            filter.getIcon().setTint(getResources().getColor(R.color.colorWhite));

        }

        filter.setOnMenuItemClickListener(menuItem -> {

            dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_selecttime);
            dialog.setCancelable(true);

            ConstraintLayout constraintLayout = dialog.findViewById(R.id.rl_dialogsettime);

            recycler_View = dialog.findViewById(R.id.dialog_recyclerview);
            tv_check = dialog.findViewById(R.id.tv_check);

            dateDialogAdapter = new DateDialogAdapter(mContext, dateDialogModels);
            mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            recycler_View.setLayoutManager(mLayoutManager);
            recycler_View.setItemAnimator(new DefaultItemAnimator());
            recycler_View.setAdapter(dateDialogAdapter);

            constraintLayout.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

            dialog.show();

            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }

    /*
     * Method to initialize date dialog list
     *
     * */
    private void prepareDateDialogModels() {

        dateDialogModels.add(new DateDialogModel("Last 7 Days", true));
        dateDialogModels.add(new DateDialogModel("Last 15 Days", false));
        dateDialogModels.add(new DateDialogModel("Last 30 Days", false));
        dateDialogModels.add(new DateDialogModel("Last 1 Year", false));
        dateDialogModels.add(new DateDialogModel("Begining of the Time", false));
        dateDialogModels.add(new DateDialogModel("Custom Range", false));
    }

    /*
     * Method to clear Filter*/
    public void clearFilter() {

        for (int i = 0; i < dateDialogModels.size(); i++) {
            if (dateDialogModels.get(i).isSelected()) {
                dateDialogModels.get(i).setSelected(false);
            }
        }
    }

    public void setFilteredData() {
        this.f_fromDate = fromDate;
        this.f_toDate = toDate;
    }

    @Override
    public void onItemViewClick(int position, String dateDialogmodels) {

        //Now we call this method to clear filter as it can be used before in different scenarios
        clearFilter();

        switch (dateDialogmodels) {

            case "Last 7 Days":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Last 7 Days");
                break;

            case "Last 15 Days":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Last 15 Days");
                break;

            case "Last 30 Days":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Last 30 Days");
                break;

            case "Last 1 Year":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Last 1 Year");
                break;

            case "Begining of the Time":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Begining of the Time");
                break;

            case "Custom Range":
                dateDialogModels.get(position).setSelected(true);
                calculateDate("Custom Range");
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateDate(String filterType) {

        try {

            f_toDate = getCurrentDate();

            switch (filterType) {

                case "Last 7 Days":
                    f_fromDate = subtractDaysInDate(7);
                    txtstartdate.setText(f_fromDate + " to " + f_toDate);
                    dialog.dismiss();
                    getSalesReport();
                    break;

                case "Last 15 Days":
                    f_fromDate = subtractDaysInDate(15);
                    txtstartdate.setText(f_fromDate + " to " + f_toDate);
                    dialog.dismiss();
                    getSalesReport();
                    break;

                case "Last 30 Days":
                    f_fromDate = subtractMonth(1);
                    txtstartdate.setText(f_fromDate + " to " + f_toDate);
                    dialog.dismiss();
                    getSalesReport();
                    break;

                case "Last 1 Year":
                    f_fromDate = subtractYear(1);
                    txtstartdate.setText(f_fromDate + " to " + f_toDate);
                    dialog.dismiss();
                    getSalesReport();
                    break;

                case "Begining of the Time":
                    f_fromDate = "";
                    f_toDate = "";
                    txtstartdate.setText("Upto Today");
                    dialog.dismiss();
                    getSalesReport();
                    break;

                case "Custom Range":
                    try {

                        dateRangePickerFragment.show(getSupportFragmentManager(), "datePicker");
                        Window w = dialog.getWindow();
                        w.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.dismiss();
                        dialog.notify();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to select date range
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) throws ParseException {

        startdateorgFormat = getProperdate(startDay) + "-" + getProperdate(startMonth) + "-" + startYear;
        enddateorgFormat = getProperdate(endDay) + "-" + getProperdate(endMonth) + "-" + endYear;

        if (!TextUtils.isEmpty(startdateorgFormat) && !TextUtils.isEmpty(enddateorgFormat)) {

            txtstartdate.setText(startdateorgFormat + " to " + enddateorgFormat);

            f_fromDate = startdateorgFormat;
            f_toDate = enddateorgFormat;

            getSalesReport();

        }
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.menu_filter, menu);
        this.menu = menu;
        filter = menu.findItem(R.id.filter);
        edit = menu.findItem(R.id.edit);
        edit.setVisible(false);
        save = menu.findItem(R.id.save);
        save.setVisible(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            filter.getIcon().setTint(ContextCompat.getColor(mContext, R.color.colorWhite));
        }

        filter.setOnMenuItemClickListener(menuItem -> {
            applyFilter();
            return false;
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void applyFilter() {

        try {

            dialogFilter = new Dialog(mContext);
            dialogFilter.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogFilter.setContentView(R.layout.dialog_filter_report);
            dialogFilter.setCanceledOnTouchOutside(true);
            dialogFilter.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

            sp_party = dialogFilter.findViewById(R.id.sp_party);
            sp_branch = dialogFilter.findViewById(R.id.sp_branch);
            sp_customer = dialogFilter.findViewById(R.id.sp_customer);

            tv_branch_title = dialogFilter.findViewById(R.id.tv_branch_title);
            tv_customer_title = dialogFilter.findViewById(R.id.tv_customer_title);
            tv_party_title = dialogFilter.findViewById(R.id.tv_party_title);

            tedtFromdate = dialogFilter.findViewById(R.id.tedt_fromdate);
            tedtTodate = dialogFilter.findViewById(R.id.tedt_todate);
            btnClearFilter = dialogFilter.findViewById(R.id.btn_clearFilter);
            btnApplyFilter = dialogFilter.findViewById(R.id.btn_applyFilter);

            sp_party.setVisibility(View.GONE);
            sp_branch.setVisibility(View.VISIBLE);
            sp_customer.setVisibility(View.VISIBLE);
            tv_branch_title.setVisibility(View.VISIBLE);
            tv_customer_title.setVisibility(View.VISIBLE);
            tv_party_title.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(fromDate)) {
                fromDate = dateConversion(CURRENT_DATE_FORMAT, RETROFIT_API_CALLING_DATE_FORMAT, f_fromDate);
            }
            if (!TextUtils.isEmpty(toDate)) {
                toDate = dateConversion(CURRENT_DATE_FORMAT, RETROFIT_API_CALLING_DATE_FORMAT, f_toDate);
            }

            tedtFromdate.setText(fromDate);
            tedtTodate.setText(toDate);

            String[] names = new String[salesReportObjects.size()];
            int i = 0;
            for (SalesReportObject obj : salesReportObjects) {
                names[i++] = obj.getCustomername();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
            sp_customer.setAdapter(adapter);

            tedtFromdate.setOnClickListener(v -> selectDate(tedtFromdate));

            tedtTodate.setOnClickListener(v -> selectDate(tedtTodate));

            btnApplyFilter.setOnClickListener(view -> {

                if (checkDates(tedtFromdate.getText().toString().trim(), tedtTodate.getText().toString().trim(), dd__MMM__yyyy)) {

                    fromDate = tedtFromdate.getText().toString().trim();
                    toDate = tedtTodate.getText().toString().trim();

                    if (dialogFilter != null) {

                        dialogFilter.dismiss();

                        salesReportObjects = new ArrayList<>();
                        getSalesReport();
                    }
                }

            });

            btnClearFilter.setOnClickListener(view -> {

                if (dialogFilter != null) {

                    fromDate = tedtFromdate.getText().toString().trim();
                    toDate = tedtTodate.getText().toString().trim();

                    fromDate = "";
                    toDate = "";
                    customername = "";

                    dialogFilter.dismiss();

                    salesReportObjects = new ArrayList<>();
                    getSalesReport();
                }
            });


            Window window = dialogFilter.getWindow();
            window.setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);

            if (dialogFilter != null) {
                dialogFilter.show();
            }

            dialogFilter.setOnDismissListener(dialog1 -> dialogFilter = null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void getSalesReport() {

        try {

            f_fromDate = dateConversion(CURRENT_DATE_FORMAT, RETROFIT_API_CALLING_DATE_FORMAT, f_fromDate);
            f_toDate = dateConversion(CURRENT_DATE_FORMAT, RETROFIT_API_CALLING_DATE_FORMAT, f_toDate);

            startProgressDialog((Activity) mContext, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient(SalesReportActivity.this).create(WebServicesAPI.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("cid", companyId);
            jsonObject.addProperty("bid", branchId);
            jsonObject.addProperty("fromdate", f_fromDate);
            jsonObject.addProperty("todate", f_toDate);

            Call<SalesReportResponse> call = webServicesAPI.getSalesReport(jsonObject);

            call.enqueue(new Callback<SalesReportResponse>() {

                @Override
                public void onResponse(Call<SalesReportResponse> call, Response<SalesReportResponse> response) {

                    stopProgressDialog();

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            object = response.body().getObj();

                            if (object != null) {

//                            object.getListOrderDetails();

                                //salesReportListAdapter=new SalesReportListAdapter(mContext, Objects.requireNonNull(response.body()).getObj()));
                                txtstartdate.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                img_nodata.setVisibility(View.GONE);

                                salesReportAdapter = new SalesReportAdapter(object.getListOrderDetails(), mContext);
                                recyclerView.setAdapter(salesReportAdapter);
                                salesReportAdapter.notifyDataSetChanged();

                            } else {

                                recyclerView.setVisibility(View.GONE);
                                img_nodata.setVisibility(View.VISIBLE);
                                txtstartdate.setVisibility(View.GONE);
                            }

                        } else {

                            recyclerView.setVisibility(View.GONE);
                            img_nodata.setVisibility(View.VISIBLE);
                            txtstartdate.setVisibility(View.GONE);
                        }

                    } else

                    {

                        showCustomToast(mContext, "Something went wrong!");
                        recyclerView.setVisibility(View.GONE);
                        img_nodata.setVisibility(View.VISIBLE);
                        txtstartdate.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<SalesReportResponse> call, Throwable t) {

                    stopProgressDialog();
                    recyclerView.setVisibility(View.GONE);
                    img_nodata.setVisibility(View.VISIBLE);
                    txtstartdate.setVisibility(View.GONE);
                    GlobalAlertDialog(mContext, getResources().getString(R.string.msg_title_api_error), getResources().getString(R.string.msg_api_error), false);
                }
            });

        } catch (
                Exception e)

        {
            e.printStackTrace();
            stopProgressDialog();
        }

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

       /* Intent intent=new Intent(mContext, DashboardActivity.class);
        intent.putExtra("reports","Reports Activity");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();*/
    }


   /* @Override
    public void SalesReportClick(int parentPosition, int childPosition, SalesReportObject child) {

    }*/
}
