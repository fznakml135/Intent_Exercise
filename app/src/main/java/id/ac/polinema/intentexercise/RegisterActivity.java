package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullName, nim, password, confirmPassword, homePage, aboutMe;
    private Button submitBtn;
    private CircleImageView imageProfile;
    private ImageView imageClick;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    private static String gambarUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.text_fullname);
        nim = findViewById(R.id.text_nim);
        password = findViewById(R.id.text_password);
        confirmPassword = findViewById(R.id.text_confirm_password);
        homePage = findViewById(R.id.text_homepage);
        aboutMe = findViewById(R.id.text_about);
        submitBtn = findViewById(R.id.button_ok);
        imageProfile = findViewById(R.id.image_profile);
        imageClick = findViewById(R.id.imageView);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(RegisterActivity.this, ProfileActivity.class);
                pindah.putExtra("fullName", fullName.getText().toString());
                pindah.putExtra("nim", nim.getText().toString());
                pindah.putExtra("password", password.getText().toString());
                pindah.putExtra("confirmPassword", confirmPassword.getText().toString());
                pindah.putExtra("homePage", homePage.getText().toString());
                pindah.putExtra("aboutMe", aboutMe.getText().toString());
                pindah.putExtra("image", gambarUri);
                startActivity(pindah);
            }
        });

        imageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "cancel input image", Toast.LENGTH_SHORT).show();
            return;
        }else if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri ImageUrl = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUrl);
                    imageProfile.setImageBitmap(bitmap);
                    gambarUri = ImageUrl.toString();
                }catch (IOException e){
                    Toast.makeText(this, "can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
