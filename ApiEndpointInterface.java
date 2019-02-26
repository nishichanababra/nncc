package com.mandaliyamedicals.medical.retrofit;

import com.mandaliyamedicals.medical.models.LoginResultInfo;
import com.mandaliyamedicals.medical.models.NotificationListInfo;
import com.mandaliyamedicals.medical.models.OfferListInfo;
import com.mandaliyamedicals.medical.models.OrderListInfo;
import com.mandaliyamedicals.medical.models.OrderPlaceInfo;
import com.mandaliyamedicals.medical.models.OrderPriceInfo;
import com.mandaliyamedicals.medical.models.PrescriptionInfo;
import com.mandaliyamedicals.medical.models.ProductCartInfo;
import com.mandaliyamedicals.medical.models.ProductCategoryListInfo;
import com.mandaliyamedicals.medical.models.ProductDetailsInfo;
import com.mandaliyamedicals.medical.models.ProductSearchInfo;
import com.mandaliyamedicals.medical.models.PromoCodeInfo;
import com.mandaliyamedicals.medical.models.RefillMedicineList;
import com.mandaliyamedicals.medical.models.RelatedProductInfo;
import com.mandaliyamedicals.medical.models.UserAddressInfo;
import com.mandaliyamedicals.medical.models.UserDetail;
import com.mandaliyamedicals.medical.models.WishListInfo;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardCategoryList;
import com.mandaliyamedicals.medical.models.dashbaord.DashboardInfo;
import com.mandaliyamedicals.medical.models.orderDetails.OrderDetailsInfo;
import com.mandaliyamedicals.medical.models.orderQuotation.OrderQuotationInfo;
import com.mandaliyamedicals.medical.models.refillMedicine.RefillOrderDeliveredInfo;
import com.mandaliyamedicals.medical.models.refillMedicine.RefillOrderDetailsInfo;
import com.mandaliyamedicals.medical.models.refillMedicine.RefillOrdersList;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.cartList.LabCartList;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.category.LabTestCategoryList;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.labOrderDetails.LabTestOrderDetailsInfo;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.labOrderList.LabTestBookList;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.labTestDetails.LabTestDetailsInfo;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.labTestList.LabTestList;
import com.mandaliyamedicals.medical.userActivities.LabTest.Model.searchlabTest.SearchLabTestDatum;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.ScheduleDate;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.ScheduleInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.appointmentList.AppointmentDetails;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.appointmentList.AppointmentListInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.cityList.CityListInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.doctorDetails.DoctorDetailsInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.doctorList.DoctorListInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.doctorSpeciality.DoctorSpecialityInfo;
import com.mandaliyamedicals.medical.userActivities.bookDoctor.models.searchDoctor.DoctorSearchInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Fatehali Asamadi on 31/3/16.
 */
public interface ApiEndpointInterface {
    /**
     * @param username
     * @param password
     * @param device_id
     * @param device_token
     * @param device_type
     * @param code
     * @return
     */
    @Multipart
    @POST("login/user_login")
    Call<ResponseBody> userLogin(@Part("username") RequestBody username,
                                 @Part("password") RequestBody password,
                                 @Part("device_id") RequestBody device_id,
                                 @Part("device_token") RequestBody device_token,
                                 @Part("device_type") RequestBody device_type,
                                 @Part("country_code") RequestBody code
    );

    @Multipart
    @POST("login/facebook_login")
    Call<ResponseBody> faceBookLogin(@Part("name") RequestBody username,
                                     @Part("email") RequestBody email,
                                     @Part("device_id") RequestBody device_id,
                                     @Part("device_type") RequestBody device_type,
                                     @Part("mobile") RequestBody mobile,
                                     @Part("facebook_id") RequestBody id
    );

    @Multipart
    @POST("login/google_login")
    Call<ResponseBody> googleLogin(@Part("name") RequestBody username,
                                   @Part("email") RequestBody password,
                                   @Part("device_id") RequestBody device_id,
                                   @Part("device_type") RequestBody device_type,
                                   @Part("mobile") RequestBody mobile,
                                   @Part("google_id") RequestBody id
    );

    /**
     * @param user_id
     * @param fname
     * @param lname
     * @param email
     * @param code
     * @param mobile
     * @param flat
     * @param building
     * @param street
     * @param landmark
     * @param city
     * @param zipcode
     * @return
     */
    @Multipart
    @POST("useraddress/add_user_address")
    Call<ArrayList<UserAddressInfo>> addUserAddress(@Part("user_id") RequestBody user_id,
                                                    @Part("fname") RequestBody fname,
                                                    @Part("lname") RequestBody lname,
                                                    @Part("email") RequestBody email,
                                                    @Part("country_code") RequestBody code,
                                                    @Part("mobile") RequestBody mobile,
                                                    @Part("flat_no") RequestBody flat,
                                                    @Part("building_name") RequestBody building,
                                                    @Part("street") RequestBody street,
                                                    @Part("landmark") RequestBody landmark,
                                                    @Part("city") RequestBody city,
                                                    @Part("state") RequestBody state,
                                                    @Part("zipcode") RequestBody zipcode);


    /**
     * @param email
     * @return
     */
    @Multipart
    @POST("login/forgot_password")
    Call<ResponseBody> forgotPassword(@Part("email") RequestBody email);

    /**
     * @param order_id
     * @return
     */
    @Multipart
    @POST("login/forgot_password")
    Call<ResponseBody> generateInvoice(@Part("order_no") RequestBody order_id);

    /**
     * @param user_address_id
     * @param fname
     * @param lname
     * @param email
     * @param code
     * @param mobile
     * @param flat
     * @param building
     * @param street
     * @param landmark
     * @param city
     * @param zipcode
     * @return
     */
    @Multipart
    @POST("useraddress/edit_user_address")
    Call<ArrayList<UserAddressInfo>> editUserAddress(@Part("user_address_id") RequestBody user_address_id,
                                                     @Part("fname") RequestBody fname,
                                                     @Part("lname") RequestBody lname,
                                                     @Part("email") RequestBody email,
                                                     @Part("country_code") RequestBody code,
                                                     @Part("mobile") RequestBody mobile,
                                                     @Part("flat_no") RequestBody flat,
                                                     @Part("building_name") RequestBody building,
                                                     @Part("street") RequestBody street,
                                                     @Part("landmark") RequestBody landmark,
                                                     @Part("city") RequestBody city,
                                                     @Part("state") RequestBody state,
                                                     @Part("zipcode") RequestBody zipcode);


    /**
     * @param first_name
     * @param code
     * @param mobile
     * @param email
     * @param password
     * @param device_id
     * @param device_token
     * @param device_type
     * @return
     */
    @Multipart
    @POST("register/register_user")
    Call<ArrayList<LoginResultInfo>> userRegister(@Part("first_name") RequestBody first_name,
                                                  @Part("mobile") RequestBody mobile,
                                                  @Part("email") RequestBody email,
                                                  @Part("country_code") RequestBody code,
                                                  @Part("password") RequestBody password,
                                                  @Part("device_id") RequestBody device_id,
                                                  @Part("device_token") RequestBody device_token,
                                                  @Part("device_type") RequestBody device_type);

    /**
     * @param key
     * @return
     */
    @Multipart
    @POST("category/get_category_list")
    Call<ArrayList<DashboardCategoryList>>productCategory(@Part("key") RequestBody key);


    /**
     * @param category_id
     * @return
     */
    @Multipart
    @POST("product/get_product_list")
    Call<ArrayList<ProductCategoryListInfo>> productCategoryList(@Part("category_id") RequestBody category_id);


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("useraddress/get_user_address_list")
    Call<ArrayList<UserAddressInfo>> userAddress(@Part("user_id") RequestBody user_id);


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("ordersinfo/get_order_list")
    Call<ArrayList<OrderListInfo>> orderList(@Part("user_id") RequestBody user_id);

    /**
     * @param payment_id
     * @return
     */
    @Multipart
    @POST("quotation/quotation_payment")
    Call<ResponseBody> setPaymentSuccess(
            @Part("payment_id") RequestBody payment_id,
            @Part("order_id") RequestBody order_id,
            @Part("type") RequestBody type,
            @Part("wallet") RequestBody wallet,
            @Part("pay_amount") RequestBody pay_amount
    );



    /**
     * @param order_id
     * @return
     */
    @Multipart
    @POST("ordersinfo/view_order_quotation_details")
    Call<OrderQuotationInfo> quotationList(@Part("order_id") RequestBody order_id);

    /**
     * @param user_id
     * @param name
     * @param email
     * @param mobile
     * @param image
     * @return
     */
    @Multipart
    @POST("profile/edit_profile")
    Call<List<UserDetail>> editUserDetails(
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile,
            @Part MultipartBody.Part image


    );

    /**
     * @param user_id
     * @param type
     * @return
     */
    @Multipart
    @POST("Notifications/notification_on_off")
    Call<ResponseBody> NotificationOn(@Part("user_id") RequestBody user_id,
                                      @Part("notification_type") RequestBody type);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("useraddress/deactive_address")
    Call<ArrayList<UserAddressInfo>> deactivateUserAddress(@Part("user_id") RequestBody user_id, @Part("user_address_id") RequestBody user_address_id);


    /**
     * @param user_id
     * @param product_id
     * @param qty
     * @return
     */
    @Multipart
    @POST("product/add_to_cart")
    Call<ArrayList<ProductCartInfo>> addToCart(@Part("user_id") RequestBody user_id, @Part("product_id") RequestBody product_id, @Part("qty") RequestBody qty);

    @Multipart
    @POST("product/add_to_kit")
    Call<ArrayList<ProductCartInfo>> addToKit(@Part("user_id") RequestBody user_id, @Part("product_id") RequestBody product_id, @Part("qty") RequestBody qty);


    @Multipart
    @POST("Wishlist/addWishList")
    Call<ArrayList<WishListInfo>> addToWishList(@Part("user_id") RequestBody user_id, @Part("product_id") RequestBody product_id);


    /**
     * @param user_id
     * @param cart_id
     * @return
     */
    @Multipart
    @POST("product/remove_cart_product")
    Call<ArrayList<ProductCartInfo>> removeFromCart(@Part("user_id") RequestBody user_id, @Part("cart_id") RequestBody cart_id);

    @Multipart
    @POST("product/remove_kit_product")
    Call<ArrayList<ProductCartInfo>> removeFromKit(@Part("user_id") RequestBody user_id, @Part("cart_id") RequestBody cart_id);

    @DELETE("Wishlist/removeProductwishList")
    Call<ArrayList<WishListInfo>> removeFromWishList(@Query("user_id") String user_id, @Query("wish_id") String cart_id);


    /**
     * @param cart_id
     * @return
     */
    @Multipart
    @POST("ordersinfo/remove_quotation_product")
    Call<OrderQuotationInfo> removeFromQuotation(@Part("order_id") RequestBody order_id, @Part("cart_id") RequestBody cart_id);

    /**
     * @param key
     * @return
     */
    @Multipart
    @POST("Offers/get_offers_list")
    Call<ArrayList<OfferListInfo>> OfferProduct(@Part("key") RequestBody key);

    /**
     * @param page_no
     * @return
     */
    @Multipart
    @POST("Product/none_prescription_order")
    Call<List<ProductCategoryListInfo>> nonPrecriptionMedicineList(@Part("page") RequestBody page_no);

    /**
     * @param offerId
     * @return
     */
    @Multipart
    @POST("Offers/get_product_offers_list")
    Call<List<ProductCategoryListInfo>> getOfferProductList(@Part("offer_id") RequestBody offerId);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("product/get_user_cart_list")
    Call<ArrayList<ProductCartInfo>> getProductCart(@Part("user_id") RequestBody user_id);

    @Multipart
    @POST("product/get_user_kit_list")
    Call<ArrayList<ProductCartInfo>> getProductKit(@Part("user_id") RequestBody user_id);

    @GET("Wishlist/getuserWishList")
    Call<ArrayList<WishListInfo>> getProductWishList(@Query("user_id") String user_id);


    /**
     * @param product_id
     * @return
     */
    @Multipart
    @POST("product/get_product_details")
    Call<ArrayList<ProductDetailsInfo>> productDetails(
            @Part("product_id") RequestBody product_id,
            @Part("user_id") RequestBody userId);


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("login/logout")
    Call<ArrayList<LoginResultInfo>> logoutUser(@Part("user_id") RequestBody user_id);

    /**
     * @param user_id
     * @param name
     * @return
     */
    @Multipart
    @POST("order/upload_prescription")
    Call<ArrayList<PrescriptionInfo>> uploadPrescription(@Part("user_id") RequestBody user_id,
                                                         @Part MultipartBody.Part name);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("order/get_upload_prescription_list")
    Call<ArrayList<PrescriptionInfo>> getUploadedPrescription(@Part("user_id") RequestBody user_id);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("order/get_prescription_history")
    Call<ArrayList<PrescriptionInfo>> getUploadedPrescriptionHistory(@Part("user_id") RequestBody user_id);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("order/existing_prescription")
    Call<ArrayList<PrescriptionInfo>> addPrescriptionFromHistory(@Part("user_id") RequestBody user_id, @Part("prescription_id") RequestBody prescription_id);

    /* *//**
     * @param user_id
     * @param de_address_id
     * @param bi_address_id
     * @param code
     * @param discount
     * @param type
     * @param remarks
     * @return
     *//*
    @Multipart
    @POST("order/order_place")
    Call<OrderPlaceInfo> placeOrder(@Part("user_id") RequestBody user_id,
                                    @Part("de_address_id") RequestBody de_address_id,
                                    @Part("bi_address_id") RequestBody bi_address_id,
                                    @Part("promocode") RequestBody code,
                                    @Part("promocode_discount") RequestBody discount,
                                    @Part("type") RequestBody type,
                                    @Part("remarks") RequestBody remarks
    );*/

    /**
     * @param user_id
     * @param de_address_id
     * @param bi_address_id
     * @param code
     * @param type
     * @param remarks
     * @return
     */

    @Multipart
    @POST("order/order_place_app")
    Call<OrderPlaceInfo> placeOrder(@Part("user_id") RequestBody user_id,
                                    @Part("de_address_id") RequestBody de_address_id,
                                    @Part("bi_address_id") RequestBody bi_address_id,
                                    @Part("type") RequestBody type,
                                    @Part("remarks") RequestBody remarks,
                                    @Part("wallet_use") RequestBody wallet_use,
                                    @Part("promocode") RequestBody code,
                                    @Part("shipping_charge") RequestBody shipping_charge,
                                    @Part("prescription_description") RequestBody prescription_description,
                                    @Part("delivery_date") RequestBody date,
                                    @Part("payment_type") RequestBody paymentType


    );

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Shippingcharges/get_shipping_charges")
    Call<OrderPriceInfo> getPriceDetails(@Part("user_id") RequestBody user_id);


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Shippingcharges/get_quotation_price_details")
    Call<OrderPriceInfo> getQuotationPriceDetails(@Part("user_id") RequestBody user_id, @Part("quotation_total") RequestBody quotation_total);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("order/remove_prescription")
    Call<ArrayList<PrescriptionInfo>> removeUploadedPrescription(@Part("user_id") RequestBody user_id, @Part("prescription_id") RequestBody prescription_id);

    /**
     * @param product_id
     * @param composition
     * @return
     */
    @Multipart
    @POST("product/substitutes_product")
    Call<ArrayList<RelatedProductInfo>> relatedProducts(@Part("composition") RequestBody composition, @Part("product_id") RequestBody product_id);

    /**
     * @param key
     * @return
     */
    @Multipart
    @POST("Dashboard")
    Call<DashboardInfo> dashboardData(@Part("key") RequestBody key);

    /**
     * @param pincode
     * @param total
     * @return
     */
    @Multipart
    @POST("dashboard/check_promo_code")
    Call<PromoCodeInfo> verifyPromoCode(
            @Part("cart_amount") RequestBody total,
            @Part("code") RequestBody pincode
    );

    /**
     * @param keyword
     * @return
     */
    @Multipart
    @POST("product/search_product")
    Call<ArrayList<ProductSearchInfo>> searchProduct(@Part("keyword") RequestBody keyword);


    /**
     * @param refill_id
     * @return
     */
    @Multipart
    @POST("refillmedicine/remove_refill_medicine")
    Call<ResponseBody> RemoveMedicineRefillById(@Part("refill_product_id") RequestBody refill_id);

    /**
     * @param cart_id
     * @param qty
     * @return
     */
    @Multipart
    @POST("Quotation/update_cart")
    Call<OrderQuotationInfo> updateQuotationQty(@Part("order_id") RequestBody order_id,
                                                @Part("cart_id") RequestBody cart_id,
                                                @Part("qty") RequestBody qty);


    /**
     * @param order_id
     * @param cart_id
     * @param type
     * @return
     */
    @Multipart
    @POST("Quotation/is_selected_price_update")
    Call<OrderQuotationInfo> updateQuotationPrice(@Part("order_id") RequestBody order_id,
                                                  @Part("cart_id") RequestBody cart_id,
                                                  @Part("type") RequestBody type);


    /**
     * @param order_id
     * @param remark
     * @return
     */
    @Multipart
    @POST("ordersinfo/cancelorder")
    Call<ResponseBody> cancelOrder(@Part("order_id") RequestBody order_id, @Part("comment") RequestBody remark);


    /**
     * @param userId
     * @return
     */
    @Multipart
    @POST("Ordersinfo/user_confirm")
    Call<ResponseBody> confirmOrderDelivered(@Part("order_id") RequestBody userId);


    /**
     * @param order_id
     * @return
     */
    @Multipart
    @POST("ordersinfo/get_orders_details")
    Call<OrderDetailsInfo> getOrderDetails(@Part("order_id") RequestBody order_id);

    /**
     * @param orderId
     * @return
     */
    @Multipart
    @POST("quotation/remove_order")
    Call<ResponseBody> CancelOrder(@Part("order_id") RequestBody orderId);

    @Multipart
    @POST("Promocode/check_promo_code")
    Call<ResponseBody> userPromoCode(
            @Part("cart_amount") RequestBody cartTotal,
            @Part("code") RequestBody couponcode
    );

    /**
     * @param order_id
     * @param products
     * @return
     */
    @Multipart
    @POST("quotation/submit_quotation")
    Call<List<OrderQuotationInfo>> submitPrescriptionquotation(
            @Part("order_id") RequestBody order_id,
            @Part("product") RequestBody products,
            @Part("wallet_use") RequestBody wallet_use,
            @Part("promocode") RequestBody code);

    /**
     * @param userId
     * @param qtys
     * @param refillmedicineId
     * @param days
     * @return
     */
    @Multipart
    @POST("refillmedicine/add_refill_product")
    Call<ResponseBody> AddMedicineToRefill(@Part("user_id") RequestBody userId,
                                           @Part("qty") RequestBody qtys,
                                           @Part("product_id") RequestBody refillmedicineId,
                                           @Part("days") RequestBody days);

    /**
     * @param userId
     * @return
     */
    @Multipart
    @POST("refillmedicine/refill_medicine_list")
    Call<List<RefillMedicineList>> GetOrderedMedicinesList(@Part("user_id") RequestBody userId);

    /**
     * @param userId
     * @return
     */
    @Multipart
    @POST("refillmedicine/get_medicine_refill_list")
    Call<List<RefillMedicineList>> getrefillmedicinelist(@Part("user_id") RequestBody userId);


    /**
     * @param user_id
     * @param question
     * @param email
     * @param mobile
     * @param name
     * @return
     */
    @Multipart
    @POST("support/ask_question")
    Call<ResponseBody> askquestion(@Part("user_id") RequestBody user_id,
                                   @Part("name") RequestBody question,
                                   @Part("email") RequestBody email,
                                   @Part("mobile") RequestBody mobile,
                                   @Part("question") RequestBody name);

    /**
     * @param user_id
     * @param question
     * @param mobile
     * @param name
     * @return
     */
    @Multipart
    @POST("Support/request_medicine")
    Call<ResponseBody> requestformedicine(@Part("user_id") RequestBody user_id,
                                          @Part("name") RequestBody name,
                                          @Part("mobile") RequestBody mobile,
                                          @Part("medicine") RequestBody question);

    /**
     * @param userId
     * @return
     */
    @Multipart
    @POST("notifications/show_user_notification")
    Call<List<NotificationListInfo>> notificationlist(@Part("user_id") RequestBody userId);


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("dashboard/get_user_wallet_balance")
    Call<ResponseBody> userWalletAmount(
            @Part("user_id") RequestBody user_id
    );

    /**
     * @param pincode
     * @return
     */
    @Multipart
    @POST("dashboard/check_pincode")
    Call<ResponseBody> getPinCode(
            @Part("addressid") RequestBody pincode
    );

    /**
     * @param user_id
     * @param old
     * @param pwd
     * @return
     */
    @Multipart
    @POST("login/change_password")
    Call<ResponseBody> ResetPwd(
            @Part("user_id") RequestBody user_id,
            @Part("old_password") RequestBody old,
            @Part("new_password") RequestBody pwd

    );

    /**
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("register/send_otp")
    Call<ResponseBody> sendOTP(@Field("mobile") String mobile);

    /**
     * @param mobile
     * @return
     */
    @Multipart
    @POST("register/check_email_mobile")
    Call<ResponseBody> checkEmailAndMobile(@Part("mobile") RequestBody mobile,
                                           @Part("email") RequestBody email);


    @Multipart
    @POST("notifications/count_notification")
    Call<ResponseBody> notificationCount(@Part("user_id") RequestBody userId);

    /**
     * @param book_id
     * @return
     */
    @Multipart
    @POST("medical/cancel_appointment")
    Call<ResponseBody> cancelAppointment(@Part("book_id") RequestBody book_id);

    /**
     * @param doctor_id
     * @param user_id
     * @param patient_name
     * @param patient_email
     * @param book_date
     * @param book_time
     * @return
     */
    @Multipart
    @POST("doctor/doctorApoinment")
    Call<ResponseBody> bookAppointment(
            @Part("doctor_id") RequestBody doctor_id,
            @Part("user_id") RequestBody user_id,
            @Part("patient_name") RequestBody patient_name,
            @Part("patient_email") RequestBody patient_email,
            @Part("aponitment_date") RequestBody book_date,
            @Part("aponitment_time") RequestBody book_time,
            @Part("time_id") RequestBody time_id);


    /**
     * @return
     */
    @Multipart
    @POST("doctor/getDoctorListByCityAndSpeciality")
    Call<ArrayList<DoctorListInfo>> getDoctorListByCity(
            @Part("speciality_id") RequestBody speciality_id,
            @Part("city_id") RequestBody city_id
    );

    /**
     * @param medical_id
     * @return
     */
    @Multipart
    @POST("city/getCityList")
    Call<ArrayList<CityListInfo>> getDoctorCityList(
            @Part("key") RequestBody medical_id
    );

    /**
     * @return
     */
    @Multipart
    @POST("speciality/getSpecialityList")
    Call<ArrayList<DoctorSpecialityInfo>> getDoctorSpecialities(
            @Part("key") RequestBody key
    );

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("doctor/getApointmentList")
    Call<ArrayList<AppointmentListInfo>> getDoctorAppointment(@Part("user_id") RequestBody user_id);


    /**
     * @param doctor_id
     * @return
     */
    @Multipart
    @POST("speciality/getDoctorDetails")
    Call<ArrayList<DoctorDetailsInfo>> GetDoctorDetailsById(@Part("doctor_id") RequestBody doctor_id);

    /**
     * @return
     */
    @Multipart
    @POST("doctor/getDoctorBysearch")
    Call<ArrayList<DoctorSearchInfo>> searchDoctorByKeyword(
            @Part("keyword") RequestBody keyword,
            @Part("city_id") RequestBody city
    );


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Labtest/labTestCartList")
    Call<List<LabCartList>> getLabTestCartList(
            @Part("user_id") RequestBody user_id
    );

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Labtestorder/labOrderList")
    Call<List<LabTestBookList>> getLabTestOrderList(@Part("user_id") RequestBody user_id);


    /**
     * @param medical_id
     * @return
     */
    @Multipart
    @POST("Labcategory/labTestCategoryList")
    Call<List<LabTestCategoryList>> getLabTestCategory(
            @Part("key") RequestBody medical_id
    );

    /**
     * @param lab_test_id
     * @param userId
     * @return
     */
    @Multipart
    @POST("Labtest/removeLabTestCart")
    Call<ResponseBody> RemoveLabTestFromcart(
            @Part("user_id") RequestBody userId,
            @Part("tmp_test_cart_id") RequestBody lab_test_id
    );

    /**
     * @param lab_test_id
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Labtest/labTestAdtoCart")
    Call<ResponseBody> AddLabTestToCart(
            @Part("lab_test_id") RequestBody lab_test_id,
            @Part("price") RequestBody price,
            @Part("user_id") RequestBody user_id
    );

    /**
     * @param lab_book_id
     * @return
     */
    @Multipart
    @POST("Labtestorder/LabOrderCancel")
    Call<ResponseBody> cancelBookLabTest(@Part("lab_order_id") RequestBody lab_book_id);


    @Multipart
    @POST("Labtest/labTestDetails")
    Call<LabTestDetailsInfo> GetLabTestDetailsById(@Part("labtest_id") RequestBody lab_test_id);

    /**
     * @param book_id
     * @return
     */
    @Multipart
    @POST("Labtestorder/labOrderDetails")
    Call<LabTestOrderDetailsInfo> getLabTestDetailsById(
            @Part("lab_order_id") RequestBody book_id
    );

    /**
     * @param category_id
     * @return
     */
    @Multipart
    @POST("Labtest/labTestList")
    Call<List<LabTestList>> getLabTestByCategory(
            @Part("lab_category_id") RequestBody category_id
    );

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Labtestorder/orderPlace")
    Call<ResponseBody> bookLabTestWithDetails(
            @Part("user_id") RequestBody user_id
    );

    /**
     * @param medical_id
     * @param couponcode
     * @param cart_total
     * @return
     */
    @Multipart
    @POST("medical/labtest_verify_couponcode")
    Call<ResponseBody> applyLabTestCouponCode(
            @Part("medical_id") RequestBody medical_id,
            @Part("couponcode") RequestBody couponcode,
            @Part("cart_total") RequestBody cart_total
    );

    /**
     * @param keyword
     * @return
     */
    @Multipart
    @POST("Labtest/labTestSearchByname")
    Call<List<SearchLabTestDatum>> searchLabTestByName(
            @Part("medical_id") RequestBody medical_id,
            @Part("keyword") RequestBody keyword
    );

    /**
     * @return
     */
    @Multipart
    @POST("notifications/show_notification")
    Call<ResponseBody> notificationShowRequest(
            @Part("user_id") RequestBody user_id,
            @Part("notification_id") RequestBody notification_id
    );


    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("refillorder/get_refill_order_list")
    Call<ArrayList<RefillOrdersList>> getUserRefillOrderList(
            @Part("user_id") RequestBody user_id
    );

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Refillorder/remove_refill_prescription_list")
    Call<ArrayList<PrescriptionInfo>> removeRefillUploadedPrescription(
            @Part("user_id") RequestBody user_id,
            @Part("prescription_id") RequestBody prescription_id);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Refillorder/refill_prescription_list")
    Call<ArrayList<PrescriptionInfo>> getRefillUploadedPrescription(@Part("user_id") RequestBody user_id);

    /**
     * @param user_id
     * @return
     */
    @Multipart
    @POST("Refillorder/refill_order")
    Call<ResponseBody> refillOrderWithData(
            @Part("user_id") RequestBody user_id,
            @Part("address_id") RequestBody address_id,
            @Part("patient_name") RequestBody patient_name,
            @Part("patient_relationship") RequestBody patient_relationship,
            @Part("all_medicine_from_prescription") RequestBody all_medicine_from_prescription,
            @Part("from_prescription_comment") RequestBody from_prescription_comment,
            @Part("refill_start_date") RequestBody refill_start_date,
            @Part("refill_cycle") RequestBody refill_cycle,
            @Part("medicine_details") RequestBody medicine_details

    );


    /**
     * @param user_id
     * @param name
     * @return
     */
    @Multipart
    @POST("Refillorder/upload_prescription")
    Call<ArrayList<PrescriptionInfo>> uploadRefillPrescription(@Part("user_id") RequestBody user_id,
                                                               @Part MultipartBody.Part name);

    /**
     * @param refill_order_id
     * @return
     */
    @Multipart
    @POST("refillorder/get_refill_order_date_status")
    Call<RefillOrderDeliveredInfo> refillOrderDelivered(
            @Part("refill_order_id") RequestBody refill_order_id
    );


    /**
     * @param refill_order_id
     * @return
     */
    @Multipart
    @POST("refillorder/get_refill_order_details")
    Call<RefillOrderDetailsInfo> userRefillOrderDetails(
            @Part("refill_order_id") RequestBody refill_order_id
    );

    /**
     * @param refill_order_id
     * @param refill_order_status
     * @return
     */
    @Multipart
    @POST("refillorder/change_refill_order_status")
    Call<ResponseBody> enableOrDisableRefillMedicine(
            @Part("refill_order_id") RequestBody refill_order_id,
            @Part("refill_order_status") RequestBody refill_order_status
    );


    @Multipart
    @POST("doctor/askQuestion")
    Call<ResponseBody> askquestion(
            @Part("user_id") RequestBody user_id,
            @Part("doctor_id") RequestBody doctor_id,
            @Part("question") RequestBody question
    );

    @Multipart
    @POST("doctor/getDoctorTime")
    Call<ArrayList<ScheduleInfo>> getDoctorTime(
            @Part("doctor_id") RequestBody doctor_id,
            @Part("date") RequestBody question
    );

    @Multipart
    @POST("doctor/getDoctorDateById")
    Call<ArrayList<ScheduleDate>> getDoctorDate(
            @Part("doctor_id") RequestBody doctor_id
    );

    @Multipart
    @POST("doctor/getApointmentDetails")
    Call<ArrayList<AppointmentDetails>> getAppointmentDetails(
            @Part("apointment_id") RequestBody apointment_id
    );

    /**
     * @param order_id
     * @param remark
     * @return
     */
    @Multipart
    @POST("doctor/cancleApointment")
    Call<ResponseBody> cancleAppointment(@Part("apointment_id") RequestBody order_id,
                                         @Part("comment") RequestBody remark);

    @Multipart
    @POST("register/CheckMobileEmail")
    Call<ResponseBody> checkEmailMobile(
            @Part("email") RequestBody email,
            @Part("mobile") RequestBody mobile
    );

    @Multipart
    @POST("dashboard/confrim_payment")
    Call<ResponseBody> setPaymentSuccessfull(
            @Part("payment_id") RequestBody payment_id,
            @Part("order_id") RequestBody order_id,
            @Part("type") RequestBody type,
            @Part("wallet") RequestBody wallet,
            @Part("pay_amount") RequestBody pay_amount
    );
}
