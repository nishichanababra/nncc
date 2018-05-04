package app.citta.utilities.GeneralizedModules.FingerPrint.Activity;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import app.citta.utilities.R;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {
    private TextView tv;
    public FingerPrintHandler(TextView tv) {
        this.tv = tv;
    }
    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        tv.setText(R.string.autherror);
        tv.setTextColor(tv.getContext().getResources().getColor(android.R.color.holo_red_dark));
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        tv.setText(R.string.finger_auth_success);
        tv.setTextColor(tv.getContext().getResources().getColor(android.R.color.holo_green_light));
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        tv.setText(R.string.auth_failed);
        tv.setTextColor(tv.getContext().getResources().getColor(android.R.color.holo_red_dark));
    }

    public void doAuth(FingerprintManager manager,
                       FingerprintManager.CryptoObject obj) {
        CancellationSignal signal = new CancellationSignal();
        try {
            manager.authenticate(obj, signal, 0, this, null);
        }
        catch(SecurityException sce) {}
    }
}