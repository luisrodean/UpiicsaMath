package com.belu.upiicsamath.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.belu.upiicsamath.R;

public class FragmentPagina extends Fragment {

    private WebView web;

    public FragmentPagina() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_pag, container, false);
        web = (WebView) vista.findViewById(R.id.webView);
        return vista;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSettings webset = web.getSettings();
        webset.setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient());

        web.loadUrl("http://192.168.0.7:8080/phpws/view/login.php");
    }
}
