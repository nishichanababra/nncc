package app.citta.retail365cloud.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.abstractclasses.BaseActivity;
import app.citta.retail365cloud.adapters.CustomerAdapter;
import app.citta.retail365cloud.apimodels.CustomerListObject;
import app.citta.retail365cloud.apimodels.CustomerListResponse;
import app.citta.retail365cloud.utils.AppPreference;
import app.citta.retail365cloud.interfaces.BaseRecyclerViewListener;
import app.citta.retail365cloud.retrofit.RetrofitAPIClient;
import app.citta.retail365cloud.retrofit.WebServicesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends BaseActivity implements BaseRecyclerViewListener<CustomerListResponse> {

    private Context mContext = CustomerActivity.this;

    private final String TAG = this.getClass().getSimpleName();

    private RecyclerView recycler_view;
    private ImageView img_nodata;

    private LinearLayoutManager mLayoutManager;

    private CustomerAdapter customerAdapter;

    private int pageNo = -10, pageRecord = 10, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0, totalListCount = 0, listCount = 0;

    private String companyId, branchId;

    private boolean loading = true;

    private ArrayList<CustomerListObject> customerListObjects = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // initialize views
        initView();
    }

    /*
     * Method to initialize the views
     *
     * */
    private void initView() {

        // Getting values from SharedPreference
        companyId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_company_id));
        branchId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_branch_id));

        recycler_view = findViewById(R.id.recycler_view);
        img_nodata = findViewById(R.id.nodata);

        customerAdapter=new CustomerAdapter(customerListObjects,mContext);
        mLayoutManager = new LinearLayoutManager(CustomerActivity.this, LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        recycler_view.setAdapter(customerAdapter);

        pageNo = -10;
        //pageRecord = 10;
        totalListCount = 0;
        listCount = 0;
        loading = true;
        totalItems = 0;
        totalItemCount = 0;


        /*
         * Load more data on scroll event of recycler view
         *
         * */
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

              /*  if (dy > 0) {

                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    loading = totalItems < totalItemCount;

                    if (loading) {

                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                            totalItems = totalItemCount;
                            loading = false;

                            if (isOnline(mContext)) {

                                // Call retrofit to make service request to get customer data from server
                                getCustomerList();

                            } else {

                                showCustomToast(mContext, getResources().getString(R.string.msg_no_internet));
                            }
                        }
                    }
                }*/
            }
        });

        //get customer data
        getCustomerList();

    }

    /**
     * Method to get customer list
     */
    private void getCustomerList() {

        try {
            startProgressDialog(CustomerActivity.this, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient(CustomerActivity.this).create(WebServicesAPI.class);

            JsonObject paramObjects = new JsonObject();
            paramObjects.addProperty("Cid", companyId);
            paramObjects.addProperty("Bid", branchId);

            Call<CustomerListResponse> call = webServicesAPI.getCustomerList(paramObjects);

            call.enqueue(new Callback<CustomerListResponse>() {

                @Override
                public void onResponse(Call<CustomerListResponse> call, Response<CustomerListResponse> response) {
                    if (response.isSuccessful()) {

                        stopProgressDialog();

                        if (response.body() != null) {

                            ArrayList<CustomerListObject> objectArrayList = response.body().getObj();

                            if(objectArrayList != null)

                                customerListObjects.addAll(objectArrayList);

                            if (customerListObjects != null && customerListObjects.size() > 0) {

                                recycler_view.setVisibility(View.VISIBLE);
                                img_nodata.setVisibility(View.GONE);
                                customerAdapter.notifyDataSetChanged();


                            } else {

                                recycler_view.setVisibility(View.GONE);
                                img_nodata.setVisibility(View.VISIBLE);
                            }

                        } else {

                            recycler_view.setVisibility(View.GONE);
                            img_nodata.setVisibility(View.VISIBLE);
                        }

                    } else {

                        recycler_view.setVisibility(View.GONE);
                        img_nodata.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onFailure(Call<CustomerListResponse> call, Throwable t) {

                    stopProgressDialog();
                    GlobalAlertDialog(CustomerActivity.this, "Whoops!", "Something went wrong. Please check  your customerlist", false);
                    recycler_view.setVisibility(View.GONE);
                    img_nodata.setVisibility(View.VISIBLE);

                }
            });
        } catch (Exception e) {

            e.printStackTrace();
            stopProgressDialog();
        }

    }

    @Override
    public void showEmptyDataView(int resId) {

    }

    @Override
    public void onRecyclerItemClick(int position, CustomerListResponse item) {

    }
}
