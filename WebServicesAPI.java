package app.citta.retail365cloud.retrofit;

import com.google.gson.JsonObject;

import app.citta.retail365cloud.apimodels.AuthenticationResponse;
import app.citta.retail365cloud.apimodels.AutomaticPoResponse;
import app.citta.retail365cloud.apimodels.BankPaymentResponse;
import app.citta.retail365cloud.apimodels.ChangePasswordResponse;
import app.citta.retail365cloud.apimodels.CustomerListResponse;
import app.citta.retail365cloud.apimodels.DashboardResponse;
import app.citta.retail365cloud.apimodels.EmployeeListResponse;
import app.citta.retail365cloud.apimodels.GSTPurchaseReportResponse;
import app.citta.retail365cloud.apimodels.GSTPurchaseResponse;
import app.citta.retail365cloud.apimodels.GSTSalesReportResponse;
import app.citta.retail365cloud.apimodels.GSTSalesResponse;
import app.citta.retail365cloud.apimodels.LedgerReportResponse;
import app.citta.retail365cloud.apimodels.MonthEndReportResponse;
import app.citta.retail365cloud.apimodels.ProductStockResponse;
import app.citta.retail365cloud.apimodels.PurchaseBillDetailResponse;
import app.citta.retail365cloud.apimodels.PurchaseBillLoadResponse;
import app.citta.retail365cloud.apimodels.PurchaseBillResponse;
import app.citta.retail365cloud.apimodels.PurchaseOrderEditResponse;
import app.citta.retail365cloud.apimodels.PurchaseOrderLoadResponse;
import app.citta.retail365cloud.apimodels.PurchaseOrderResponse;
import app.citta.retail365cloud.apimodels.PurchaseReportResponse;
import app.citta.retail365cloud.apimodels.PurchaseReturnDetailResponse;
import app.citta.retail365cloud.apimodels.PurchaseReturnReportResponse;
import app.citta.retail365cloud.apimodels.PurchaseReturnResponse;
import app.citta.retail365cloud.apimodels.SalesBillAccountGrpResponse;
import app.citta.retail365cloud.apimodels.SalesBillCountryResponse;
import app.citta.retail365cloud.apimodels.SalesBillGSTResponse;
import app.citta.retail365cloud.apimodels.SalesBillNumberResponse;
import app.citta.retail365cloud.apimodels.SalesBillStateResponse;
import app.citta.retail365cloud.apimodels.SalesBillUpdateResponse;
import app.citta.retail365cloud.apimodels.SalesCustomerResponse;
import app.citta.retail365cloud.apimodels.SalesDetailResponse;
import app.citta.retail365cloud.apimodels.SalesListResponse;
import app.citta.retail365cloud.apimodels.SalesNewCustomerResponse;
import app.citta.retail365cloud.apimodels.SalesOrderEditResponse;
import app.citta.retail365cloud.apimodels.SalesOrderFormResponse;
import app.citta.retail365cloud.apimodels.SalesOrderLoadResponse;
import app.citta.retail365cloud.apimodels.SalesProductResponse;
import app.citta.retail365cloud.apimodels.SalesReportResponse;
import app.citta.retail365cloud.apimodels.SalesReturnCustomerResponse;
import app.citta.retail365cloud.apimodels.SalesReturnDetailResponse;
import app.citta.retail365cloud.apimodels.SalesReturnFormAddResponse;
import app.citta.retail365cloud.apimodels.SalesReturnReportResponse;
import app.citta.retail365cloud.apimodels.SalesReturnResponse;
import app.citta.retail365cloud.apimodels.SalesReturnUpdateResponse;
import app.citta.retail365cloud.apimodels.StockDetailResponse;
import app.citta.retail365cloud.apimodels.StockTransferResponse;
import app.citta.retail365cloud.apimodels.SupplierListResponse;
import app.citta.retail365cloud.apimodels.UpdateUserInfoResponse;
import app.citta.retail365cloud.apimodels.UserInfoResponse;
import app.citta.retail365cloud.apimodels.UserLoginResponse;
import app.citta.retail365cloud.models.PurchaseOrderModel;
import app.citta.retail365cloud.models.SalesBillModel;
import app.citta.retail365cloud.models.SalesReturnModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServicesAPI {

    /**
     * Get user login details
     */
    @GET("api/checklogin")
    Call<UserLoginResponse> getUserDetails(@Query("username") String username, @Query("password") String password);

    /**
     * Get user login details
     */
    @GET("api/GetCustomerByAuthenticationKey")
    Call<AuthenticationResponse> setAuthentication(@Query("authenticationKey") String authenticationKey);


    /**
     * Set user password
     */
    @POST("api/setpassword")
    Call<ChangePasswordResponse> setPassoword(@Body JsonObject requestbody);

    /**
     * Set user password
     */
    @GET("api/Profile/GetUserInfo")
    Call<UserInfoResponse> getUserInfo(@Query("username") String username, @Query("password") String password);

    /**
     * update user information
     */
    @POST("api/updateUserInfo")
    Call<UpdateUserInfoResponse> updateUserInfo(@Body JsonObject requestbody);

    /**
     * Get customer details
     */
    @POST("api/Customer/GetCustomerList")
    Call<CustomerListResponse> getCustomerList(@Body JsonObject requestbody);

    /**
     * Get employee details
     */
    @POST("api/Employee/GetEmployeeList")
    Call<EmployeeListResponse> getEmployeeList(@Body JsonObject requestBody);

    /**
     * Get sales list
     */
    @POST("api/Sales/GetSalesList")
    Call<SalesListResponse> getSalesList(@Body JsonObject jsonObject);

    /**
     * Get sales details
     */
    @POST("api/Sales/GetSalesDetail")
    Call<SalesDetailResponse> getSalesDetail(@Body JsonObject jsonObject);

    /**
     * Get Supplier details
     */
    @POST("api/Supplier/GetSupplierList")
    Call<SupplierListResponse> getSupplierList(@Body JsonObject requestbody);

    /**
     * Get Purchase bill list
     */
    @POST("api/Purchase/GetPurchaseList")
    Call<PurchaseBillResponse> getPurchaseBillList(@Body JsonObject requestbody);

    /**
     * Get purchase bill details
     */
    @POST("api/Purchase/GetPurchaseDetail")
    Call<PurchaseBillDetailResponse> getPurchaseBillDetail(@Body JsonObject requestbody);

    /**
     * Get Sales return list
     */
    @POST("api/SalesReturn/GetSalesReturnList")
    Call<SalesReturnResponse> getSalesReturnList(@Body JsonObject requestbody);

    /**
     * Get Sales return details
     */
    @POST("api/SalesReturn/GetSalesReturnDetail")
    Call<SalesReturnDetailResponse> getSalesReturnDetail(@Body JsonObject requestbody);

    /**
     * Get Purchase return list
     */
    @POST("api/PurchaseReturn/GetPurchaseReturnList")
    Call<PurchaseReturnResponse> getPurchaseReturnList(@Body JsonObject requestbody);


    /**
     * Get Purchase return details
     */
    @POST("api/Purchase/GetPurchaseReturnDetail")
    Call<PurchaseReturnDetailResponse> getPurchaseReturnDetail(@Body JsonObject requestbody);


    /**
     * Get GST Sales details
     */
    @POST("api/GSTSales/GetGSTSalesList")
    Call<GSTSalesResponse> getGSTSalesList(@Body JsonObject requestbody);


    /**
     * Get GST Purchase details
     */
    @POST("api/GSTPurchase/GetGSTPurchaseList")
    Call<GSTPurchaseResponse> getGSTPurchaseList(@Body JsonObject requestbody);

    /**
     * Get Product details
     */
    @POST("api/Product/GetProductList")
    Call<ProductStockResponse> getProductList(@Body JsonObject requestbody);


    /**
     * Get stock transfer details
     */
    @POST("api/Stock/GetStockTransferList")
    Call<StockTransferResponse> getStockTransferList(@Body JsonObject requestbody);

    /**
     * Get stock transfer details
     */
    @POST("api/Stock/GetStockList")
    Call<StockDetailResponse> getStockDetail(@Body JsonObject requestbody);

    /**
     * Get ledger report details
     */
    @POST("api/LedgerReport")
    Call<LedgerReportResponse> getLedgerReport(@Body JsonObject requestbody);

    /**
     * Get month end sales report details
     */
    @POST("api/MonthEndReport")
    Call<MonthEndReportResponse> getMonthEndReport(@Body JsonObject requestbody);


    /**
     * Get gst sales  report details
     */
    @POST("api/GSTSalesReport")
    Call<GSTSalesReportResponse> getGSTSalesReport(@Body JsonObject requestbody);

    /**
     * Get gst purchase_report details
     */
    @POST("api/GSTPurchaseReport")
    Call<GSTPurchaseReportResponse> getGSTPurchaseReport(@Body JsonObject requestbody);

    /**
     * Get dashboard amount details
     */
    @POST("api/Dashboard")
    Call<DashboardResponse> getDashboardAmount(@Body JsonObject requestbody);

    /**
     * Get sales report details
     */
    @POST("api/SalesOrderReport")
    Call<SalesReportResponse> getSalesReport(@Body JsonObject requestbody);

    /**
     * Get sales return report details
     */
    @POST("api/SalesReturnReport")
    Call<SalesReturnReportResponse> getSalesReturnReport(@Body JsonObject requestbody);


    /**
     * Get purchase  report details
     */
    @POST("api/PurchaseBillReport")
    Call<PurchaseReportResponse> getPurchaseReport(@Body JsonObject requestbody);

    /**
     * Get purchase  report details
     */
    @POST("api/PurchaseReturnReport")
    Call<PurchaseReturnReportResponse> getPurchaseReturnReport(@Body JsonObject requestbody);

    /**
     * Add New Sales Order
     */
    @POST("api/Sales/Add")
    Call<PurchaseReturnReportResponse> addNewSalesOrder(@Body JsonObject requestBody);


    /**
     * Get purchase  report details
     */
    @POST("api/Sales/Add")
    Call<SalesOrderFormResponse> getSalesOrderdata(@Body SalesBillModel salesBillModel);

    /**
     * Get sales product list
     */
    @POST("api/Sales/ProductList")
    Call<SalesProductResponse> getSalesProductdata(@Body JsonObject requestbody);

    /**
     * Get sales customer list
     */
    @POST("api/Sales/CustomerList")
    Call<SalesCustomerResponse> getSalesCustomerdata(@Body JsonObject requestbody);

    /**
     * Get bank list
     */
    @POST("api/Sales/BankList")
    Call<BankPaymentResponse> getBankPaymentdata(@Body JsonObject requestbody);

    /**
     * delete the sales bill record
     */
    @POST("api/Sales/Delete")
    Call<SalesListResponse> deleteSalesdata(@Body JsonObject requestbody);

    /**
     * delete the sales bill record
     */
    @POST("api/Sales/Update")
    Call<SalesBillUpdateResponse> updateSalesdata(@Body SalesBillModel salesBillModel);


    /**
     * get the sales bill gst list
     */
    @POST("api/Sales/TaxList")
    Call<SalesBillGSTResponse> getSalesGSTdata(@Body JsonObject requestbody);

    /**
     * get the sales bill country list
     */
    @POST("api/Country")
    Call<SalesBillCountryResponse> getSalesCountrydata(@Body JsonObject requestbody);

    /**
     * get the sales bill country list
     */
    @POST("api/State")
    Call<SalesBillStateResponse> getSalesStatedata(@Body JsonObject requestbody);

    /**
     * get the sales bill acccount group list
     */
    @POST("api/Sales/AccountGroup")
    Call<SalesBillAccountGrpResponse> getSalesAccountgrpdata(@Body JsonObject requestbody);

    /**
     * get the sales return
     * <p>
     * *
     */
    @POST("api/SR/GetOrderDetail")
    Call<SalesReturnCustomerResponse> getSalesReturnCustomerdata(@Body JsonObject requestbody);

    /**
     * delete the sales return data
     * <p>
     * *
     */
    @POST("api/SR/Delete")
    Call<SalesReturnCustomerResponse> deleteSaleReturnData(@Body JsonObject requestbody);

    /**
     * get the sales return
     * <p>
     * *
     */
    @POST("api/Sales/AddCustomer")
    Call<SalesNewCustomerResponse> getSalesNewCustomerdata(@Body JsonObject requestbody);

    /**
     * get the sales auto bill no
     * <p>
     * *
     */
    @POST("api/GetMaxNumber")
    Call<SalesBillNumberResponse> getSalesBillNo(@Body JsonObject requestbody);

    /**
     * update the sales return data
     * <p>
     * *
     */
    @POST("api/SR/Update")
    Call<SalesReturnUpdateResponse> updateSalesReturndata(@Body JsonObject requestbody);

    /**
     * update the sales return data
     * <p>
     * *
     */
    @POST("api/SR/Edit")
    Call<SalesReturnCustomerResponse> editSalesReturndata(@Body JsonObject requestbody);

    /**
     * add the sales return form data
     * <p>
     * *
     */
    @POST("api/SR/Add")
    Call<SalesReturnFormAddResponse> addSalesReturndata(@Body SalesReturnModel salesReturnModel);


    /**
     * get the sales auto bill no
     * <p>
     * *
     */
    @POST("api/SR/GetOrderDetail")
    Call<SalesReturnCustomerResponse> getSalesReturnOrderDetail(@Body JsonObject requestbody);

    /**
     * get the sales auto bill no
     * <p>
     * *
     */
    @POST("api/Sales/Edit")
    Call<SalesOrderEditResponse> editSalesOrderDetail(@Body JsonObject requestbody);


    @POST("api/Sales/Load")
    Call<SalesOrderLoadResponse> loadSalesOrderForm(@Body JsonObject requestbody);


    /**
     * get the sales auto bill no
     * <p>
     * *
     */
    @POST("api/Purchase/POList")
    Call<PurchaseOrderResponse> getPOList(@Body JsonObject requestbody);


    /**
     * get the sales auto bill no (method name change krvanu)
     * <p>
     * *
     */
    @POST("api/Purchase/LowStockList")
    Call<AutomaticPoResponse> getlowStockList(@Body JsonObject requestbody);


    @POST("api/PO/Load")
    Call<PurchaseOrderLoadResponse> loadPOForm(@Body JsonObject requestbody);


    /**
     * get the sales auto bill no
     * <p>
     * *
     */
    @POST("api/PO/Edit")
    Call<PurchaseOrderEditResponse> editPOList(@Body JsonObject requestbody);

    /**
     * delete the sales bill record
     */
    @POST("api/SalesReturn/Update")
    Call<SalesReturnCustomerResponse> updateSalesReturndata(@Body SalesReturnModel salesReturnModel);

    /**
     * add the purchase order record
     */
    @POST("api/PO/Add")
    Call<PurchaseOrderLoadResponse> getPOdata(@Body PurchaseOrderModel purchaseOrderModel);

    /**
     * update the purchase order record
     */
    @POST("api/PO/Update")
    Call<PurchaseOrderEditResponse> updatepurchaseorderdata(@Body PurchaseOrderModel purchaseOrderModel);


    /**
     * load the purchase bill record
     */
    @POST("api/PB/Load")
    Call<PurchaseBillLoadResponse> loadPBdata(@Body JsonObject requestbody);

    @POST("api/PB/Add")
    Call<PurchaseBillAddResponse> addPBdata(@Body PurchaseBillModel purchaseBillModel);

}
