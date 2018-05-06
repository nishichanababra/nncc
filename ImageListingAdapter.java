package com.css.opddoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.css.opddoctor.R;
import com.css.opddoctor.model.ImageListingModel;

import java.util.ArrayList;

/**
 * Created by jyoti on 08/01/2018.
 */

public class ImageListingAdapter extends RecyclerView.Adapter<ImageListingAdapter.RecyclerViewHolder> {
  private final String TAG = "DataAdapter";
  private Context context;

  private ArrayList<ImageListingModel> arrayList = new ArrayList<ImageListingModel>();


  public ImageListingAdapter(Context context, ArrayList<ImageListingModel> arrayList) {
    this.arrayList = arrayList;
    this.context = context;

  }

  @Override
  public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_add_diesease_image, parent, false);
    RecyclerViewHolder recyclerviewholder = new RecyclerViewHolder(view);
    return recyclerviewholder;
  }

  @Override
  public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
    final ImageListingModel imageListingModel = arrayList.get(position);
    // holder.imgRecyclerView.setImageResource(Integer.parseInt(imageListingModel.getImgAddDiease()));

    byte[] decodedBytes = Base64.decode(imageListingModel.getPath(), Base64.DEFAULT);
    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    holder.imgAddDiease.setImageBitmap(bitmap);

    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        arrayList.remove(position);
        notifyDataSetChanged();
      }
    });

  }//end of onBind method

  @Override
  public int getItemCount() {
    return arrayList.size();
  }


  public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = "RecyclerViewHolder";
    private ImageView imgAddDiease;
    private Button btnDelete;


    public RecyclerViewHolder(View itemView) {
      super(itemView);

      imgAddDiease = (ImageView) itemView.findViewById(R.id.imgAddDiease);
      btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

    }//end of  RecyclerViewHolder method

  }//end of RecyclerViewHolder class


}//end of class

