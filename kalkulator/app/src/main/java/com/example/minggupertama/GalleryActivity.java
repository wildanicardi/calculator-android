package com.example.minggupertama;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final int REQUEST_CODE_OPEN_DOCUMENT = 2;

    private static final String TAG = "GalleryActivity";
    private ArrayList<Gallery> galleries;
    private GalleryAdapter mAdapterGallery;
    private ListView recyclerViewGallery;

    public DriveServiceHelper mDriveServiceHelper;
    public String mOpenFileId;

    private EditText mFileTitleEditText;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Cari");
        searchView.setFocusable(true);
        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                caridata(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });

        requestSignIn();

        Button btn_get = findViewById(R.id.btn_get);

        recyclerViewGallery = (ListView) findViewById(R.id.list_view_get);
        galleries = new ArrayList<>();
        mAdapterGallery = new GalleryAdapter(this, galleries);

        recyclerViewGallery.setAdapter(mAdapterGallery);
        mAdapterGallery.notifyDataSetChanged();

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        recyclerViewGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gallery gallery = galleries.get(position);
                Uri uri = Uri.parse("https://drive.google.com/uc?export=view&id=" + gallery.getId()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    handleSignInResult(resultData);
                }
                break;

            case REQUEST_CODE_OPEN_DOCUMENT:
                if (resultCode == Activity.RESULT_OK && resultData != null) {
                    Uri uri = resultData.getData();
                    if (uri != null) {
//                        openFileFromFilePicker(uri);
                    }
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, resultData);
    }

    private void requestSignIn() {
        Log.d(TAG, "Requesting sign-in");

        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                        .build();
        GoogleSignInClient client = GoogleSignIn.getClient(this, signInOptions);

        // The result of the sign-in Intent is handled in onActivityResult.
        startActivityForResult(client.getSignInIntent(), REQUEST_CODE_SIGN_IN);
    }

    /**
     * Handles the {@code result} of a completed sign-in activity initiated from {@link
     * #requestSignIn()}.
     */
    private void handleSignInResult(Intent result) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
                .addOnSuccessListener(googleAccount -> {
                    Log.d(TAG, "Signed in as " + googleAccount.getEmail());

                    // Use the authenticated account to sign in to the Drive service.
                    GoogleAccountCredential credential =
                            GoogleAccountCredential.usingOAuth2(
                                    this, Collections.singleton(DriveScopes.DRIVE_FILE));
                    credential.setSelectedAccount(googleAccount.getAccount());
                    Drive googleDriveService =
                            new Drive.Builder(
                                    AndroidHttp.newCompatibleTransport(),
                                    new GsonFactory(),
                                    credential)
                                    .setApplicationName("Drive API Migration")
                                    .build();

                    // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                    // Its instantiation is required before handling any onClick actions.
                    mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
                })
                .addOnFailureListener(exception -> Log.e(TAG, "Unable to sign in.", exception));
    }

    private void query() {
        galleries.clear();
        mAdapterGallery.notifyDataSetChanged();
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Querying for files.");
            mDriveServiceHelper.queryFiles()
                    .addOnSuccessListener(fileList -> {
                        StringBuilder builder = new StringBuilder();
                        for (com.google.api.services.drive.model.File file : fileList.getFiles()) {
//                            builder.append(file.getHasThumbnail()).append("\n");
                            if(file.getMimeType().equalsIgnoreCase("image/png")) {
                                Gallery gallery = new Gallery();
                                gallery.setName(file.getName());
                                gallery.setId(file.getId());
                                gallery.setFile(file.getThumbnailLink());
                                galleries.add(gallery);
                            }
                        }
                        mAdapterGallery.notifyDataSetChanged();
                    })
                    .addOnFailureListener(exception -> Log.e(TAG, "Unable to query files.", exception));
        }
        mAdapterGallery.notifyDataSetChanged();
    }

    private void caridata(String query) {
        galleries.clear();
        mAdapterGallery.notifyDataSetChanged();
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Querying for files.");
            mDriveServiceHelper.queryFiles()
                    .addOnSuccessListener(fileList -> {
                        StringBuilder builder = new StringBuilder();
                        for (com.google.api.services.drive.model.File file : fileList.getFiles()) {
//                            builder.append(file.getHasThumbnail()).append("\n");
                            if(file.getName().equalsIgnoreCase(query)) {
                                Gallery gallery = new Gallery();
                                gallery.setName(file.getName());
                                gallery.setId(file.getId());
                                gallery.setFile(file.getThumbnailLink());
                                galleries.add(gallery);
                            }
                        }
                        mAdapterGallery.notifyDataSetChanged();
                    })
                    .addOnFailureListener(exception -> Log.e(TAG, "Unable to query files.", exception));
        }
        mAdapterGallery.notifyDataSetChanged();
    }

}
