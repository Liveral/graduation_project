package com.example.test_application3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;


import com.example.test_application3.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab, fab1, fab2;
    Animation fabOpen, fabClose;

    ImageView imageview;
    File file;
    Uri uri;
    int value=0;
    private View view;
    Button bntCamera;
    boolean isOpen =false; // 디폴트값은 false
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;
    private Frag2 frag2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView =findViewById(R.id.bottom_navi);
        bottomNavigationView.setBackground(null);
        fab=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab1=(FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab2=(FloatingActionButton) findViewById(R.id.floatingActionButton3);
        imageview = findViewById(R.id.imageView3);



        //FAB 버튼 클릭 애니메이션
        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isOpen){
                    fab2.startAnimation(fabClose);
                    fab1.startAnimation(fabClose);

                    fab1.setClickable(false);
                    fab2.setClickable(false);
                    isOpen=false;
                }
                else{
                    fab2.startAnimation(fabOpen);
                    fab1.startAnimation(fabOpen);

                    fab1.setClickable(true);
                    fab2.setClickable(true);
                    isOpen=true;
                }
            }
                               });


        fab1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{
                    file = createFile();
                    if(file.exists()){
                        file.delete();
                    }

                    file.createNewFile();
                } catch(Exception e){
                    e.printStackTrace();
                }

                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 101);
                /*Intent myintent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(myintent);*/


            }

        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        setfrag(0);
                        break;

                    case R.id.star:
                        setfrag(1);
                        break;

                }
                return true;
            }
        });
        frag1=new Frag1();
        frag2=new Frag2();
        setfrag(0);




    }




    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageview.setImageBitmap(imageBitmap);
        }
    }*/

    public void takePicture(){
        try{
            file = createFile();
            if(file.exists()){
                file.delete();
            }

            file.createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, file);
        } else{
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(intent, 101);
    }

    private File createFile(){
        String filename = "capture.jpg";
        File outFile = new File(getFilesDir(), filename);
        Log.d("Main", "File path : " + outFile.getAbsolutePath());
        return outFile;
    }

    private void setfrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame,frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,frag2);
                ft.commit();
                break;

        }
    }

}