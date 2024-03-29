package com.example.kochbuch.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kochbuch.Model.Recipe;
import com.example.kochbuch.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    private List<Recipe> recipeList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflater = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_layout, viewGroup, false);
        return new RecipeHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder recipeHolder, int i) {
        Recipe recipe = recipeList.get(i);
        recipeHolder.textViewTitle.setText(recipe.getTitle());

        byte[] imageByte = recipe.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        recipeHolder.imageView.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipe(List<Recipe> recipe) {
        this.recipeList = recipe;
        notifyDataSetChanged();
    }


    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView imageView;

        public RecipeHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(recipeList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

}
