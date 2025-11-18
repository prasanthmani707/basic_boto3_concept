package com.example.mother_portal;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView menuicon =findViewById(R.id.menuIcon);
        menuicon.setOnClickListener(v ->{

            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.header_menu,popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id =item.getItemId();
                if (id == R.id.menu_about) {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class));
                    return true;
                }
                if(id == R.id.menu_join){
                    startActivity(new Intent(MainActivity.this, JoinCommunity.class));
                    return true;
                }
                if(id == R.id.menu_community){
                    startActivity(new Intent(MainActivity.this, CommunityActivity.class));
                    return true;
                }
                if(id == R.id.menu_modules){
                    startActivity(new Intent(MainActivity.this, ModulesActivity.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });



    }
}