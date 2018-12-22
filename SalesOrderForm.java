package app.citta.retail365cloud.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.abstractclasses.BaseActivity;
import app.citta.retail365cloud.adapters.PaymentListAdapter;
import app.citta.retail365cloud.adapters.SalesOrderFormAdapter;
import app.citta.retail365cloud.adapters.SpinnerItemAdapter;
import app.citta.retail365cloud.apimodels.SalesBillNumberResponse;
import app.citta.retail365cloud.apimodels.SalesBillUpdateResponse;
import app.citta.retail365cloud.apimodels.SalesCustomerObject;
import app.citta.retail365cloud.apimodels.SalesListPaymentDetail;
import app.citta.retail365cloud.apimodels.SalesListSalesDetail;
import app.citta.retail365cloud.apimodels.SalesOrderEditObject;
import app.citta.retail365cloud.apimodels.SalesOrderFormResponse;
import app.citta.retail365cloud.apimodels.SalesOrderLoadObject;
import app.citta.retail365cloud.apimodels.SalesOrderLoadResponse;
import app.citta.retail365cloud.apimodels.SalesProductObject;
import app.citta.retail365cloud.fragment.CustomerBottomsheetFragment;
import app.citta.retail365cloud.fragment.PaymentBottomsheetFragment;
import app.citta.retail365cloud.fragment.SalesOrderBottomsheetFragment;
import app.citta.retail365cloud.interfaces.RecyclerviewListener;
import app.citta.retail365cloud.interfaces.SalesBillListener;
import app.citta.retail365cloud.models.BankModel;
import app.citta.retail365cloud.models.CustomerModel;
import app.citta.retail365cloud.models.PaymentModel;
import app.citta.retail365cloud.models.SalesBillModel;
import app.citta.retail365cloud.models.SalesProductModel;
import app.citta.retail365cloud.models.TaxStructureModel;
import app.citta.retail365cloud.retrofit.RetrofitAPIClient;
import app.citta.retail365cloud.retrofit.WebServicesAPI;
import app.citta.retail365cloud.utils.AppPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesOrderForm extends BaseActivity implements View.OnClickListener, SalesBillListener,
        SalesOrderBottomsheetFragment.OnSalesOrderSubmitListener,
        PaymentBottomsheetFragment.OnPaymentSubmitOnListener, RecyclerviewListener {

    private Context mContext = SalesOrderForm.this;

    public String billNo, strTotalAmt, strBillDate, salesOrderDate, paymentTypeId, extraCharge, discount, tax1, tax2, tax3, totalamount, grandTotal = "0", paidAmount, change, unPaid, cashPay, cardPay, chequePay, chequeNo, bankName, chequeDate, taxStructureId, isFinish, isAlter, uId, bId, cId, id, orderid, productId, stockId, qty, qtyUnit, unitPrice, price, discountInPer, productDiscount, totalPrice, taxStructreId, productTax1, productTax2, productTax3, productName, saleUnitId, strgst;

    public static double totalprice;
    public TextInputLayout tnlBillno, tnlBilldate, tnlMobileno, tnlExtracharge, tnlTotalamt, tnlBalance, tnlCharge, tnlCustomernm, tnl_gst;
    public Spinner spCustomernm, sp_salesgst;
    private ImageView imgAddCustomer, imgAddSales, imgAddPayment;
    private Button btnSubmit;
    private int position;
    private SalesOrderForm salesOrderForm = null;
    private SalesOrderBottomsheetFragment salesOrderBottomsheetFragment;
    private PaymentBottomsheetFragment paymentBottomsheetFragment;
    private BottomSheetDialogFragment bottomSheetDialogFragment;
    //public int totalAmt=0;
    private String[] names;
    private int ids[];
    private int customerid, taxstructureid, product;
    Double totalAmount = 0.0, extracharge = 0.0, lsttotalamount = 0.0;
    private boolean isAdd = false;

    private SalesOrderFormResponse salesOrderFormResponse;
    public SalesOrderFormAdapter salesOrderFormAdapter;

    public PaymentListAdapter paymentListAdapter;

    public ArrayList<PaymentModel> paymentModels = new ArrayList<>();
    public ArrayList<SalesProductModel> salesProductModels = new ArrayList<>();

    public ArrayList<CustomerModel> customerModels = new ArrayList<>();
    private ArrayList<SalesCustomerObject> salesCustomerObjects = new ArrayList<>();
    private ArrayList<SalesProductObject> salesProductObjects = new ArrayList<>();
    private RecyclerView recyclerviewSalesOrderDetail, recyclerviewPayment;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<String> customernm = new ArrayList<>();

    private SalesOrderEditObject salesOrderEditObject = null;
    private ArrayList<SalesListSalesDetail> listSalesDetails = new ArrayList<>();
    private ArrayList<SalesListPaymentDetail> listPaymentDetails = new ArrayList<>();

    private String unit, avstock, strqty, unitprice, strprice, gstper, gstrs = "0", discrs, discper, strtotalprice, bank, amount, chequeno, chequedate, depositeto;

    /****************************************************************************************/
    private SpinnerItemAdapter spinnerAdapter = null;
    private SalesOrderLoadObject salesOrderLoadObject = null;
    private List<CustomerModel> listOfCustomer = new ArrayList<>();
    private List<TaxStructureModel> listOfTaxStructure = new ArrayList<>();
    private List<BankModel> listOfBank = new ArrayList<>();
    private List<SalesProductObject> listOfProducts = new ArrayList<>();
    private String customerName[] = null;
    private String taxStructureName[] = null;
    private int customerSelectedId = 0;
    private int taxSelectedId = 0;
    private String customerSelectedMobile = "";

    private boolean isEdit = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_form);

        LoadCustomerForm();

        initView();


    }

    /**
     * Method to initialize the views
     */
    private void initView() {

        // Getting values from SharedPreference
        cId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_company_id));
        bId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_branch_id));
        uId = AppPreference.getInstance().getStringPreference(mContext, getResources().getString(R.string.pref_user_id));


        // get bill no

        tnlBillno = findViewById(R.id.tnl_billno);
        tnlBilldate = findViewById(R.id.tnl_billdate);
        tnlMobileno = findViewById(R.id.tnl_mobileno);
        tnlExtracharge = findViewById(R.id.tnl_extracharge);
        tnlTotalamt = findViewById(R.id.tnl_totalamt);
        tnlBalance = findViewById(R.id.tnl_balance);
        tnl_gst = findViewById(R.id.tnl_gst);
        tnlCharge = findViewById(R.id.tnl_charge);
        spCustomernm = findViewById(R.id.sp_customernm);
        sp_salesgst = findViewById(R.id.sp_salesgst);
        imgAddCustomer = findViewById(R.id.img_addcustomer);
        imgAddSales = findViewById(R.id.img_add_sales);
        imgAddPayment = findViewById(R.id.img_payment_add);
        btnSubmit = findViewById(R.id.btn_submit);

        strBillDate = getCurrentDate();
        Objects.requireNonNull(tnlBilldate.getEditText()).setText(strBillDate);

        recyclerviewSalesOrderDetail = findViewById(R.id.recycler_view_sales);
        recyclerviewPayment = findViewById(R.id.recycler_view_payment);

        imgAddCustomer.setOnClickListener(this);
        imgAddSales.setOnClickListener(this);
        imgAddPayment.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        Objects.requireNonNull(tnlBilldate.getEditText()).setOnClickListener(this);

        LoadSalesOrderForm();

        sp_salesgst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    TaxStructureModel tax = listOfTaxStructure.get(i - 1);
                    taxSelectedId = tax.getId();
                    sp_salesgst.setSelection(i);
                } else {
                    sp_salesgst.setSelection(0);
                }

                try {
                    if (i == 1) {

                        gstrs = String.valueOf(((Double.parseDouble(grandTotal) * 5) / 100));
                        tnl_gst.getEditText().setText(gstrs);
                        tnlTotalamt.getEditText().setText(String.valueOf(Double.parseDouble(gstrs) + Double.parseDouble(grandTotal)));
                    } else if (i == 2) {
                        gstrs = String.valueOf(((Double.parseDouble(grandTotal) * 12) / 100));
                        tnl_gst.getEditText().setText(gstrs);
                        tnlTotalamt.getEditText().setText(String.valueOf(Double.parseDouble(gstrs) + Double.parseDouble(grandTotal)));
                    } else if (i == 3) {
                        gstrs = String.valueOf(((Double.parseDouble(grandTotal) * 18) / 100));
                        tnl_gst.getEditText().setText(gstrs);
                        tnlTotalamt.getEditText().setText(String.valueOf(Double.parseDouble(gstrs) + Double.parseDouble(grandTotal)));
                    } else if (i == 4) {
                        gstrs = String.valueOf(((Double.parseDouble(grandTotal) * 28) / 100));
                        tnl_gst.getEditText().setText(gstrs);
                        tnlTotalamt.getEditText().setText(String.valueOf(Double.parseDouble(gstrs) + Double.parseDouble(grandTotal)));
                    } else {
                        gstrs = String.valueOf(((Double.parseDouble(grandTotal) * 0) / 100));
                        tnl_gst.getEditText().setText(gstrs);
                        tnlTotalamt.getEditText().setText(String.valueOf(Double.parseDouble(gstrs) + Double.parseDouble(grandTotal)));
                    }
                } catch (Exception e) {
                    Log.d("EXCEPTION", e.toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Objects.requireNonNull(tnlExtracharge.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

               /* extraCharge = tnlExtracharge.getEditText().getText().toString().trim();
                tnlTotalamt.getEditText().setText(extraCharge+strTotalAmt);*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spCustomernm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i > 0) {
                    CustomerModel customer = listOfCustomer.get(i - 1);
                    customerSelectedId = customer.getId();
                    spCustomernm.setSelection(i);
                    tnlMobileno.getEditText().setText(customer.getStrMobileno());
                } else {
                    spCustomernm.setSelection(0);
                    tnlMobileno.getEditText().setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Method is use to Load Sales Order Form with all spinners
     */
    private void LoadSalesOrderForm() {

        try {
            startProgressDialog((Activity) mContext, "", false);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("cid", cId);
            jsonObject.addProperty("bid", bId);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) mContext).create(WebServicesAPI.class);

            Call<SalesOrderLoadResponse> call = webServicesAPI.loadSalesOrderForm(jsonObject);

            call.enqueue(new Callback<SalesOrderLoadResponse>() {
                @Override
                public void onResponse(Call<SalesOrderLoadResponse> call, Response<SalesOrderLoadResponse> response) {

                    stopProgressDialog();

                    if (response != null) {
                        if (response.isSuccessful()) {

                            salesOrderLoadObject = Objects.requireNonNull(response.body()).getObject();

                            if (salesOrderLoadObject != null) {
                                if (salesOrderLoadObject.getListOfCustomer() != null) {
                                    listOfCustomer = salesOrderLoadObject.getListOfCustomer();
                                    setCustomerListToSpinner();

                                }

                                if (salesOrderLoadObject.getListOfTaxes() != null) {
                                    listOfTaxStructure = salesOrderLoadObject.getListOfTaxes();
                                    setTaxListToSpinner();
                                }

                                if (salesOrderLoadObject.getListOfProducts() != null) {
                                    listOfProducts = salesOrderLoadObject.getListOfProducts();

                                }

                                if (salesOrderLoadObject.getListOfBanks() != null) {
                                    listOfBank = salesOrderLoadObject.getListOfBanks();
                                }
                            }

                            Bundle bundle = getIntent().getExtras();
                            if (bundle != null) {
                                isEdit = true;
                                Gson gson = new Gson();
                                String salesOrder = bundle.getString("SalesModel");

                                if (!TextUtils.isEmpty(salesOrder)) {
                                    Type type = new TypeToken<SalesOrderEditObject>() {
                                    }.getType();
                                    salesOrderEditObject = gson.fromJson(salesOrder, type);
                                }
                                setFormsDetail();
                            } else {
                                GetSalesBillNo();
                            }
                        }
                    } else {
                        showCustomToast(mContext, "Response is Null");
                    }
                }

                @Override
                public void onFailure(Call<SalesOrderLoadResponse> call, Throwable t) {
                    Log.d("FAILURE ", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("ERROR ", e.toString());
        }
    }

    /**
     * This method set Tax Structure data into Spinner
     */
    private void setTaxListToSpinner() {
        if (listOfTaxStructure != null) {
            int i = 0;
            taxStructureName = new String[listOfTaxStructure.size() + 1];
            taxStructureName[i++] = "Select GST";
            for (TaxStructureModel taxStructure : listOfTaxStructure) {
                taxStructureName[i++] = taxStructure.getTaxName();
            }
        }
        try {
            sp_salesgst.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, taxStructureName));
//            spinnerAdapter = new SpinnerItemAdapter(this, R.layout.row_spinner_item, new ArrayList<String>(Arrays.asList(taxStructureName)));
//            sp_salesgst.setAdapter(spinnerAdapter);
            sp_salesgst.setSelection(0);
        } catch (Exception e) {
            Log.d("EXCEPTION ", e.toString());
        }
    }

    /**
     * This method set Customer data into Spinner
     */
    private void setCustomerListToSpinner() {
        int i = 0;
        if (listOfCustomer != null) {
            customerName = new String[listOfCustomer.size() + 1];
            customerName[i++] = "Select Customer";
            for (CustomerModel customerData : listOfCustomer) {
                customerName[i++] = customerData.getStrFirstname();
            }
        }
        try {
            spCustomernm.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, customerName));
//            spinnerAdapter = new SpinnerItemAdapter(this, R.layout.row_spinner_item, new ArrayList<String>(Arrays.asList(customerName)));
//            spCustomernm.setAdapter(spinnerAdapter);
            spCustomernm.setSelection(0);
        } catch (Exception e) {
            Log.d("EXCEPTION ", e.toString());
        }
    }


    private void setFormsDetail() {

        if (salesOrderEditObject != null) {
            if (!TextUtils.isEmpty(salesOrderEditObject.getAutoBillNo())) {
                Objects.requireNonNull(tnlBillno.getEditText()).setText(salesOrderEditObject.getAutoBillNo());
            }

            if (!TextUtils.isEmpty(salesOrderEditObject.getSalesOrderDate())) {
                Objects.requireNonNull(tnlBilldate.getEditText()).setText(salesOrderEditObject.getSalesOrderDate());
            }

            customerid = salesOrderEditObject.getCustomerId();

            for (int i = 0; i < listOfCustomer.size(); i++) {
                if (customerid == listOfCustomer.get(i).getId()) {
                    spCustomernm.setSelection(i);
                    Objects.requireNonNull(tnlMobileno.getEditText()).setText(listOfCustomer.get(i).getStrMobileno());
                    break;
                }
            }

            if (!TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getExtraCharge()))) {
                Objects.requireNonNull(tnlExtracharge.getEditText()).setText(String.valueOf(salesOrderEditObject.getExtraCharge()));
            }

            if (!TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getTax1())) &&
                    !TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getTax2())) &&
                    !TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getTax3()))
                    ) {
                Objects.requireNonNull(tnl_gst.getEditText()).setText(String.valueOf(
                        salesOrderEditObject.getTax1() + salesOrderEditObject.getTax2() + salesOrderEditObject.getTax3()
                ));
            }

            if (!TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getTotalAmount()))) {
                Objects.requireNonNull(tnlTotalamt.getEditText()).setText(String.valueOf(salesOrderEditObject.getTotalAmount()));
            }

            if (!TextUtils.isEmpty(String.valueOf(salesOrderEditObject.getChange()))) {
                Objects.requireNonNull(tnlCharge.getEditText()).setText(String.valueOf(salesOrderEditObject.getChange()));
            }

            if (salesOrderEditObject.getListSalesDetails() != null) {
                salesProductModels = salesOrderEditObject.getListSalesDetails();
                setSalesOrderDetailRecyclerView(salesProductModels);
            }

            if (salesOrderEditObject.getListPaymentDetails() != null) {
                paymentModels = salesOrderEditObject.getListPaymentDetails();
                setPaymentDetailsRecyclerView(paymentModels);
            }
        }
    }


    /**
     * Method is use to Load Sales Order Form with all spinners
     */
    private void LoadCustomerForm() {

        try {
            startProgressDialog((Activity) mContext, "", false);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("cid", cId);
            jsonObject.addProperty("bid", bId);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) mContext).create(WebServicesAPI.class);

            Call<SalesOrderLoadResponse> call = webServicesAPI.loadSalesOrderForm(jsonObject);

            call.enqueue(new Callback<SalesOrderLoadResponse>() {
                @Override
                public void onResponse(Call<SalesOrderLoadResponse> call, Response<SalesOrderLoadResponse> response) {

                    stopProgressDialog();

                    if (response != null) {
                        if (response.isSuccessful()) {

                            salesOrderLoadObject = Objects.requireNonNull(response.body()).getObject();

                            if (salesOrderLoadObject != null) {
                                if (salesOrderLoadObject.getListOfCustomer() != null) {
                                    listOfCustomer = salesOrderLoadObject.getListOfCustomer();
                                    setCustomerListToSpinner();

                                }
                            }

                            Bundle bundle = getIntent().getExtras();
                            if (bundle != null) {
                                isEdit = true;
                                Gson gson = new Gson();
                                String salesOrder = bundle.getString("SalesModel");

                                if (!TextUtils.isEmpty(salesOrder)) {
                                    Type type = new TypeToken<SalesOrderEditObject>() {
                                    }.getType();
                                    salesOrderEditObject = gson.fromJson(salesOrder, type);
                                }
                                setFormsDetail();
                            } else {
                                GetSalesBillNo();
                            }
                        }
                    } else {
                        showCustomToast(mContext, "Response is Null");
                    }
                }

                @Override
                public void onFailure(Call<SalesOrderLoadResponse> call, Throwable t) {
                    Log.d("FAILURE ", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("ERROR ", e.toString());
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_addcustomer:

                CustomerBottomsheetFragment customerBottomsheetFragment = new CustomerBottomsheetFragment(this);
                customerBottomsheetFragment.show(getSupportFragmentManager(), customerBottomsheetFragment.getTag());
                break;

            case R.id.img_add_sales:
                SalesOrderBottomsheetFragment salesOrderBottomsheetFragment = new SalesOrderBottomsheetFragment(this, listOfProducts);
                salesOrderBottomsheetFragment.show(getSupportFragmentManager(), salesOrderBottomsheetFragment.getTag());
                salesOrderBottomsheetFragment.setProductListToSpinner();
                LoadSalesOrderForm();
                break;

            case R.id.img_payment_add:
                PaymentBottomsheetFragment paymentBottomsheetFragment = new PaymentBottomsheetFragment(this);
                paymentBottomsheetFragment.show(getSupportFragmentManager(), paymentBottomsheetFragment.getTag());
                break;

            case R.id.edt_billdate:

                selectDateFromDatePickerDialog(tnlBilldate.getEditText(), CURRENT_DATE_FORMAT);
                break;

            case R.id.btn_submit:
                if (!isEdit) {
                    saveSalesOrderData();
                } else {
                    updateSalesOrderData();
                }
                break;
        }
    }

    public void setPaymentDetailsRecyclerView(ArrayList<PaymentModel> paymentModelArrayList) {

        try {

            if (paymentModelArrayList != null && paymentModelArrayList.size() > 0) {

                if (paymentListAdapter == null) {

                    mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    recyclerviewPayment.setLayoutManager(mLayoutManager);
                    recyclerviewPayment.setItemAnimator(new DefaultItemAnimator());
                    paymentListAdapter = new PaymentListAdapter(paymentModelArrayList, mContext);
                    recyclerviewPayment.setAdapter(paymentListAdapter);
                } else {

                    paymentListAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to get sales order data
     */

    public void setSalesOrderDetailRecyclerView(ArrayList<SalesProductModel> listSalesDetails) {

        if (listSalesDetails != null && listSalesDetails.size() > 0) {

            if (salesOrderFormAdapter == null) {

                recyclerviewSalesOrderDetail.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                recyclerviewSalesOrderDetail.setItemAnimator(new DefaultItemAnimator());
                salesOrderFormAdapter = new SalesOrderFormAdapter(listSalesDetails, mContext);
                recyclerviewSalesOrderDetail.setAdapter(salesOrderFormAdapter);

            } else {
                salesOrderFormAdapter.notifyDataSetChanged();
            }
        }
    }

    public void saveSalesOrderData() {

        try {
            billNo = tnlBillno.getEditText().getText().toString();
            strBillDate = tnlBilldate.getEditText().getText().toString();
            extraCharge = tnlExtracharge.getEditText().getText().toString().trim();

            grandTotal = tnlTotalamt.getEditText().getText().toString();
            paidAmount = String.valueOf(Double.parseDouble(grandTotal) - Double.parseDouble(tnlBalance.getEditText().getText().toString()));
            strgst = tnl_gst.getEditText().getText().toString();

            startProgressDialog((Activity) mContext, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) mContext).create(WebServicesAPI.class);

            SalesBillModel salesBillModel = new SalesBillModel();

            salesBillModel.setAutoBillno(billNo);
            salesBillModel.setCustomerId(String.valueOf(customerSelectedId));
            salesBillModel.setSalesOrderDate(dateConversion("dd-MM-yyyy", "yyyy-MM-dd'T'hh:mm:ss", strBillDate));
            salesBillModel.setExtraCharge(extraCharge);
            salesBillModel.setDiscount("0.0");
            salesBillModel.setTax1(strgst);
            salesBillModel.setTax2(strgst);
            salesBillModel.setTax3(strgst);
            salesBillModel.setTotalAmount(grandTotal);
            salesBillModel.setGrandTotal(String.valueOf(Double.parseDouble(grandTotal) + Double.parseDouble(extraCharge)));
            salesBillModel.setPaidAmount("");
            salesBillModel.setChange("0.0");
            salesBillModel.setUnPaid("0.0");
            salesBillModel.setCashPay(totalPrice);
            salesBillModel.setChequePay(totalPrice);
            salesBillModel.setCardPay(totalPrice);
            salesBillModel.setChequeNo("0");
            salesBillModel.setBankName(null);
            salesBillModel.setChequeDate(null);
            salesBillModel.setTaxStructureId(String.valueOf(taxSelectedId));
            salesBillModel.setIsFinish("0");
            salesBillModel.setIsAlter("0");
            salesBillModel.setuId(uId);
            salesBillModel.setBid(bId);
            salesBillModel.setCid(cId);
            salesBillModel.setIsFrom("2");
            salesBillModel.setSalesProductModels(salesProductModels);
            salesBillModel.setPaymentModels(paymentModels);

            Call<SalesOrderFormResponse> call = webServicesAPI.getSalesOrderdata(salesBillModel);

            call.enqueue(new Callback<SalesOrderFormResponse>() {

                @Override
                public void onResponse(Call<SalesOrderFormResponse> call, Response<SalesOrderFormResponse> response) {
                    stopProgressDialog();
                    if (response.isSuccessful()) {

                        Log.d("123", String.valueOf(response.body()));

                        if (response.body() != null) {

                            int x = response.body().getStatus();
                            if (x == 1) {
                                showCustomToast(mContext, "Record inserted successfully");

                            } else {
                                showCustomToast(mContext, "Record not inserted ");
                            }
                        } else {
                            showCustomToast(mContext, response.body().getMessage());
                        }
                    } else {
                        Log.d("123", String.valueOf(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(Call<SalesOrderFormResponse> call, Throwable t) {

                    stopProgressDialog();
                    GlobalAlertDialog(mContext, getResources().getString(R.string.msg_title_api_error), getResources().getString(R.string.msg_api_error), false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get Sales Bill Number
     */
    public void GetSalesBillNo() {
        try {
            startProgressDialog((Activity) mContext, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) mContext).create(WebServicesAPI.class);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bid", bId);
            jsonObject.addProperty("cid", cId);
            jsonObject.addProperty("table", "sales");


            Call<SalesBillNumberResponse> call = webServicesAPI.getSalesBillNo(jsonObject);

            call.enqueue(new Callback<SalesBillNumberResponse>() {

                @Override
                public void onResponse(Call<SalesBillNumberResponse> call, Response<SalesBillNumberResponse> response) {

                    stopProgressDialog();

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            int x = response.body().getStatus();

                            if (x == 1) {
                                billNo = response.body().getObj().toString();
                                tnlBillno.getEditText().setText(billNo);
                            } else {
                                Toast.makeText(mContext, "Bill No  not generated ", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.d("123", String.valueOf(response.errorBody()));
                        }

                    } else {
                        Log.d("123", String.valueOf(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(Call<SalesBillNumberResponse> call, Throwable t) {

                    stopProgressDialog();
                    GlobalAlertDialog(mContext, getResources().getString(R.string.msg_title_api_error), getResources().getString(R.string.msg_api_error), false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to update sales order data
     */

    public void updateSalesOrderData() {

        try {

            extraCharge = tnlExtracharge.getEditText().getText().toString().trim();
            grandTotal = tnlTotalamt.getEditText().getText().toString();
            paidAmount = String.valueOf(Double.parseDouble(grandTotal) - Double.parseDouble(tnlBalance.getEditText().getText().toString()));

            startProgressDialog((Activity) mContext, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient((Activity) mContext).create(WebServicesAPI.class);

            SalesBillModel salesBillModel = new SalesBillModel();

            if (salesOrderEditObject != null) {
                salesBillModel.setId(String.valueOf(salesOrderEditObject.getId()));
                salesBillModel.setOrderno(salesOrderEditObject.getOrderno());
                salesBillModel.setCustomerId(String.valueOf(customerSelectedId));
                salesBillModel.setSalesOrderDate(dateConversion("dd-MM-yyyy", "yyyy-MM-dd", salesOrderEditObject.getSalesOrderDate()));
                salesBillModel.setExtraCharge(extraCharge);
                salesBillModel.setDiscount(String.valueOf(salesOrderEditObject.getDiscount()));
                salesBillModel.setTax1(String.valueOf(salesOrderEditObject.getTax1()));
                salesBillModel.setTax2(String.valueOf(salesOrderEditObject.getTax2()));
                salesBillModel.setTax3(String.valueOf(salesOrderEditObject.getTax3()));
                salesBillModel.setTotalAmount(grandTotal);
                salesBillModel.setGrandTotal(String.valueOf(Double.parseDouble(grandTotal) + Double.parseDouble(extraCharge)));
                salesBillModel.setPaidAmount(paidAmount);
                salesBillModel.setChange(tnlCharge.getEditText().getText().toString());
                salesBillModel.setUnPaid(tnlBalance.getEditText().getText().toString());
                salesBillModel.setCashPay("0.0");
                salesBillModel.setChequePay("0.0");
                salesBillModel.setCardPay("0.0");
                salesBillModel.setChequeNo("0");
                salesBillModel.setBankName(null);
                salesBillModel.setChequeDate(null);
                salesBillModel.setTaxStructureId(String.valueOf(taxSelectedId));
                salesBillModel.setIsActive("1");
                salesBillModel.setIsDelete("0");
                salesBillModel.setuId(uId);
                salesBillModel.setBid(bId);
                salesBillModel.setCid(cId);
                salesBillModel.setSalesProductModels(salesProductModels);
                salesBillModel.setPaymentModels(paymentModels);
            }

            Call<SalesBillUpdateResponse> call = webServicesAPI.updateSalesdata(salesBillModel);

            call.enqueue(new Callback<SalesBillUpdateResponse>() {

                @Override
                public void onResponse(Call<SalesBillUpdateResponse> call, Response<SalesBillUpdateResponse> response) {

                    stopProgressDialog();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            int x = response.body().getStatus();
                            if (x == 1) {
                                Toast.makeText(mContext, "Record Updated successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(mContext, "Record not updated ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            showCustomToast(mContext, response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SalesBillUpdateResponse> call, Throwable t) {

                    stopProgressDialog();
                    GlobalAlertDialog(mContext, getResources().getString(R.string.msg_title_api_error), getResources().getString(R.string.msg_api_error), false);
                }
            });

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void addOrderDetailOnClick
            (ArrayList<SalesProductModel> salesProductModel, SalesOrderBottomsheetFragment
                    salesOrderBottomsheetFragment) {

        if (bottomSheetDialogFragment != null && bottomSheetDialogFragment.getShowsDialog()) {
            bottomSheetDialogFragment.dismiss();

        }
        //setSalesOrderDetailsRecyclerView(salesProductModel);

    }

    @Override
    public void addPaymnetDetailOnClick
            (ArrayList<PaymentModel> paymentModels, PaymentBottomsheetFragment
                    paymentBottomsheetFragment) {

        setPaymentDetailsRecyclerView(paymentModels);
        if (bottomSheetDialogFragment != null && bottomSheetDialogFragment.getShowsDialog()) {
            bottomSheetDialogFragment.dismiss();
        }
    }

    @Override
    public void onSaveClick(SalesProductModel salesProductModel) {

        //System.out.print(salesProductModel);
        salesProductModels.add(salesProductModel);

        if (salesOrderFormAdapter == null) {

            mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerviewSalesOrderDetail.setLayoutManager(mLayoutManager);
            recyclerviewSalesOrderDetail.setItemAnimator(new DefaultItemAnimator());
            salesOrderFormAdapter = new SalesOrderFormAdapter(salesProductModels, mContext);
            recyclerviewSalesOrderDetail.setAdapter(salesOrderFormAdapter);

        } else {
            salesOrderFormAdapter.notifyDataSetChanged();
        }

        double totalAmount = 0;
        for (int i = 0; i < salesProductModels.size(); i++) {
            double totalPrice = 0;

            totalPrice = Double.parseDouble(salesProductModels.get(i).getTotalprice());
            totalAmount = totalAmount + totalPrice;
        }
        grandTotal = String.valueOf(totalAmount);
        tnlTotalamt.getEditText().setText(grandTotal);
        tnlBalance.getEditText().setText(grandTotal);

    }

    @Override
    public void onPaymnetSaveClick(PaymentModel paymentModel) {

        paymentModels.add(paymentModel);

        if (paymentListAdapter == null) {

            mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerviewPayment.setLayoutManager(mLayoutManager);
            recyclerviewPayment.setItemAnimator(new DefaultItemAnimator());
            paymentListAdapter = new PaymentListAdapter(paymentModels, mContext);
            recyclerviewPayment.setAdapter(paymentListAdapter);

        } else {

            paymentListAdapter.notifyDataSetChanged();
        }

        double totalAmount = 0;
        for (int i = 0; i < paymentModels.size(); i++) {
            double totalPrice = 0;

            totalPrice = Double.parseDouble(paymentModels.get(i).getAmount());
            totalAmount = Double.parseDouble(grandTotal) - totalPrice;
        }
        grandTotal = String.valueOf(totalAmount);
        tnlBalance.getEditText().setText(String.valueOf(totalAmount));

    }

    @Override
    public void onDeleteClick(int adapterPosition) {


        if (position >= 0) {
            if (paymentModels != null && paymentModels.size() > 0) {
                paymentModels.remove(position);
            }
            if (paymentListAdapter != null) {
                paymentListAdapter.notifyDataSetChanged();
            } else {
                paymentListAdapter = new PaymentListAdapter(paymentModels, mContext);
                recyclerviewPayment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                recyclerviewPayment.setItemAnimator(new DefaultItemAnimator());
                recyclerviewPayment.setAdapter(paymentListAdapter);
            }
        }

    }

    @Override
    public void onEditClick(int position) {

        if (position >= 0) {
            PaymentModel packet = new PaymentModel();
            if (paymentModels != null && paymentModels.size() > 0) {
                packet = paymentModels.get(position);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelable("paymentModel", packet);

            PaymentBottomsheetFragment parcelInformation = new PaymentBottomsheetFragment(this, paymentModels, position);
            parcelInformation.setArguments(bundle);
            parcelInformation.show(getSupportFragmentManager(), null);
        }


    }

    @Override
    public void onEditProductClick(int position) {

        if (position >= 0) {
            SalesProductModel packet = new SalesProductModel();
            if (salesProductModels != null && salesProductModels.size() > 0) {
                packet = salesProductModels.get(position);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelable("productModel", packet);

            SalesOrderBottomsheetFragment parcelInformation = new SalesOrderBottomsheetFragment(this, salesProductModels, position);
            parcelInformation.setArguments(bundle);
            parcelInformation.show(getSupportFragmentManager(), null);
        }
    }

    @Override
    public void onDeleteProductClick(int adapterPosition) {

        if (position >= 0) {
            if (salesProductModels != null && salesProductModels.size() > 0) {
                salesProductModels.remove(position);
            }
            if (salesOrderFormAdapter != null) {
                salesOrderFormAdapter.notifyDataSetChanged();
            } else {
                salesOrderFormAdapter = new SalesOrderFormAdapter(salesProductModels, mContext);
                recyclerviewSalesOrderDetail.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                recyclerviewSalesOrderDetail.setItemAnimator(new DefaultItemAnimator());
                recyclerviewSalesOrderDetail.setAdapter(salesOrderFormAdapter);
            }
        }

    }

}
