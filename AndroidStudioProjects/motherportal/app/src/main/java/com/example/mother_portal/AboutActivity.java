package com.example.mother_portal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView menuicon =findViewById(R.id.menuIcon);


        menuicon.setOnClickListener(v ->{
            PopupMenu popup = new PopupMenu(AboutActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.header_menu,popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id =item.getItemId();
                if(id == R.id.menu_join){
                    startActivity(new Intent(AboutActivity.this, JoinCommunity.class));
                    return true;
                }
                if(id == R.id.menu_community){
                    startActivity(new Intent(AboutActivity.this, CommunityActivity.class));
                    return true;
                }
                if(id == R.id.menu_modules){
                    startActivity(new Intent(AboutActivity.this, ModulesActivity.class));
                    return true;
                }
                if(id == R.id.menu_home){
                    startActivity(new Intent(AboutActivity.this, MainActivity.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }
}
