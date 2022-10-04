package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private TextView aboutMe, fullName, nim, homePage;
    private Button homePageBtn;
    private CircleImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        aboutMe = findViewById(R.id.label_about);
        fullName = findViewById(R.id.label_fullname);
        nim = findViewById(R.id.label_nim);
        homePage = findViewById(R.id.label_homepage);
        homePageBtn = findViewById(R.id.button_homepage);
        imageProfile = findViewById(R.id.image_profile);

        String about = getIntent().getExtras().getString("aboutMe");
        String name = getIntent().getExtras().getString("fullName");
        String nimMhs = getIntent().getExtras().getString("nim");
        final String home_page = getIntent().getExtras().getString("homePage");

        aboutMe.setText(about);
        fullName.setText(name);
        nim.setText(nimMhs);
        homePage.setText(home_page);

        try {
            Uri ImageUrl = Uri.parse(getIntent().getExtras().getString("image"));
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUrl);
            imageProfile.setImageBitmap(bitmap);
        }catch (IOException e){
            Toast.makeText(this, "can't load image", Toast.LENGTH_SHORT).show();
        }

        homePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = home_page;
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
