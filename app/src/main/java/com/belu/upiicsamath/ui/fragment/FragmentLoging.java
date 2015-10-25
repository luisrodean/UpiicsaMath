package com.belu.upiicsamath.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import com.belu.upiicsamath.R;
import com.belu.upiicsamath.ui.activity.Principal;

public class FragmentLoging extends Fragment {

    // UI references.
    private EditText txtuser;
    private EditText txtpass;
    private Button btnAcceder;
    private View mProgressView;
    private View mLoginFormView;

    // Progress Dialog
    private ProgressDialog pDialog;

  //  private JSONParser jsonParser;
    EditText inputName;
    EditText inputPrice;
    EditText inputDesc;

    // url to create new product
    private static String url_create_product = "http://localhost:8080/wsmathupiicsa/bd/add_user.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public FragmentLoging() {
//        jsonParser = new JSONParser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_loging,container,false);

        txtuser = (EditText) vista.findViewById(R.id.txt_login_usuario);
        txtpass = (EditText) vista.findViewById(R.id.txt_login_password);

        btnAcceder = (Button) vista.findViewById(R.id.btn_loging_acceder);

        mLoginFormView = vista.findViewById(R.id.login_form);
        mProgressView = vista.findViewById(R.id.login_progress);

        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

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
            Intent intent = new Intent(getActivity().getApplicationContext(),Principal.class);
            startActivity(intent);
            getActivity().finish();
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
