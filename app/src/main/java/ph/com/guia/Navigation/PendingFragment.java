package ph.com.guia.Navigation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ph.com.guia.Guide.LoggedInGuide;
import ph.com.guia.Helper.JSONParser;
import ph.com.guia.Helper.RVadapter;
import ph.com.guia.Model.Constants;
import ph.com.guia.Model.PendingRequest;
import ph.com.guia.R;

public class PendingFragment extends Fragment {

    public static ArrayList<PendingRequest> mList = new ArrayList<PendingRequest>();
    public static RecyclerView rv;
    public static RVadapter adapter;
    public static ProgressDialog pd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pd = ProgressDialog.show(this.getContext(), "Loading", "Please wait...", true, false);

        mList.clear();
        if(!LoggedInGuide.guide_id.equals("")) {
            JSONObject request = new JSONObject();
            try {
                request.accumulate("booking_guide_id", LoggedInGuide.guide_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser(getActivity());
            parser.getBookingsByGuideId(request, Constants.getBookingsByGuideId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        rv = (RecyclerView) view.findViewById(R.id.cardList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

//        adapter = new RVadapter(getActivity().getApplicationContext(), null, null, null, mList);
//        rv.setAdapter(adapter);
        return view;
    }
}
