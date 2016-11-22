package com.amador.recyclerManageProduct.presenter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;

import com.amador.recyclerManageProduct.R;

/**
 * Created by usuario on 2/11/16.
 */

public class AcountSettingActivity extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_general);

    }
}
