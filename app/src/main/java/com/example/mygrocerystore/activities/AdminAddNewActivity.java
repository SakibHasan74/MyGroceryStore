package com.example.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mygrocerystore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;


public class AdminAddNewActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChosseImage;
    private Button mButtonUpload;
    private Uri img_url;
    private String type;
    private ImageView mImageView;
    private EditText name;
    private Spinner admintype;
    private EditText price;
    private EditText description;
    private  EditText rating;
    FirebaseFirestore firestore;
    uploadPost u;

    FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
    StorageReference storageReference=firebaseStorage.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    DocumentReference documentReference;


    FirebaseAuth auth;
    FirebaseDatabase database;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new);
        mButtonChosseImage=findViewById(R.id.btn);
        mButtonUpload=findViewById(R.id.btn2);
        mImageView=findViewById(R.id.imgc);
        name=findViewById(R.id.admin_name);
       admintype=findViewById(R.id.admin_type);
        price=findViewById(R.id.admin_price);
        description=findViewById(R.id.admin_des);
        rating=findViewById(R.id.admin_rat);
        u=new uploadPost();
        firestore=FirebaseFirestore.getInstance();


        String items[]=new String[]{"fruit","fish","vegetable","egg","milk"};
        admintype.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));
        admintype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView< ? > parent, View view, int position, long id) {

                    type=admintype.getSelectedItem().toString();
                }


            @Override
            public void onNothingSelected(AdapterView< ? > parent) {

            }
        });
        mButtonChosseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.setName(name.getText().toString().trim());
                u.setDes(description.getText().toString().trim());
               u.setType(admintype.getSelectedItem().toString().trim());
               u.setRating(rating.getText().toString().trim());
               u.setPrice(Integer.parseInt(price.getText().toString().trim()));
               firestore.collection("AllProducts").add(u);
                Toast.makeText(AdminAddNewActivity.this,"Successfuly updated",Toast.LENGTH_LONG).show();

            }
        });

    }
    private void openFileChooser()
    {
        Intent intent = new Intent();                            //initialize intent obf from class
        intent.setType("image/*");                              //we have to give the type of the intent we want to get
        intent.setAction(Intent.ACTION_GET_CONTENT);            //have to set Action about any content and it data type is image
        startActivityForResult(intent,PICK_IMAGE_REQUEST);     //to get the result of intent request we use startActivityForResult
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            img_url =  data.getData();
            Picasso.get().load(img_url).into(mImageView);
        }
    }
    public String getFileExtention(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void Image_Upload()
    {
        if(img_url!=null)
        {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtention(img_url));
            fileReference.putFile(img_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
//                    result.addOnSuccessListener(new OnSuccessListener< Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//
//                            String uri_string = uri.toString();
//                            Toast.makeText(getApplicationContext(),"Uploaded Successfully" ,Toast.LENGTH_SHORT).show();
//                            documentReference = firebaseFirestore.collection("notes").document(user.getUid()).collection("Image").document();
//                            uploadPost post = new uploadPost(picture_title.getText().toString(),datatext,hourtext,taskSnapshot.toString());
//                            Map<String,Object> m = new HashMap<>();
//
//
//                                }
//                            });
//                        }
//                    });



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener< UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());

                }
            });
        }


    }

    class  uploadPost
    {
        String name ;
        String des;
        int price;
        String rating;
        String imgUri;
        String type;

        public uploadPost() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getImgUri() {
            return imgUri;
        }

        public void setImgUri(String imgUri) {
            this.imgUri = imgUri;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}