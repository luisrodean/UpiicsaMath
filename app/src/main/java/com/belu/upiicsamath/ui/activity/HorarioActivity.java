package com.belu.upiicsamath.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by Betza Ojeda on 29/10/2015.
 */
public class HorarioActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTxtTitleToolbar().setText(getResources().getString(R.string.title_catalog_main));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_catalog, CatalogFragment.newInstance()).commit();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_catalog;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();
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
