package com.example.sviatoslav.newsapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Sviatoslav on 12/19/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private NewsSuite newsSuite;

    public NewsAdapter(NewsSuite newsSuite){
        this.newsSuite = newsSuite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Article article = newsSuite.getArticle(position);
        holder.subject.setText(article.getTitle());
        String shortDescription = article.getDescription();
        if (shortDescription.length() > 60){
            shortDescription = shortDescription.substring(0,60) + "...";
        }
        holder.description.setText(shortDescription);
        String imageUrl = article.getUrlToImage();
        String smallImage = (imageUrl.split("\\?"))[0].concat("?w=800");
        Picasso.with(holder.image.getContext())
                .load(smallImage)
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(holder.image);
        //OnViewClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("subject", article.getTitle());
                intent.putExtra("author", article.getAuthor());
                intent.putExtra("source", newsSuite.getSource());
                intent.putExtra("date", article.getPublishedAt());
                intent.putExtra("description", article.getDescription());
                intent.putExtra("image_url", article.getUrlToImage());
                intent.putExtra("url", article.getUrl().toString());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsSuite.articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subject;
        private TextView description;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.subject);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


}
