package net.egofor.searchtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MaterialDetailFragment extends Fragment {

    private static final String LOG_TAG = MaterialDetailFragment.class.getSimpleName();
    Bundle extras = null;


    public MaterialDetailFragment() {
        setHasOptionsMenu(true);
    }

    public void putArgs(Bundle bundle) {
        this.extras = bundle;
    }

    public static MaterialDetailFragment newInstance(Intent intent) {
        MaterialDetailFragment DetailFragment = new MaterialDetailFragment();
        DetailFragment.putArgs(intent.getExtras());
        return DetailFragment;
    }

    //  These bind the views to the member variables
    //  These is utilizing the Butterknife library to directly hook the views together
    @Bind(R.id.imageView)
    ImageView mImageView;

    @Bind(R.id.description)
    TextView mDescription;

    @Bind(R.id.upc)
    TextView mUPC;

    @Bind(R.id.mfg)
    TextView mMfg;

    @Bind(R.id.mfg_part)
    TextView mMfgPart;

    @Bind(R.id.trade_size)
    TextView mTradeSize;

    @Bind(R.id.application)
    TextView mApplication;

    @Bind(R.id.material)
    TextView mMaterial;

    @Bind(R.id.insulated)
    TextView mInsulated;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        //  Initialize Butterknife
        ButterKnife.bind(this, rootView);

        if (extras == null) {
            extras = getActivity().getIntent().getExtras();
        }

        //  These string variables are being set to the string passed in from MaterialFragment Intent
        final String thumbnail = extras.getString("thumbnail");
        final String description = extras.getString("description");
        final String upc = extras.getString("upc");
        final String mfg = extras.getString("mfg");
        final String mfg_part = extras.getString("mfg_part");
        final String trade_size = extras.getString("trade_size");
        final String application = extras.getString("application");
        final String material = extras.getString("material");
        final String insulated = extras.getString("insulated");

        //  This is the result of setting a text to a view by using Butterknife
        mDescription.setText(description);
        mUPC.setText(upc);
        mMfg.setText(mfg);
        mMfgPart.setText(mfg_part);
        mTradeSize.setText(trade_size);
        mApplication.setText(application);
        mMaterial.setText(material);
        mInsulated.setText(insulated);
        Picasso.with(getContext())
                .load(thumbnail)
                .resize(185, 185)
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(mImageView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


    }

    @Override
    public void onStart() {
        super.onStart();
    }


}
