package com.stopchildpornph.stopchildpornph;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReportUsingImage extends AppCompatActivity {
    String picturePath;
    Button btnUpload;
    EditText editTextFile;
    final int CAMERA_REQUEST = 1;
    final int FILE_SELECT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_using_image);
        setUp();
        btnUploadOnClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {  // From Camera
                Log.e("onActivityResult", "CAMERA");
                Log.e("TEST", data.getExtras().get("data").toString());
//                bitmap = (Bitmap) data.getExtras().get("data");
            } else if (requestCode == 2) {   // From Gallery
                Log.e("onActivityResult", "FILE UPLOAD");
                Uri selectedImage = data.getData();
                String[] strArrFilePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, strArrFilePathColumn,
                        null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(strArrFilePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    String fileName = picturePath.substring(picturePath.lastIndexOf('/')+1, picturePath.length());
                    editTextFile.setText(fileName);
                    cursor.close();
                }
//                bitmap = BitmapFactory.decodeFile(picturePath);
            }
//            savePhotoToDb();
        }
    }

    private void setUp() {
        btnUpload = (Button) findViewById(R.id.buttonUpload);
        editTextFile = (EditText) findViewById(R.id.editTextFile);
    }

    private void btnUploadOnClick() {
      btnUpload.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              AlertDialog.Builder  builder = new AlertDialog.Builder(ReportUsingImage.this);
              builder.setTitle("Upload Image");
              builder.setItems(R.array.image_dialog_options, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      switch (which) {
                          case 0:
                              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                              startActivityForResult(intent, CAMERA_REQUEST);
                              break;
                          case 1:
                              intent = new Intent(Intent.ACTION_PICK,
                                      MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                              intent.setType("image/*");
                              startActivityForResult(Intent.createChooser(intent,
                                      "Select Picture"), FILE_SELECT_REQUEST);
                              break;
                          case 2:
                              dialog.dismiss();
                              break;
                      }
                  }
              });
              builder.show();
          }
      });
    }

}
