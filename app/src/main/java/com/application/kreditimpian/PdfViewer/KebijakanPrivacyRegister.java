package com.application.kreditimpian.PdfViewer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.LoginRegister.Register;
import com.application.kreditimpian.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class KebijakanPrivacyRegister extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {

    PDFView pdfViewer;
    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;
    Integer pageNumber = 0;
    String pdfFileName;
    private static final String TAG = KebijakanPrivacyRegister.class.getSimpleName();
    public static final String SAMPLE_FILE = "kebijakanprivacy.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebijakan_privacy);

        setActionBarTitle("Kebijakan Privacy");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfViewer = findViewById(R.id.pdfViewer);
        displayFromAsset(SAMPLE_FILE);
    }




    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfViewer.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .onPageChange(KebijakanPrivacyRegister.this)
                .enableAnnotationRendering(true)
                .onLoad(KebijakanPrivacyRegister.this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(KebijakanPrivacyRegister.this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfViewer.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfViewer.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(KebijakanPrivacyRegister.this, Register.class));
        finish();
        super.onBackPressed();
    }
}
