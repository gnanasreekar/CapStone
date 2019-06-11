package com.rgs.capstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Details extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.collapsing_tool_bar)
    CollapsingToolbarLayout collapsingToolBar;
    @BindView(R.id.id_appbarLayout)
    AppBarLayout idAppbarLayout;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.content)
    TextView content;

    String authors;
    String titile;
    String description;
    String date;
    String contents;
    String url;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.date)
    TextView datet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        authors = intent.getStringExtra("author");
        contents = intent.getStringExtra("content");
        titile = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        url = intent.getStringExtra("url");
        description = intent.getStringExtra("desc");
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).fit().centerInside().into(backImage);
        collapsingToolBar.setTitle(intent.getStringExtra("author"));
        author.setText(authors);
        desc.setText(description);
        content.setText(contents);
        datet.setText("Published date :" + date);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
    }
}
