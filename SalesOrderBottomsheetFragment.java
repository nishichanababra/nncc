package app.citta.retail365cloud.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.abstractclasses.BaseFragment;
import app.citta.retail365cloud.activities.SalesOrderForm;
import app.citta.retail365cloud.apimodels.ListPOProducts;
import app.citta.retail365cloud.apimodels.SalesProductObject;
import app.citta.retail365cloud.apimodels.SalesProductResponse;
import app.citta.retail365cloud.models.CustomerModel;
import app.citta.retail365cloud.models.SalesProductModel;
import app.citta.retail365cloud.retrofit.RetrofitAPIClient;
import app.citta.retail365cloud.retrofit.WebServicesAPI;
import app.citta.retail365cloud.utils.AppPreference;
import app.citta.retail365cloud.utils.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class SalesOrderBottomsheetFragment extends BaseFragment implements View.OnClickListener {

    public TextInputLayout tnlProduct, tnlUnit, tnlAvlStock, tnlQty, tnlUnitPrice, tnlPrice, tnlTotalPrice, tnlDiscRupees, tnlDiscPer, tnlGstPer, tnlGst;
    private Button btnAdd;
    public String strProduct = "", strUnit = "", strAvlstock = "", strQty = "1", strUnitprice = "", strPrice = "", strDiscRupees = "", strTotalPrice = "", strDiscPer = "", strGst = "", strGstPer = "", bId, cId;
    private ImageView ivBack;
    private Spinner sp_product;
    private SalesOrderForm salesOrderForm = null;
    public static double qty, unitprice, price, discper, discrs, totalprice;
    private ArrayList<SalesProductObject> salesProductObjects = new ArrayList<>();
    private SalesProductObject salesProductObject;
    public ArrayList<SalesProductModel> salesProductmodels = new ArrayList<>();
    private SalesProductModel salesOrdermodel;
    private String[] names;
    List<SalesProductObject> productObjectList=new ArrayList<>();
    private int[] ids;
    public int productid, position;
    private BottomSheetDialogFragment bottomSheetDialogFragment;
    boolean isEdit=false;
    private String productname[] = null;
    // public static TextInputLayout tnlTotalPrice;

    public SalesOrderBottomsheetFragment() {

    }

    public SalesOrderBottomsheetFragment(SalesOrderForm salesOrderForm, ArrayList<SalesProductModel> salesProductModels, int position) {
        this.salesOrderForm = salesOrderForm;
        this.salesProductmodels = salesProductModels;
        this.position = position;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getActivity(), R.layout.bottomsheet_salesorder, null);
        dialog.setContentView(view);
        initView(view);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);

            DisplayMetrics displaymetrics = new DisplayMetrics();
            Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int screenHeight = displaymetrics.heightPixels;
            ((BottomSheetBehavior) behavior).setPeekHeight(screenHeight);
        }

        getSalesProductData();

        Bundle bundle = getArguments();
        if (bundle != null) {
            isEdit = true;
            salesOrdermodel = salesProductmodels.get(position);
            setFormsDetail();
        }
    }


    private void setFormsDetail() {

        if (salesOrdermodel != null) {
          /*  if (!TextUtils.isEmpty(salesOrdermodel.getProductName())) {
                Objects.requireNonNull(tnlProduct.getEditText()).setText(salesOrdermodel.getProductName());
            }*/

            if (!TextUtils.isEmpty(salesOrdermodel.getQtyUnit())) {
                Objects.requireNonNull(tnlUnit.getEditText()).setText(salesOrdermodel.getQtyUnit());
            }
            if (!TextUtils.isEmpty(salesOrdermodel.getUnitPrice())) {
                Objects.requireNonNull(tnlUnitPrice.getEditText()).setText(salesOrdermodel.getUnitPrice());
            }
            if (!TextUtils.isEmpty(salesOrdermodel.getPrice())) {
                Objects.requireNonNull(tnlPrice.getEditText()).setText(salesOrdermodel.getPrice());
            }
            if (!TextUtils.isEmpty(salesOrdermodel.getDiscountper())) {
                Objects.requireNonNull(tnlDiscPer.getEditText()).setText(salesOrdermodel.getDiscountper());
            }
            if (!TextUtils.isEmpty(salesOrdermodel.getDiscountrupees())) {
                Objects.requireNonNull(tnlDiscRupees.getEditText()).setText(salesOrdermodel.getDiscountrupees());
            }
            if (!TextUtils.isEmpty(salesOrdermodel.getTotalprice())) {
                Objects.requireNonNull(tnlTotalPrice.getEditText()).setText(salesOrdermodel.getTotalprice());
            }

        }
    }

    public SalesOrderBottomsheetFragment(SalesOrderForm salesOrderForm,List<SalesProductObject> listOfProducts) {
        this.salesOrderForm = salesOrderForm;
        productObjectList=listOfProducts;
    }

    private void initView(View view) {

        // Getting values from SharedPreference
        cId = AppPreference.getInstance().getStringPreference(getContext(), getResources().getString(R.string.pref_company_id));
        bId = AppPreference.getInstance().getStringPreference(getContext(), getResources().getString(R.string.pref_branch_id));

        // tnlProduct = view.findViewById(R.id.tnl_product);
        tnlUnit = view.findViewById(R.id.tnl_unit);
        tnlAvlStock = view.findViewById(R.id.tnl_avlstock);
        tnlQty = view.findViewById(R.id.tnl_qty);
        tnlUnitPrice = view.findViewById(R.id.tnl_unitprice);
        tnlPrice = view.findViewById(R.id.tnl_price);
        tnlDiscRupees = view.findViewById(R.id.tnl_discrupees);
        tnlTotalPrice = view.findViewById(R.id.tnl_totalprice);
        tnlDiscPer = view.findViewById(R.id.tnl_discper);
        btnAdd = view.findViewById(R.id.btn_add);
        tnlGstPer = view.findViewById(R.id.tnl_gstper);
        tnlGst = view.findViewById(R.id.tnl_gst);
        ivBack = view.findViewById(R.id.ivBackArrow);
        sp_product = view.findViewById(R.id.sp_product);

        ivBack.setOnClickListener(this);

        strUnit = tnlUnit.getEditText().getText().toString().trim();
        strAvlstock = tnlAvlStock.getEditText().getText().toString().trim();
        strQty = tnlQty.getEditText().getText().toString().trim();
        strUnitprice = tnlUnitPrice.getEditText().getText().toString().trim();
        strPrice = tnlPrice.getEditText().getText().toString().trim();
        strDiscRupees = tnlDiscRupees.getEditText().getText().toString().trim();
        strTotalPrice = tnlTotalPrice.getEditText().getText().toString().trim();
        strDiscPer = tnlDiscPer.getEditText().getText().toString().trim();
        strGst = tnlGst.getEditText().getText().toString().trim();
        strGstPer = tnlGstPer.getEditText().getText().toString().trim();

        tnlQty.getEditText().setText(strQty);

        btnAdd.setOnClickListener(this);


        sp_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                strProduct = sp_product.getSelectedItem().toString();
                productid = ids[i];

                salesProductObject = salesProductObjects.get(i);

                if (salesProductObject != null) {

                    tnlUnit.getEditText().setText(salesProductObject.getUnit());
                    tnlAvlStock.getEditText().setText(salesProductObject.getStockqty());


                  /*  strAvlstock=tnlAvlStock.getEditText().getText().toString();
                    if(Double.parseDouble(strAvlstock)>0.00){

                    }
                    else {

                        tnlAvlStock.setError("Stock is not available");
//                        GlobalAlertDialog(getContext(),"Available Stock","Stock is not avilable",true);
                    }*/

                    strUnitprice = salesProductObject.getUnitprice();
                    strUnitprice = strUnitprice.replaceAll(",", "");
                    strQty = salesProductObject.getQty();

                    tnlUnitPrice.getEditText().setText(strUnitprice);
                    tnlQty.getEditText().setText(strQty);

                    Double totalPrice = 0.0;
                    totalPrice = Double.parseDouble(strQty) * Double.parseDouble(strUnitprice);
                    strPrice = String.valueOf(totalPrice);
                    //strPrice = salesProductObject.getPrice();
                    tnlPrice.getEditText().setText(strPrice);
                    tnlTotalPrice.getEditText().setText(strPrice);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        Objects.requireNonNull(tnlUnitPrice.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (checkLength()) {
                    getCalculationOfProduct(
                            tnlQty.getEditText().getText().toString(),
                            tnlUnitPrice.getEditText().getText().toString(),
                            tnlDiscPer.getEditText().getText().toString()
                    );
                } else {
                    tnlUnitPrice.getEditText().setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        Objects.requireNonNull(tnlQty.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (checkLength()) {
                    getCalculationOfProduct(
                            tnlQty.getEditText().getText().toString(),
                            tnlUnitPrice.getEditText().getText().toString(),
                            tnlDiscPer.getEditText().getText().toString()
                    );
                } else {
                    tnlQty.getEditText().setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Objects.requireNonNull(tnlDiscPer.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkLength()) {
                    getCalculationOfProduct(
                            tnlQty.getEditText().getText().toString(),
                            tnlUnitPrice.getEditText().getText().toString(),
                            tnlDiscPer.getEditText().getText().toString()
                    );
                } else {
                    tnlDiscPer.getEditText().setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * This method set Customer data into Spinner
     */
    public void setProductListToSpinner() {
        int i = 0;
        if (productObjectList != null) {
            productname = new String[productObjectList.size() + 1];
            productname[i++] = "Select Product";
            for (SalesProductObject customerData : productObjectList) {
                productname[i++] = customerData.getProductName();
            }
        }
        try {
            sp_product.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, productname));
//            spinnerAdapter = new SpinnerItemAdapter(this, R.layout.row_spinner_item, new ArrayList<String>(Arrays.asList(customerName)));
//            spCustomernm.setAdapter(spinnerAdapter);
            sp_product.setSelection(0);
        } catch (Exception e) {
            Log.d("EXCEPTION ", e.toString());
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_add:

                if (validation()) {

                    //strProduct = tnlProduct.getEditText().toString().trim();

                    SalesProductModel salesProductModel = new SalesProductModel(String.valueOf(productid), "0", strQty, strUnit, strUnitprice, strPrice, strDiscPer, strDiscRupees, strTotalPrice, "", "0.0", "0.0", "0.0", strProduct, "");
//                    salesOrderForm.salesProductModels.add(salesProductModel);
                    //salesOrderForm.setSalesOrderDetailsRecyclerView(salesOrderForm.salesProductModels);

                    OnSalesOrderSubmitListener onSalesOrderSubmitListener = (OnSalesOrderSubmitListener) salesOrderForm;
                    onSalesOrderSubmitListener.onSaveClick(salesProductModel);

                    salesOrderForm.addOrderDetailOnClick(salesProductmodels, SalesOrderBottomsheetFragment.this);
                    this.dismiss();

                }
                break;

            case R.id.ivBackArrow:
                dismiss();
                break;
        }
    }


    /**
     * Method to get sales customer data
     */

    public void getSalesProductData() {

        try {

            startProgressDialog((Activity) getContext(), "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) getContext()).create(WebServicesAPI.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bid", bId);

            Call<SalesProductResponse> call = webServicesAPI.getSalesProductdata(jsonObject);

            call.enqueue(new Callback<SalesProductResponse>() {

                @Override
                public void onResponse(Call<SalesProductResponse> call, Response<SalesProductResponse> response) {

                    stopProgressDialog();

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            ArrayList<SalesProductObject> objectArrayList = response.body().getObj();

                            if (objectArrayList != null && objectArrayList.size() > 0) {

                                salesProductObjects.addAll(objectArrayList);

                                names = new String[objectArrayList.size()];
                                ids = new int[objectArrayList.size()];

                                int i = 0;
                                int j = 0;
                                for (SalesProductObject obj : objectArrayList) {

                                    names[i++] = obj.getProductName();
                                    ids[j++] = obj.getId();

                                }
                                ArrayList<String> nameList = new ArrayList<>(Arrays.asList(names));
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nameList);
                                sp_product.setAdapter(adapter);
                            }

                        } else {

                            // showCustomToast((Activity) getContext(), response.body().getMessage());
                        }

                    }
                }

                @Override
                public void onFailure(Call<SalesProductResponse> call, Throwable t) {

                    stopProgressDialog();
                    GlobalAlertDialog((Activity) getContext(), getResources().getString(R.string.msg_title_api_error), getResources().getString(R.string.msg_api_error), false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validation() {

        return Validator.getInstance().validateString(getContext(), tnlUnit, "Enter Unit") &&
                Validator.getInstance().validateString(getContext(), tnlAvlStock, "Stock is not Available") &&
                Validator.getInstance().validateString(getContext(), tnlQty, "Enter Quantity") &&
                Validator.getInstance().validateString(getContext(), tnlUnitPrice, "Enter Unit Price") &&
                Validator.getInstance().validateString(getContext(), tnlPrice, "Enter Price") &&
                Validator.getInstance().validateString(getContext(), tnlTotalPrice, "Enter Total Price");
    }

    public interface OnSalesOrderSubmitListener {

        void onSaveClick(SalesProductModel salesProductModel);
    }

    public void getCalculationOfProduct(String qty, String unitPrice, String discInPer) {


        Double Qty = Double.parseDouble(qty);
        Double UnitPrice = Double.parseDouble(unitPrice);
        Double DiscInPer = Double.parseDouble(discInPer);

        Double price = Qty * UnitPrice;
        Double DiscInRs = 0.0;
        Double TotalWithDisc = 0.0;
        tnlPrice.getEditText().setText(String.valueOf(price));

        if (DiscInPer > 0) {

            DiscInRs = ((price * DiscInPer) / 100);
            TotalWithDisc = price - DiscInRs;

            tnlTotalPrice.getEditText().setText(String.valueOf(TotalWithDisc));
            tnlDiscRupees.getEditText().setText(String.valueOf(DiscInRs));

        } else {
            TotalWithDisc = Qty * UnitPrice;

            tnlTotalPrice.getEditText().setText(String.valueOf(TotalWithDisc));

        }

        strQty = String.valueOf(Qty);
        strUnit = tnlUnit.getEditText().getText().toString();
        strUnitprice = String.valueOf(UnitPrice);
        strPrice = String.valueOf(price);
        strDiscRupees = String.valueOf(DiscInRs);
        strDiscPer = String.valueOf(DiscInPer);
        strTotalPrice = String.valueOf(TotalWithDisc);
    }

    public boolean checkLength() {
        return tnlDiscPer.getEditText().getText().length() > 0 &&
                tnlQty.getEditText().getText().length() > 0 &&
                tnlUnitPrice.getEditText().getText().length() > 0;
    }



    }
