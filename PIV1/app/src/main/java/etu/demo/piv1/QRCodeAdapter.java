package etu.demo.piv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QRCodeAdapter extends RecyclerView.Adapter<QRCodeAdapter.QRViewHolder> {
    Context context;
    String[] titres_qrcodes;
    int[] images_qrcodes;

    public QRCodeAdapter(Context context, String[] titres,
                         int[] images) {
//je récupère les données à afficher
        this.context = context;
        this.titres_qrcodes = titres;
        this.images_qrcodes = images;
    }
    @NonNull
    @Override
    public QRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View itemView = layoutInflater.inflate(R.layout.recycleview_item_layout, parent, false);
        return new QRViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QRViewHolder holder, int position) {
        holder.tvTitre.setText(titres_qrcodes[position]);
        holder.imgQR.setImageResource(images_qrcodes[position]);
    }

    @Override
    public int getItemCount() {
        return titres_qrcodes.length;
    }

    //J'attache les composants du layout xml de l'item à mes composants java
    public class QRViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitre;
        ImageView imgQR;

        public QRViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tv_titre);
            imgQR = itemView.findViewById(R.id.img_qrcode);
        }
    }
}

