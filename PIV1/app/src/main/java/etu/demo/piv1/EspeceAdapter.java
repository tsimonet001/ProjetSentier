package etu.demo.piv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EspeceAdapter extends RecyclerView.Adapter<EspeceAdapter.EspeceViewHolder> {
    Context context;
    String[] titres_especes, descriptions_especes;
    int[] images_especes;

    public EspeceAdapter(Context context, String[] titres, String[] descriptions,
                         int[] images) {
//je récupère les données à afficher
        this.context = context;
        this.titres_especes = titres;
        this.descriptions_especes = descriptions;
        this.images_especes = images;
    }
    @NonNull
    @Override
    public EspeceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item_layout, parent, false);
        return new EspeceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EspeceViewHolder holder, int position) {
        holder.tvTitre.setText(titres_especes[position]);
        holder.tvDescription.setText(descriptions_especes[position]);
        holder.imgEspece.setImageResource(images_especes[position]);
    }

    @Override
    public int getItemCount() {
        return titres_especes.length;
    }

    //J'attache les composants du layout xml de l'item à mes composants java
    public class EspeceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitre, tvDescription;
        ImageView imgEspece;

        public EspeceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tv_titre);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgEspece = itemView.findViewById(R.id.img_espece);
        }
    }
}

