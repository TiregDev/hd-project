package tiregdev.hi_depok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tiregdev.hi_depok.R;
import tiregdev.hi_depok.activity.DetailMuseumActivity;
import tiregdev.hi_depok.model.MasterpiecePost;
import tiregdev.hi_depok.utils.AppConfig;

/**
 * Created by TiregDev on 29/08/2017.
 */

public class MasterMuseumAdapter extends RecyclerView.Adapter<MasterMuseumAdapter.holder_museum> {
    private List<MasterpiecePost> itemList;
    private Context context;

    public MasterMuseumAdapter(Context context, List<MasterpiecePost> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MasterMuseumAdapter.holder_museum onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_master_museum,null);
        MasterMuseumAdapter.holder_museum hn = new holder_museum(layoutView);
        return hn;
    }

    @Override
    public void onBindViewHolder(MasterMuseumAdapter.holder_museum holder, int position){
        holder.list_judul.setText(itemList.get(position).getNama_prestasi());
        holder.list_nama.setText(itemList.get(position).getNama_peraih());
        holder.list_tahun.setText(itemList.get(position).getTgl_post().substring(0, 10));
        Glide.with(context).load(AppConfig.IMG_MASTERPIECE + itemList.get(position).getImage()).fitCenter().into(holder.list_picMuseum);
    }

    @Override
    public int getItemCount(){
        return this.itemList.size();
    }

    public class holder_museum extends RecyclerView.ViewHolder {
        public TextView list_judul, list_nama, list_tahun;
        public ImageView list_picMuseum;

        public holder_museum(View itemView){
            super(itemView);

            list_judul = (TextView)itemView.findViewById(R.id.titleMuseum);
            list_nama = (TextView)itemView.findViewById(R.id.nama);
            list_tahun= (TextView)itemView.findViewById(R.id.tahun);
            list_picMuseum = (ImageView)itemView.findViewById(R.id.picMuseum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailMuseumActivity.class);
                    intent.putExtra("NAMA", itemList.get(getAdapterPosition()).getNama_prestasi());
                    intent.putExtra("DESKRIPSI", itemList.get(getAdapterPosition()).getDeskripsi());
                    intent.putExtra("PERAIH", itemList.get(getAdapterPosition()).getNama_peraih());
                    intent.putExtra("TANGGAL", itemList.get(getAdapterPosition()).getTgl_post());
                    intent.putExtra("KATEGORI", itemList.get(getAdapterPosition()).getKategori());
                    intent.putExtra("TINGKAT", itemList.get(getAdapterPosition()).getTingkat());
                    intent.putExtra("IMAGE", itemList.get(getAdapterPosition()).getImage());
                    context.startActivity(intent);
                }
            });
        }
    }
}
