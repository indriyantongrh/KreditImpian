package com.example.kreditimpian.FormPengajuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.kreditimpian.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class CaptureGambar extends AppCompatActivity {


    public static final int PERMISSION_REQUEST_CODE = 114;//request code for Camera and External Storage permission
    private static final int CAMERA_REQUEST_CODE = 133;//request code for capture image

    private Uri fileUri = null;//Uri to capture image
    private String getImageUrl = "";

    Button btnfoto,btnselanjutnya;


    int PICK_IMAGE_REQUEST_1 = 1;
    //untuk upload gambar
    Bitmap bitmap, decoded_1, decoded_2, decoded_3;
    int REQUEST_IMAGE_CAPTURE_1 = 11;
    public static final int REQUEST_PERM_WRITE_STORAGE = 102;
    public static final int CAPTURE_PHOTO = 104;
    private static final String TAG = CaptureGambar.class.getSimpleName();

    private String KEY_IMAGE = "image";

    ImageView image;
    Bitmap  decoded;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_gambar);

        image = findViewById(R.id.image);



        btnfoto = findViewById(R.id.btnfoto);
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //pakai alert dialog
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CaptureGambar.this);
                builder.setTitle("Pilihan");
                builder.setMessage("Silahkan memilih kamera atau galeri");
                builder.setPositiveButton("Kamera", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(CaptureGambar.this.getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_1);

                        }
                    }
                });
                builder.setNegativeButton("Galeri", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showFileChooser1();
                    }
                });

                android.app.AlertDialog alert = builder.create();
                alert.show();

      /*          ///set permition camera
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(CaptureGambar.this, new String[]{Manifest.permission.CAMERA},1);
                    }

                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CaptureGambar.this,
                            new String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_PERM_WRITE_STORAGE);
                }else {
                    takephoto();
                }*/



            }




 /*               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);*/

        });

/*        btnselanjutnya = findViewById(R.id.btnselanjutnya);
        btnselanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ///  startActivity(new Intent(getApplicationContext(),StepisiProduct.class).putExtra("img",getStringImage(bitmap)));



*//*
                Intent intent = new Intent(getApplication(), StepisiProduct.class);
                intent.putExtra("imagePath", decoded_1);
                startActivity(intent);
*//*

 *//*               ///Bitmap _bitmap; // your bitmap
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                decoded_1.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("byteArray", _bs.toByteArray());
                startActivity(intent);
*//*
            }


        });*/

    }


    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //untuk memilih gambar dari galeri
    private void showFileChooser1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_1);
    }


    //untuk set ke imageview
    private void setToImageView1(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded_1 = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded_1);


    }


    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //ini untuk gambar
        if (requestCode == PICK_IMAGE_REQUEST_1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(CaptureGambar.this.getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView1(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //ini untuk dari kamera
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
            setToImageView1(getResizedBitmap(imageBitmap, 512));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, CaptureGambar.this);
    }




    public void sendimage (View v)
    {
        Intent i = new Intent (CaptureGambar.this, StepisiProduct.class);
        i.putExtra("resID", bitmap);
        startActivity(i);
    }

  /*  public void  takephoto(){
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent,CAPTURE_PHOTO );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      if (resultCode == RESULT_OK){
          switch (requestCode){

              case CAPTURE_PHOTO;
                    Bitmap capturedCoolerBitmap = (Bitmap) data.getExtras().get("data");

                    int capturedCImageWidth =1200;
                    int capturedCImageHeight = 800;


          }


      }

    }*/





/*
    //untuk upload image, compress .JPEG ke bitmap
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //untuk memilih gambar
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

*//*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        //imageView.setImageBitmap(imageBitmap);
        setToImageView(getResizedBitmap(imageBitmap, 512));
    }*//*
    *//*Menampilkan gambar*//*
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        image.setImageBitmap(decoded);
    }

    // fungsi resize image
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    *//*  Check both permissions  *//*
    private boolean checkPermission() {
        ArrayList<String> permissions = new ArrayList<>();
        for (String permission : getAllPermissions()) {
            int result = checkPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissions.add(permission);
            }
        }
        //If both permissions are granted
        if (permissions.size() == 0)
            allPermissionGranted();
        else
            //if any one of them are not granted then request permission
            requestPermission(permissions.toArray(new String[permissions.size()]));
        return true;
    }

    *//*   on both permission granted  *//*
    private void allPermissionGranted() {
        //Initiate capture image method
        if (isDeviceSupportCamera())
            captureImage();
    }

    *//*  Request permissions  *//*
    private void requestPermission(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    *//*  Permissions string array  *//*
    private String[] getAllPermissions() {
        return new String[]{CAMERA, WRITE_EXTERNAL_STORAGE};
    }

    *//*  Method to check permissions  *//*
    public static int checkPermission(final Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    int counter = 0;//counter to traverse all permissions
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //show alert dialog if any of the permission denied
                            showMessageOKCancel(getString(R.string.permission_message),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                //If user click on OK button check permission again.
                                                checkPermission();
                                            }
                                        }
                                    }, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(CaptureGambar.this, R.string.capture_deny_message, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            return;
                        } else {
                            counter++;
                            //If counter is equal to permissions length mean all permission granted.
                            if (counter == permissions.length)
                                allPermissionGranted();
                        }
                    }

                }


                break;
        }
    }


    *//*  Alert dialog on permission denied    *//*
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(CaptureGambar.this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, okListener)
                .setNegativeButton(android.R.string.cancel, cancelListener)
                .setCancelable(false)
                .create()
                .show();
    }

    // Checking camera supportability
    private boolean isDeviceSupportCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA))
            return true;
        else {
            Toast.makeText(CaptureGambar.this, getResources().getString(R.string.camera_not_supported), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    *//*  Capture Image Method  *//*
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//Start intent with Action_Image_Capture
        fileUri = CameraUtils.getOutputMediaFileUri(this);//get fileUri from CameraUtils
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);//Send fileUri with intent
        startActivityForResult(intent, CAMERA_REQUEST_CODE);//start activity for result with CAMERA_REQUEST_CODE
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                try {
                    //When image is captured successfully
                    if (resultCode == RESULT_OK) {

                        //Check if device SDK is greater than 22 then we get the actual image path via below method
                        if (Build.VERSION.SDK_INT > 22)
                            getImageUrl = ImagePath_MarshMallow.getPath(CaptureGambar.this, fileUri);
                        else
                            //else we will get path directly
                            getImageUrl = fileUri.getPath();


                        //After image capture show captured image over image view
                        showCapturedImage();
                    } else
                        Toast.makeText(this, R.string.cancel_message, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

        }
    }


    *//*  Show Captured over ImageView  *//*
    private void showCapturedImage() {
        if (!getImageUrl.equals("") && getImageUrl != null)
            image.setImageBitmap(CameraUtils.convertImagePathToBitmap(getImageUrl, false));
        else
            Toast.makeText(this, R.string.capture_image_failed, Toast.LENGTH_SHORT).show();
    }

    *//**
     * Here we store the file url as it will be null after returning from camera
     * app
     *//*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    *//*
     * Here we restore the fileUri again
     *//*
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }*/
}
