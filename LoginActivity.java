package com.mandaliyamedicals.medical.userActivities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mandaliyamedicals.medical.CustomAppCompatActivity;
import com.mandaliyamedicals.medical.Dashboard;
import com.mandaliyamedicals.medical.R;
import com.mandaliyamedicals.medical.databinding.ActivityLoginBinding;
import com.mandaliyamedicals.medical.generalHelper.AppUtil;
import com.mandaliyamedicals.medical.generalHelper.DialogUtil;
import com.mandaliyamedicals.medical.generalHelper.GH;
import com.mandaliyamedicals.medical.generalHelper.L;
import com.mandaliyamedicals.medical.generalHelper.SP;
import com.mandaliyamedicals.medical.interfaces.CDialogListener;
import com.mandaliyamedicals.medical.retrofit.RetrofitBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asama on 06-04-2017.
 * LoginActivity
 */

public class LoginActivity extends CustomAppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Context mContext;
    private ActivityLoginBinding binding;
    private static final String EMAIL = "email";
    private String name, email, id;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private Dialog dialogOTP;
    private EditText etOtp;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;

    CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        binding.btnLogin.setOnClickListener(this);
        binding.txtRegister.setOnClickListener(this);
        binding.txtForgotPassword.setOnClickListener(this);
        binding.btnFacebook.setOnClickListener(this);


        GH.addEditTextChangeListener(binding.tilMobileNumber, binding.etMobileNumber);
        GH.addEditTextChangeListener(binding.tilPassword, binding.etPassword);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        binding.btnGoogle.setOnClickListener(this);

        binding.btnFB.setReadPermissions("email", "public_profile");
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String userId = loginResult.getAccessToken().getUserId();

                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    name = object.getString("first_name");
                                    email = object.getString("email");
                                    id = object.getString("id");
                                    RequstToGetUserDateFacebook(name, email, id);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                L.showError(String.valueOf(object));
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "first_name,email,id");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        setResult(RESULT_CANCELED);
                        finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


    }

    private void firbaseTest() {
        showProgress(getString(R.string.requesting), false);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + binding.etMobileNumber.getText().toString().trim(),         // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verificaiton without
            //     user action.
            dismissProgress();
            /*Toast.makeText(mContext, "onVerificationCompleted " + phoneAuthCredential.toString(), Toast.LENGTH_SHORT).show();*/
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            showProgress(getString(R.string.requesting), false);
            Toast.makeText(mContext, "onVerificationFailed " + e.toString(), Toast.LENGTH_SHORT).show();

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                dismissProgress();
                Toast.makeText(mContext, "Invalid Request " + e.toString(), Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                dismissProgress();
                Toast.makeText(mContext, "The SMS quota for the project has been exceeded " + e.toString(), Toast.LENGTH_SHORT).show();
            }
            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(String verificationId,
                               PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.

            mVerificationId = verificationId;
            mResendToken = token;
            /*Toast.makeText(mContext, "onCodeSent " + verificationId, Toast.LENGTH_SHORT).show();*/
            dismissProgress();
            showDialogForOTP();
            // Save verification ID and resending token so we can use them later

            PhoneAuthProvider.ForceResendingToken mResendToken = token;

            // ...
        }

    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (dialogOTP != null && dialogOTP.isShowing()) {
                                dialogOTP.cancel();
                            }

                            requestToLoginUser();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(mContext, "Verify code invalid ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

    private void showDialogForOTP() {
        dialogOTP = new Dialog(LoginActivity.this);
        dialogOTP.setCancelable(false);
        dialogOTP.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogOTP.setCancelable(false);
        try {
            dialogOTP.setContentView(R.layout.view_otp_dialog);
        } catch (Exception e) {
            L.showError(e.getMessage());
        }
        LinearLayout llResend = dialogOTP.findViewById(R.id.llResend);
        llResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOTP.cancel();
                firbaseTest();
            }
        });
        dialogOTP.setCanceledOnTouchOutside(true);
        Button btn = dialogOTP.findViewById(R.id.btnSend);
        etOtp = dialogOTP.findViewById(R.id.etOtp);
        Button txtCanel = dialogOTP.findViewById(R.id.btnCancle);
        TextView txtMobile = dialogOTP.findViewById(R.id.txtMobile);
        txtMobile.setText(binding.etMobileNumber.getText().toString().trim());
        txtCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOTP.cancel();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, etOtp.getText().toString().trim());
                signInWithPhoneAuthCredential(credential);

            }
        });
        dialogOTP.show();
    }
    private void RequstToGetUserDateFacebook(String name, String email, String id) {
        RequestBody firstName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody emailId = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), FirebaseInstanceId.getInstance().getToken() == null ? "Empty" : FirebaseInstanceId.getInstance().getToken());
        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody mobileNumber = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody fbid = RequestBody.create(MediaType.parse("text/plain"), id);


        showProgress(getString(R.string.requesting), false);

        Call<ResponseBody> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .faceBookLogin(
                        firstName,
                        emailId,
                        deviceId,
                        deviceType,
                        mobileNumber,
                        fbid
                );
        arrayListCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissProgress();
                try {
                    JSONObject jsonObject = new JSONObject(GH.RetrofitBufferReaderResponse(response));
                    if (jsonObject.has("status")) {

                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            signOut();
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            JSONArray jsonAddress = jsonObject.getJSONArray("result_address");
                            setUserDetail(jsonArray, jsonAddress);
                            Intent intent = new Intent(mContext, Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            DialogUtil.showMessageDialog(mContext, jsonObject.getString("msg"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissProgress();
                DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
            }
        });

    }

    private void RequstToGetUserDateGoogle(String name, String email, String id) {
        RequestBody firstName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody emailId = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), FirebaseInstanceId.getInstance().getToken() == null ? "Empty" : FirebaseInstanceId.getInstance().getToken());
        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody mobileNumber = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody fbid = RequestBody.create(MediaType.parse("text/plain"), id);


        showProgress(getString(R.string.requesting), false);

        Call<ResponseBody> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .googleLogin(
                        firstName,
                        emailId,
                        deviceId,
                        deviceType,
                        mobileNumber,
                        fbid
                );
        arrayListCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissProgress();
                try {
                    JSONObject jsonObject = new JSONObject(GH.RetrofitBufferReaderResponse(response));
                    if (jsonObject.has("status")) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            signOut();
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            JSONArray jsonAddress = jsonObject.getJSONArray("result_address");
                            setUserDetail(jsonArray, jsonAddress);
                            Intent intent = new Intent(mContext, Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            DialogUtil.showMessageDialog(mContext, jsonObject.getString("msg"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissProgress();
                DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            acct.getId();
            id = acct.getId();
            name = acct.getDisplayName();
            email = acct.getEmail();

            RequstToGetUserDateGoogle(name, email, id);


        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFacebook:
                binding.btnFB.performClick();
                break;
            case R.id.btnGoogle:
                signInGoogle();
                break;
            case R.id.btnLogin:
               if(isValidDataToLogin()){
                   firbaseTest();
               }
                break;

            case R.id.txtRegister:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;

            case R.id.txtForgotPassword:
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;

            default:
                break;
        }
    }

    private void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void requestToLoginUser() {
        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), binding.etMobileNumber.getText().toString().trim());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), binding.etPassword.getText().toString().trim());
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), FirebaseInstanceId.getInstance().getToken() == null ? "Empty" : FirebaseInstanceId.getInstance().getToken());
        RequestBody deviceToken = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody deviceType = RequestBody.create(MediaType.parse("text/plain"), "android");
        RequestBody code = RequestBody.create(MediaType.parse("text/plain"), "+91");


        showProgress(getString(R.string.requesting), false);

        Call<ResponseBody> arrayListCall = RetrofitBuilder.getInstance().getRetrofit(mContext)
                .userLogin(username,
                        password,
                        deviceId,
                        deviceToken,
                        deviceType,
                        code
                );
        arrayListCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissProgress();
                try {
                    JSONObject jsonObject = new JSONObject(GH.RetrofitBufferReaderResponse(response));
                    if (jsonObject.has("status")) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            JSONArray jsonAddress = jsonObject.getJSONArray("result_address");
                            setUserDetail(jsonArray, jsonAddress);
                            Intent intent = new Intent(mContext, Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            DialogUtil.showMessageDialog(mContext, jsonObject.getString("msg"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissProgress();
                DialogUtil.showMessageDialog(mContext, getString(R.string.str_something_went_wrong_please_try_again));
            }
        });
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            binding.btnGoogle.setVisibility(View.GONE);

        } else {
            binding.btnGoogle.setVisibility(View.VISIBLE);

        }
    }

    private void setUserDetail(JSONArray loginresult, JSONArray result_address) {
        JSONObject json;
        JSONObject json1;
        if (result_address.length() > 0) {
            try {
                json = result_address.getJSONObject(0);
                SP.savePreferences(mContext, SP.USER_DELIVERY_ADDRESS_ID, json.getString("user_address_id"));
                SP.savePreferences(mContext, SP.USER_DELIVERY_ADDRESS, getAddress(json));
                SP.savePreferences(mContext, SP.USER_BILLING_ADDRESS_ID, json.getString("user_address_id"));
                SP.savePreferences(mContext, SP.USER_BILLING_ADDRESS, getAddress(json));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if (loginresult.length() > 0) {
            try {
                json1 = loginresult.getJSONObject(0);

                SP.savePreferences(mContext, SP.LOGIN_STATUS, SP.SP_TRUE);
                SP.savePreferences(mContext, SP.USER_FIRST_NAME, json1.getString("firstname"));
                // SP.savePreferences(mContext, SP.USER_LAST_NAME, json1.getString("lastname"));
                SP.savePreferences(mContext, SP.USER_MOBILE, json1.getString("mobilenumber"));
                SP.savePreferences(mContext, SP.USER_EMAIL, json1.getString("email"));
                SP.savePreferences(mContext, SP.USER_ID, json1.getString("user_id"));
                SP.savePreferences(mContext, SP.COUNTRY_CODE, json1.getString("country_code"));
                SP.savePreferences(mContext, SP.CALL_US, json1.getString("call_us"));
                SP.savePreferences(mContext, SP.CART_TOTAL, json1.getString("cart_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private String getAddress(JSONObject model) {
        String address = "";
        try {
            address = model.getString("first_name") + model.getString("street") + "," +
                    "" + model.getString("landmark") + ",\n" +
                    "" + model.getString("city") + "," +
                    "" + model.getString("zipcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return address;
    }

    private boolean isValidDataToLogin() {
        if (!GH.isValidMobileNumber(binding.etMobileNumber.getText().toString().trim())) {
            GH.setEditTextError(getString(R.string.enter_valid_mobile_number), binding.tilMobileNumber);
            return false;
        }  else {
            return true;
        }
    }

    @Override
    public void onResumeCalled() {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}