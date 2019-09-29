package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.model.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastAdapterVH> {

    Context context;
    List<Cast> castList;

    public CastAdapter(Context context, List<Cast> castList) {
        this.context = context;
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false);
        return new CastAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapterVH holder, int i) {
        String pathUrl = IMAGE_SMALL_PATH + castList.get(i).getProfile_path();
        holder.realNameCast.setText(castList.get(i).getName());
        holder.charNameCast.setText(castList.get(i).getCharacter());
        Picasso.get().load(pathUrl).into(holder.imageCast);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    class CastAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.image_cast)
        ImageView imageCast;
        @BindView(R.id.realName_cast)
        TextView realNameCast;
        @BindView(R.id.characterName_cast)
        TextView charNameCast;

        public CastAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
