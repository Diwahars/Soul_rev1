package bluepanther.jiljungjuk.recycler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

import java.io.File;
import java.util.List;

import bluepanther.jiljungjuk.CurrentUser;
import bluepanther.jiljungjuk.Group_tab;
import bluepanther.jiljungjuk.Out_Share;
import bluepanther.jiljungjuk.R;
import bluepanther.jiljungjuk.RowItem;
import bluepanther.jiljungjuk.TimeContent;


public class CustomAdapterShare extends RecyclerView.Adapter<CustomAdapterShare.MyViewHolder> {

 //   private ArrayList<DataModel> dataSet;
    public static Out_Share mainActivity;
    Context context;
    ProgressDialog progressDialog;
    String file1;
    List<RowItem> rowItems;
    public static class MyViewHolder extends RecyclerView.ViewHolder
            {

        TextView title, category, uploader,date;
        CheckBox checkBox;
        ImageView imageViewIcon;
        CardView cardView;
                BoomMenuButton bmb1;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.category = (TextView) itemView.findViewById(R.id.category);
            this.uploader = (TextView) itemView.findViewById(R.id.uploader);
            this.date=(TextView)itemView.findViewById(R.id.date);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView99);
            this.bmb1 = (BoomMenuButton) itemView.findViewById(R.id.bmb1);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            //cardView.setOnLongClickListener(mainActivity);

        }


    }

    public CustomAdapterShare(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout2, parent, false);

        view.setOnClickListener(Out_Share.myOnClickListener);
      //  view.setOnLongClickListener(Group_tab.myOnLongClickListener);


        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView title = holder.title;
        TextView category = holder.category;
        TextView uploader = holder.uploader;
        TextView date=holder.date;
        ImageView imageView = holder.imageViewIcon;
        RowItem row_pos = rowItems.get(listPosition);


        title.setText(row_pos.getMember_name());
        category.setText(row_pos.getStatus());
        uploader.setText(row_pos.getAuthor());
        date.setText(row_pos.getTime());
        imageView.setImageResource(row_pos.getProfile_pic_id());



    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }
}
