package net.egofor.searchtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MaterialGridViewAdapter extends BaseAdapter {

    private List<ResponseBushings.ResultsEntity> mMaterialitem;
    private Context mContext;

    //  These bind the views to the member variables
    @Bind(R.id.material_image)
    ImageView mImageView;

    @Bind(R.id.mfg)
    TextView mManufacturer;

    @Bind(R.id.mfg_part)
    TextView mPartNumber;


    public MaterialGridViewAdapter(Context mContext, List<ResponseBushings.ResultsEntity> mMaterialitem) {
        this.mContext = mContext;
        this.mMaterialitem = mMaterialitem;
    }

    @Override
    public int getCount() {
        return mMaterialitem.size();
    }

    @Override
    public Object getItem(int position) {
        return mMaterialitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.material_item, parent, false);
        ResponseBushings.ResultsEntity item = (ResponseBushings.ResultsEntity) getItem(position);

        //  Initializing Butterknife
        ButterKnife.bind(this, rowView);

        //  Using Butterknife to inject the views
        mManufacturer.setText(item.get_mfg());
        mPartNumber.setText(item.get_mfg_part_number());
        final String url = item.get_thumbnail();
        Picasso
                .with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .fit()
                .into(mImageView);


        return rowView;
    }


}
