package com.belu.upiicsamath.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.ui.activity.Principal;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private EditText txtuser;
    private EditText txtpass;
    private Button btnAcceder;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        txtuser = (EditText) findViewById(R.id.txt_login_usuario);

        txtpass = (EditText) findViewById(R.id.txt_login_password);

        btnAcceder = (Button) findViewById(R.id.btn_loging_acceder);
        btnAcceder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void Login() {
         //Reinicia marca de errores
        txtuser.setError(null);
        txtpass.setError(null);

        // Store values at the time of the login attempt.
        String user = txtuser.getText().toString();
        String pass = txtpass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(pass)) {
            // Valida que pass no este vacio, sino que sea valido
            txtpass.setError(getString(R.string.error_field_required));
            focusView = txtpass;
            cancel = true;
        }

        if (TextUtils.isEmpty(user)) {
            // Valida que usuario no este vacio, sino que sea valido
            txtuser.setError(getString(R.string.error_field_required));
            focusView = txtuser;
            cancel = true;
        }

        if (!isUserValid(user) && !isPasswordValid(pass)) {
            txtuser.setError(getString(R.string.error_invalid_usuario));
            focusView = txtuser;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
           // Snackbar.make(btnAcceder, "Se ha iniciado sesion", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Intent intent = new Intent(this,Principal.class);
            startActivity(intent);
            finish();
//            showProgress(true);
        }

    }

    private boolean isUserValid(String user) {
        if (user.equals("belu")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isPasswordValid(String password) {
        if (password.equals("belu")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

