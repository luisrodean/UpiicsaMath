package com.belu.upiicsamath.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.belu.upiicsamath.R;

/**
 * Created by Luis on 19/11/2015.
 */
public class Validar {
    private View focusView;

    public View getFocusView(){
        return focusView;
    }

    public boolean CampoVacio(EditText campo, String msgError){
        if(TextUtils.isEmpty(campo.getText().toString())){
            campo.setError(msgError);
            focusView = campo;
            return true;
        }else{
            focusView = null;
            return false;
        }
    }

    public boolean isOnline(Context context, View vista) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean enabled = true;
        String mensaje = "Conectando";

        if ((netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable())){
            enabled = false;
            mensaje = "Sin conexion a internet";
        }
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_SHORT).show();

        return enabled;
    }

    public void  StringVacio(String texto){

    }

}
