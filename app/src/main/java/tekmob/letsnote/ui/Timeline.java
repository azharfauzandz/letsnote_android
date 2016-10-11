package tekmob.letsnote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tekmob.letsnote.R;
import tekmob.letsnote.adapter.TimelineRVAdapter;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.models.TimelineModel;
import tekmob.letsnote.network.VolleySingleton;

public class Timeline extends AppCompatActivity {

    public static final String JSON_URL = "http://ec2-52-207-209-250.compute-1.amazonaws.com/letsnote/getTimeline.php";
    private List<TimelineModel> notes;
    private RecyclerView rv;
    private String jsonResponse;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //NotesModel fisika = new NotesModel(1,1,"catatan fisika","Catatan ini adalah kisi kisi ujian",10000,"www.getphoto.com");

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        Log.d("timelinegui","timelinegui");
        sendRequestv();

    }

    private void sendRequestv(){
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


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Timeline.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void showJSON(JSONObject json){

    }

    private void initializeAdapter(){
        TimelineRVAdapter adapter = new TimelineRVAdapter(notes);
        rv.setAdapter(adapter);
    }
}
