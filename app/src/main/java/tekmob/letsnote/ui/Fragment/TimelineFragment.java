package tekmob.letsnote.ui.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tekmob.letsnote.R;
import tekmob.letsnote.adapter.TimelineRVAdapter;
import tekmob.letsnote.models.TimelineModel;
import tekmob.letsnote.network.VolleySingleton;
import tekmob.letsnote.ui.NotesDetailActivity;

/**
 * Created by azhardz on 10/11/16.
 */
public class TimelineFragment extends Fragment implements TimelineRVAdapter.ClassListener {
    public static final String JSON_URL = "http://ec2-52-207-209-250.compute-1.amazonaws.com/letsnote/getTimeline.php";
    private List<TimelineModel> notes;
    private RecyclerView rv;
    private String jsonResponse;
    private ImageLoader imageLoader;
    private ProgressDialog progressDialog;
    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_timeline, container, false);

        rv=(RecyclerView)v.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        Log.d("timelinegui","timelinegui");

        sendRequestv();

        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void sendRequestv(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        RequestQueue requestQueue = VolleySingleton.getsInstance().getRequestQueue();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,JSON_URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //showJSON(response);
                        try{
                            JSONArray jsonArray = response.getJSONArray("server_response");
                            notes = new ArrayList<>();
                            Log.d("panjang array",""+jsonArray.length());
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject note = (JSONObject) jsonArray.get(i);
                                int id_notes=note.getInt("id_notes");
                                int id_users=note.getInt("id_users");
                                String name=note.getString("name");
                                String title=note.getString("title");
                                String description=note.getString("description");
                                int price=note.getInt("price");
                                String photo_link=note.getString("photo_link");


                                Log.d("cetak",id_notes+" "+id_users+" "+name+" "+photo_link+" "+title);

                                notes.add(new TimelineModel(id_notes,id_users,name,title,description,price,photo_link));
                            }
                            initializeAdapter();
                            Log.d("ukuran nots",""+notes.size());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });

        //RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void initializeAdapter(){
        TimelineRVAdapter adapter = new TimelineRVAdapter(notes);
        adapter.setListener(this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), NotesDetailActivity.class);
        intent.putExtra("NOTES_ID", "" + notes.get(pos).getId_notes());
        startActivity(intent);
    }

}
