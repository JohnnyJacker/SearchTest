package net.egofor.searchtest;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.egofor.searchtest.R;
import net.egofor.searchtest.data.BushingColumns;
import net.egofor.searchtest.data.MaterialProvider;

import org.w3c.dom.Text;

import butterknife.Bind;


public class MaterialAdapter extends CursorAdapter{
    public MaterialAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    private String CursorToUX(Cursor cursor) {
        // get row indices for our cursor
        int idx_image = cursor.getColumnIndex(BushingColumns.IMAGE);
        int idx_mfg = cursor.getColumnIndex(BushingColumns.MFG);
        int idx_part_number = cursor.getColumnIndex(BushingColumns.MFG_PART_NUMBER);

        String image = cursor.getString(idx_image);
        String mfg = cursor.getString(idx_mfg);
        String part_number = cursor.getString(idx_part_number);

        return image + mfg + part_number;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.material_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //  TODO  fill in the views with the contents of the cursor

        TextView tv = (TextView)view;
        tv.setText(CursorToUX(cursor));

    }
}
