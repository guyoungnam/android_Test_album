package org.techtown.album;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    public void openGallery() {
        Intent intent = new Intent(); //인텐트 객체
        intent.setType("image/*"); // MME 타입이 image로 시작하는 데이터를 가져오라는 의미
        intent.setAction(Intent.ACTION_GET_CONTENT); //액션 정보 설정

        startActivityForResult(intent, 101);
    }

        @Override
        protected  void onActivityResult(int requestCode, int resultCode, Intent data){
            if(requestCode == 101){
                if(resultCode == RESULT_OK){
                    Uri fileUri = data.getData();

                    ContentResolver resolver = getContentResolver();

                    try {
                        InputStream instream = resolver.openInputStream(fileUri);
                        Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                        imageView.setImageBitmap(imgBitmap);
                        instream.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

