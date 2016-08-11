package com.ptg.inventory.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptg.inventory.activity.MainActivity;
import com.ptg.inventory.activity.R;


public class InwordFragment extends Fragment {

    EditText et_products;
    Button btn_submit;
    TextView tv_result;
    LinearLayout ll_products;
    Button btn_scan;
    TextView tvStatus;
	public InwordFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_inward, container, false);
        et_products=(EditText)rootView.findViewById(R.id.et_totalProducts);
        btn_submit=(Button)rootView.findViewById(R.id.btn_submit);
        ll_products=(LinearLayout)rootView.findViewById(R.id.ll_products);
        tv_result=(TextView)rootView.findViewById(R.id.tvresult);
        btn_scan=(Button)rootView.findViewById(R.id.butProd);
         tvStatus=(TextView)rootView.findViewById(R.id.tvStatus);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                startActivityForResult(intent, 0);
            }
        });

       if(tv_result.getText().toString().equalsIgnoreCase("Product Details:")){
            ll_products.setVisibility(View.INVISIBLE);
            btn_submit.setVisibility(View.INVISIBLE);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_result.getText().toString().equalsIgnoreCase("Ready")){
                    ll_products.setVisibility(View.INVISIBLE);
                    btn_submit.setVisibility(View.INVISIBLE);
                }else{
                    ll_products.setVisibility(View.VISIBLE);
                    btn_submit.setVisibility(View.VISIBLE);
                }
            }
        });
         
        return rootView;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {

            if (resultCode == MainActivity._CONTEXT.RESULT_OK) {
                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tv_result.setText(intent.getStringExtra("SCAN_RESULT"));
                btn_submit.performClick();
            } else if (resultCode == MainActivity._CONTEXT.RESULT_CANCELED) {
                tvStatus.setText("Press a button to start a scan.");
                tv_result.setText("Scan cancelled.");
            }
        }
    }

}
