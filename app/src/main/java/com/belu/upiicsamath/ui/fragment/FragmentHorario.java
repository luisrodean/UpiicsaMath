package com.belu.upiicsamath.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.belu.upiicsamath.R;

public class FragmentHorario extends Fragment {

    public FragmentHorario() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().setText(getResources().getString(R.layout.fragment_horario));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_catalog, CatalogFragment.newInstance()).commit();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_principal;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super
                overridePendingTransition(R.animator.open_main, R.animator.close_next);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.open_main, R.animator.close_next);
    }

}
