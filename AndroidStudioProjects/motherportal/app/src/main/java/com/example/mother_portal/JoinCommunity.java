package com.example.mother_portal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class JoinCommunity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_join);
        ImageView menuicon =findViewById(R.id.menuIcon);


        menuicon.setOnClickListener(v ->{
            PopupMenu popup = new PopupMenu(JoinCommunity.this, v);
            popup.getMenuInflater().inflate(R.menu.header_menu,popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id =item.getItemId();
                if (id == R.id.menu_about) {
                    startActivity(new Intent(JoinCommunity.this, AboutActivity.class));
                    return true;
                }
                if(id == R.id.menu_community){
                    startActivity(new Intent(JoinCommunity.this, CommunityActivity.class));
                    return true;
                }
                if(id == R.id.menu_modules){
                    startActivity(new Intent(JoinCommunity.this, ModulesActivity.class));
                    return true;
                }
                if(id == R.id.menu_home){
                    startActivity(new Intent(JoinCommunity.this, MainActivity.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

}
