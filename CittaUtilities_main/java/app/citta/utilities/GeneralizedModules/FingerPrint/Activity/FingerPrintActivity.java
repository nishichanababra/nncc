package app.citta.utilities.GeneralizedModules.FingerPrint.Activity;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import app.citta.utilities.R;

public class FingerPrintActivity extends AppCompatActivity {

    FingerprintManager fingerprintManager;
    TextView message;
    KeyStore keyStore;
    KeyGenerator keyGenerator;
    public static String KEY_NAME = "Citta Utilities";
    FingerprintManager.CryptoObject cryptoObject;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fingerprint_demo);
        message = (TextView) findViewById(R.id.message);
        inittoolbar();

        Button btn = (Button) findViewById(R.id.authBtn);
        FingerPrintHandler fph = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            fph = new FingerPrintHandler(message);
        }
        if (!checkFinger()) {
            btn.setEnabled(false);
        }
        else {
            // We are ready to set up the cipher and the key
            try {
                generateKey();
                Cipher cipher = generateCipher();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);
                }
            }
            catch(Exception fpe) {
                // Handle exception
                btn.setEnabled(false);
            }
        }
        final FingerPrintHandler finalFph = fph;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message.setText(R.string.swipe_finger);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    finalFph.doAuth(fingerprintManager, cryptoObject);
                }
            }
        });

    }

    void inittoolbar() {
        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.back);
        mTitle.setText(getResources().getString(R.string.finger_print));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean checkFinger() {
        // Keyguard Manager
        KeyguardManager keyguardManager = (KeyguardManager)
                getSystemService(KEYGUARD_SERVICE);
        // Fingerprint Manager
        fingerprintManager = (FingerprintManager)
                getSystemService(FINGERPRINT_SERVICE);
        try {
            // Check if the fingerprint sensor is present
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!fingerprintManager.isHardwareDetected()) {
                    // Update the UI with a message
                    message.setText(R.string.auth_notsupportedfinger);
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!fingerprintManager.hasEnrolledFingerprints()) {
                    message.setText(R.string.not_configured_fingure);
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if (!keyguardManager.isKeyguardSecure()) {
                    message.setText(R.string.not_lockscreen_enabled);
                    return false;
                }
            }
        }
        catch(SecurityException se) {
            se.printStackTrace();
        }
        return true;
    }

    private void generateKey() throws Exception {
        try {
            // Get the reference to the key store
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            // Key generator to generate the key
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               keyGenerator.init( new
                       KeyGenParameterSpec.Builder(KEY_NAME,
                       KeyProperties.PURPOSE_ENCRYPT |
                               KeyProperties.PURPOSE_DECRYPT)
                       .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                       .setUserAuthenticationRequired(true)
                       .setEncryptionPaddings(
                               KeyProperties.ENCRYPTION_PADDING_PKCS7)
                       .build());
            }
            keyGenerator.generateKey();
        }
        catch(KeyStoreException
                | NoSuchAlgorithmException
                | NoSuchProviderException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        }
    }

    private Cipher generateCipher() throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher;
        }
        catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | UnrecoverableKeyException
                | KeyStoreException exc) {
            exc.printStackTrace();
            throw new Exception(exc);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

