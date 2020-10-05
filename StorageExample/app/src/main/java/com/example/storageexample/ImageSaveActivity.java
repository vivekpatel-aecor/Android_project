package com.example.storageexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageSaveActivity extends AppCompatActivity {

    @BindView(R.id.ivShow)
    protected ImageView ivShow;
    protected final static int PICK_PHOTO_CODE = 100;
    protected final static int CAMERA_IMAGE_REQUEST = 101;
    protected Bitmap selectedImage;
    protected String photoFileName = new Date().toString() + ".jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_save);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btnSelectImage)
    protected void selectImage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Please select an image ");
        alertDialogBuilder.setPositiveButton("CAMERA",
                (arg0, arg1) -> checkPermission(Manifest.permission.CAMERA, 101));

        alertDialogBuilder.setNegativeButton("OTHER", (dialog, which) -> checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 100));

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    protected void checkPermission(String permission, int requestCode) {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                ImageSaveActivity.this,
                permission)
                == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 101) {
                openCamera();
            }
            if (requestCode == 100) {
                openAlbum();
            }
        }
        if (ContextCompat.checkSelfPermission(
                ImageSaveActivity.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            ImageSaveActivity.this,
                            new String[]{permission},
                            requestCode);
        }
    }

    protected void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    protected void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_IMAGE_REQUEST);
        }
    }


    @OnClick(R.id.btnSave)
    protected void saveImage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure to save image.. ");
        alertDialogBuilder.setPositiveButton("Yes",
                (arg0, arg1) -> images(selectedImage));

        alertDialogBuilder.setNegativeButton("No", (dialog, which) -> Toast.makeText(ImageSaveActivity.this, "Image not saved", Toast.LENGTH_SHORT).show());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    protected Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            if (Build.VERSION.SDK_INT > 27) {

                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((data != null) && requestCode == PICK_PHOTO_CODE) {
            Uri photoUri = data.getData();
            // Load the image located at photoUri into selectedImage
            selectedImage = loadFromUri(photoUri);
            ivShow.setImageBitmap(selectedImage);
        }
        if ((data != null) && requestCode == CAMERA_IMAGE_REQUEST) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                selectedImage = (Bitmap) extras.get("data");
                ivShow.setImageBitmap(selectedImage);
            } else {
                Toast.makeText(ImageSaveActivity.this, "Image is not capture", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void images(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File file = new File(directory, photoFileName);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(ImageSaveActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}