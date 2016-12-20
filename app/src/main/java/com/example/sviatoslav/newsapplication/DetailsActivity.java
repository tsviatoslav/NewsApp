package com.example.sviatoslav.newsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private TextView subject;
    private TextView author;
    private TextView source;
    private TextView date;
    private TextView link;
    private ImageView image;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        subject = (TextView) findViewById(R.id.detail_subject);
        author = (TextView) findViewById(R.id.detail_author);
        source = (TextView) findViewById(R.id.detail_source);
        date = (TextView) findViewById(R.id.detail_date);
        link = (TextView) findViewById(R.id.detail_url);
        image = (ImageView) findViewById(R.id.detail_image);
        description = (TextView) findViewById(R.id.detail_description);

        Intent intent = getIntent();

        subject.setText(intent.getStringExtra("subject"));
        author.setText("Author: " + intent.getStringExtra("author"));
        source.setText("Source: " + intent.getStringExtra("source"));
        date.setText("Date: " + intent.getStringExtra("date"));
        description.setText(intent.getStringExtra("description"));
        link.setText("More details: " + intent.getStringExtra("url"));
        Picasso.with(image.getContext())
                .load(intent.getStringExtra("image_url"))
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(image);
    }
}
