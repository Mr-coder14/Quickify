package com.example.quiz;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import Classes.Reportissue;

public class Reportissueactivity extends AppCompatActivity {
//    private FusedLocationProviderClient fusedLocationClient;
//    private double latitude;
//    private double longitude;

    private LinearLayout fourWheelerLayout, twoWheelerLayout;
    private ImageButton button;
    private ImageView uploadImage, ticktwo, tickfour, u1;
    private EditText vehicleBrand, vehicleModel, issueDescription, issuetitle, vecihleno,address;
    private Button submitButton, buttonUpload;
    private Uri selectedImageUri;
    private TextView charCountTextView;
    private boolean isFourWheelerSelected = false;
    private boolean isTwoWheelerSelected = false;
    private String uid;
    private Reportissue reportissue = new Reportissue();
    private RadioButton radioButton;
    private DatabaseReference databaseReference, usrref;
    private ProgressDialog progressDialog;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    uploadImage.setVisibility(View.VISIBLE);
                    u1.setVisibility(View.GONE);
                    uploadImage.setImageURI(selectedImageUri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportissueactivity);

        initializeUI();
        setupListeners();
        fetchUserData();
//        requestLocationPermissions();
    }

    private void initializeUI() {
        button = findViewById(R.id.backbtni);
        address=findViewById(R.id.address);
        issuetitle = findViewById(R.id.issuetitle);
        radioButton=findViewById(R.id.ratiobtnaddress);
        vecihleno = findViewById(R.id.vehicleno);
        usrref = FirebaseDatabase.getInstance().getReference().child("users");
        tickfour = findViewById(R.id.tickfour);
        ticktwo = findViewById(R.id.ticktwo);
        u1 = findViewById(R.id.uploadimage1);
        uploadImage = findViewById(R.id.uploadimage);
        vehicleBrand = findViewById(R.id.textvehiclebrand);
        vehicleModel = findViewById(R.id.brandnametxt);
        issueDescription = findViewById(R.id.editTextDescription);
        submitButton = findViewById(R.id.buttonSubmit);
        buttonUpload = findViewById(R.id.buttonUpload);
        charCountTextView = findViewById(R.id.charCountTextView);

        fourWheelerLayout = findViewById(R.id.fourwheeler);
        twoWheelerLayout = findViewById(R.id.twowheeler);
        ticktwo.setVisibility(View.GONE);
        tickfour.setVisibility(View.GONE);

        uid = FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("issues");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.setCancelable(false);
    }

    private void setupListeners() {
        button.setOnClickListener(v -> finish());
        setupTextWatchers();
        setupVehicleSelectionListeners();
        buttonUpload.setOnClickListener(v -> openGallery());
     submitButton.setOnClickListener(v ->
             validateAndSubmitIssue()
             );
    }

    private void fetchUserData() {
        usrref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String phoneNumber = snapshot.child("phno").getValue(String.class);
                    reportissue.setUsername(name);
                    reportissue.setPhno(phoneNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

//    private void requestLocationPermissions() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//            }, 1);
//        } else {
//            // Permissions granted, retrieve location
//            getLocationn();
//        }
//    }

    private void setupTextWatchers() {
        issueDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update character count
                int currentLength = s.length();
                charCountTextView.setText(currentLength + "/500");
                charCountTextView.setTextColor(currentLength > 500 ? getResources().getColor(android.R.color.holo_red_light) : getResources().getColor(android.R.color.black));
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setupVehicleSelectionListeners() {
        fourWheelerLayout.setOnClickListener(v -> {
            isFourWheelerSelected = true;
            isTwoWheelerSelected = false;
            reportissue.setFourwheeler(true);
            reportissue.setTwowheeler(false);
            tickfour.setVisibility(View.VISIBLE);
            ticktwo.setVisibility(View.GONE);
        });

        twoWheelerLayout.setOnClickListener(v -> {
            isTwoWheelerSelected = true;
            isFourWheelerSelected = false;
            reportissue.setTwowheeler(true);
            reportissue.setFourwheeler(false);
            ticktwo.setVisibility(View.VISIBLE);
            tickfour.setVisibility(View.GONE);
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }


    public void validateAndSubmitIssue() {

        String brand = vehicleBrand.getText().toString().trim();
        String model = vehicleModel.getText().toString().trim();
        String adr=address.getText().toString().trim();
        String description = issueDescription.getText().toString().trim();
        String vehino = vecihleno.getText().toString().trim();
        String issuetit = issuetitle.getText().toString().trim();


        if (!isFourWheelerSelected && !isTwoWheelerSelected) {
            showToast("Please select either Four Wheeler or Two Wheeler");
            return;
        }
        if(TextUtils.isEmpty(adr)){
            showToast("Please Enter the Address");
            return;
        }
        if (selectedImageUri == null) {
            showToast("Please upload an image");
            return;
        }
        if (TextUtils.isEmpty(vehino)) {
            showToast("Please enter the vehicle no");
            return;
        }

        if (TextUtils.isEmpty(issuetit)) {
            showToast("Please enter the Issue Title");
            return;
        }
        if (TextUtils.isEmpty(brand)) {
            showToast("Please enter the vehicle brand name");
            return;
        }
        if(!radioButton.isChecked()){
            showToast("Please Select the address");
            return;
        }
        if (TextUtils.isEmpty(model)) {
            showToast("Please enter the vehicle model number");
            return;
        }
        if (TextUtils.isEmpty(description) || description.length() < 50) {
            showToast("Description must be at least 50 characters long");
            return;
        }
        if (description.length() > 500) {
            showToast("Description must not exceed 500 characters");
            return;
        }

        // Show ProgressDialog before upload starts
        progressDialog.show();
        String reportId = databaseReference.push().getKey();

        // Upload image to Firebase Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("reportissue")
                .child(uid)
                .child(UUID.randomUUID().toString());

        storageReference.putFile(selectedImageUri)
                .addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    reportissue.setVehicleno(vehino);
                    reportissue.setDescription(description);
                    reportissue.setBrandname(brand);
                    reportissue.setModelno(model);
                    reportissue.setIssuetitile(issuetit);
                    reportissue.setAddress(adr);
                    reportissue.setImageuri(uri.toString());
                    reportissue.setLatitude(String.valueOf(00.0));
                    reportissue.setLongitude(String.valueOf(00.0));
                    databaseReference.child(uid).child(reportId).setValue(reportissue)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {

                                    progressDialog.dismiss();
                                    showToast("Issue submitted successfully");
                                    startActivity(new Intent(Reportissueactivity.this,selectshop.class));
                                    clearFields();
                                } else {
                                    progressDialog.dismiss();
                                    showToast("Failed to submit issue");
                                }
                            });
                }))
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    showToast("Failed to upload image");
                });
    }

    private void clearFields() {
        vehicleBrand.setText("");
        vehicleModel.setText("");
        issueDescription.setText("");
        issuetitle.setText("");
        vecihleno.setText("");
        selectedImageUri = null;
        uploadImage.setVisibility(View.GONE);
        u1.setVisibility(View.VISIBLE);
        ticktwo.setVisibility(View.GONE);
        tickfour.setVisibility(View.GONE);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    private void getLocationn() {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            fusedLocationClient.getLastLocation()
//                    .addOnSuccessListener(this, location -> {
//                        if (location != null) {
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
//                        } else {
//                            // Handle case when location is null
//                            showToast("Unable to retrieve location. Please try again.");
//                        }
//                    });
//        } else {
//            requestLocationPermissions(); // If permissions are not granted, request them
//        }
//    }

//    private boolean isLocationEnabled() {
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
//                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//    }
}
