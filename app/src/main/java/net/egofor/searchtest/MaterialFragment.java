package net.egofor.searchtest;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import net.egofor.searchtest.data.BushingColumns;
import net.egofor.searchtest.data.MaterialProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

import cz.msebera.android.httpclient.Header;

public class MaterialFragment extends Fragment {

    private static final String LOG_TAG = MaterialFragment.class.getSimpleName();

    private MaterialInterface materialInterface;
    GridView gridview;
    ResponseBushings responseObj;
    MaterialGridViewAdapter adapter;
    Gson gson;
    AsyncHttpClient client;
    Cursor mCursor;
    Uri mNewUri;
    Context mContext;


    public interface MaterialInterface {
        //  This is where we are declaring the name of the method to be used in the interface
        void showMaterialDetail(Intent intent);

    }

    public MaterialFragment(Context context) {

        mContext = context;

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        updateMovies();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        gridview = (GridView) rootView.findViewById(R.id.gridview_material);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //  Setting item variable to position within inner class ResultsEntity in ResponseBushings class
                //  This puts all the data at each position in one variable  to package and send and Intent
                ResponseBushings.ResultsEntity item = (ResponseBushings.ResultsEntity) adapter.getItem(position);

                String thumbnail = item.get_thumbnail();
                String description = item.get_short_description();
                String upc = item.get_upc();
                String mfg = item.get_mfg();
                String mfgPart = item.get_mfg_part_number();
                String tradeSize = item.get_trade_size();
                String application = item.get_application();
                String material = item.get_material();
                String insulated = item.get_insulated();

                Intent i = new Intent(getActivity(), MaterialDetailActivity.class);
                i.putExtra("thumbnail", String.valueOf(thumbnail));
                i.putExtra("description", String.valueOf(description));
                i.putExtra("upc", String.valueOf(upc));
                i.putExtra("mfg", String.valueOf(mfg));
                i.putExtra("mfg_part", String.valueOf(mfgPart));
                i.putExtra("trade_size", String.valueOf(tradeSize));
                i.putExtra("application", String.valueOf(application));
                i.putExtra("material", String.valueOf(material));
                i.putExtra("insulated", String.valueOf(insulated));

                //  Using an interface to determine what layout is present within MainActivity
                materialInterface.showMaterialDetail(i);

                String toast = "Thumbnail: " + thumbnail + "\n" +
                        "Description: " + description + "\n" +
                        "UPC: " + upc + "\n" +
                        "Mfg: " + mfg + "\n" +
                        "Mfg Part#: " + mfgPart + "\n" +
                        "Trade Size: " + tradeSize + "\n" +
                        "Application: " + application + "\n" +
                        "Material: " + material + "\n" +
                        "Insulated: " + insulated + "\n";


            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().getIntent().getExtras() == null) return;
        updateMovies();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void updateMovies() {
        parseJson();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //  If context has a MaterialInterface
        if (context instanceof MaterialInterface) {
            //  Set member variable to MaterialInterface of the context
            materialInterface = (MaterialInterface) context;
            //  This .showMaterialDetail is defined in MainActivity

        } else {
            throw new Error("Needs to implement MovieInterface");
        }
    }


    public String builtURL() {

        String MATERIAL_BASE_URL = "https://api.myjson.com/bins/3nqyk";

        return MATERIAL_BASE_URL;
    }


    private String[] getBushingsDataFromJson(String responseStr) {

        final String RESULTS = "results";
        final String IMAGE = "_thumbnail";
        final String BUSHING = "bushing";
        final String DESCRIPTION = "_short_description";
        final String UPC = "_upc";
        final String MFG = "_mfg";
        final String MFG_PART = "_mfg_part_number";
        final String TRADE_SIZE = "_trade_size";
        final String MATERIAL = "_material";
        final String INSULATED = "_insulated";


        try {
            JSONObject bushingForecastJson = new JSONObject(responseStr);


            //  TODO get error code and check for network connectivity here
            //  TODO will need to modify the json for final implementation for this

            JSONArray bushingArray = bushingForecastJson.getJSONArray(RESULTS);

            Vector<ContentValues> cVVector = new Vector<ContentValues>(bushingArray.length());

            for (int i = 0; i < bushingArray.length(); i++) {

                String imageUrl;
                String description;
                String upc;
                String mfg;
                String mfgPart;
                String tradeSize;
                String material;
                String insulated;

                JSONObject bushingObject = bushingArray.getJSONObject(i);

                imageUrl = bushingObject.getString(IMAGE);
                description = bushingObject.getString(DESCRIPTION);
                upc = bushingObject.getString(UPC);
                mfg = bushingObject.getString(MFG);
                mfgPart = bushingObject.getString(MFG_PART);
                tradeSize = bushingObject.getString(TRADE_SIZE);
                material = bushingObject.getString(MATERIAL);
                insulated = bushingObject.getString(INSULATED);

                ContentValues bushingValues = new ContentValues();

                bushingValues.put(BushingColumns.IMAGE, imageUrl);
                bushingValues.put(BushingColumns.SHORT_DESCRIPTION, description);
                bushingValues.put(BushingColumns.UPC, upc);
                bushingValues.put(BushingColumns.MFG, mfg);
                bushingValues.put(BushingColumns.MFG_PART_NUMBER, mfgPart);
                bushingValues.put(BushingColumns.TRADE_SIZE, tradeSize);
                bushingValues.put(BushingColumns.MATERIAL, material);
                bushingValues.put(BushingColumns.INSULATED, insulated);

                cVVector.add(bushingValues);

            }

            if (cVVector.size() > 0) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);

                mContext.getContentResolver().delete(MaterialProvider.Bushings.CONTENT_URI, null, null);

                mContext.getContentResolver().bulkInsert(MaterialProvider.Bushings.CONTENT_URI, cvArray);
            }


            Cursor cur = mContext.getContentResolver().query(MaterialProvider.Bushings.CONTENT_URI,
                    null, null, null, null);

            cVVector = new Vector<ContentValues>(cur.getCount());
            if (cur.moveToFirst()) {
                do {
                    ContentValues cv = new ContentValues();
                    cVVector.add(cv);
                } while (cur.moveToNext());
            }

            Log.d(LOG_TAG, "Fetch Material Task Complete. " +
                    cVVector.size() + " Inserted");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void parseJson() {

        client = new AsyncHttpClient();
        client.get(getActivity(), builtURL(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                //  This responseStr is the actual JSON string from the server
                String responseStr = new String(responseBody);

                gson = new Gson();
                responseObj = gson.fromJson(responseStr, ResponseBushings.class);
                adapter = new MaterialGridViewAdapter(getActivity(), responseObj.getResults());
                gridview.setAdapter(adapter);

                getBushingsDataFromJson(responseStr);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


}
