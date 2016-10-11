package tekmob.letsnote.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

import tekmob.letsnote.R;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.models.TimelineModel;
import tekmob.letsnote.network.VolleySingleton;

/**
 * Created by dananarief on 08-10-16.
 */
public class TimelineRVAdapter extends RecyclerView.Adapter<TimelineRVAdapter.NoteViewHolder>{
    List<TimelineModel> notes;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView personName;
        ImageView personPhoto;

        NoteViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.title);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }


    public TimelineRVAdapter(List<TimelineModel> notes){
        this.notes = notes;
        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cardview_timeline, viewGroup, false);
        NoteViewHolder pvh = new NoteViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder noteViewHolder, int i) {
        noteViewHolder.title.setText(notes.get(i).getTitle());
        noteViewHolder.personName.setText(notes.get(i).getOwner());

        String url=notes.get(i).getPhotoLink();
        //String url="http://mahasiswa.cs.ui.ac.id/~danan.arief/icon texteditor.png";

        if(url!=null){
            imageLoader.get(url, new ImageLoader.ImageListener() {

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    Log.d("masuk on response","a");
                    noteViewHolder.personPhoto.setImageBitmap(response.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("masuk error response","a");
                    //Log.d("eror gambar",error.getMessage());
                    noteViewHolder.personPhoto.setImageResource(R.mipmap.ic_launcher);
                }


            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
