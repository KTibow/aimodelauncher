package com.example.aimodelauncher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make sure the keyboard is visible when the activity starts
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_main);

        final EditText queryEditText = findViewById(R.id.query_edit_text);
        Button searchButton = findViewById(R.id.search_button);

        // Request focus and show keyboard once layout is ready
        queryEditText.post(() -> {
            queryEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(queryEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        TextView.OnEditorActionListener submitListener = (v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                openGoogleAiMode(queryEditText.getText().toString());
                hideKeyboard(v);
                return true;
            }
            return false;
        };

        queryEditText.setOnEditorActionListener(submitListener);

        searchButton.setOnClickListener(v -> {
            openGoogleAiMode(queryEditText.getText().toString());
            hideKeyboard(v);
        });
    }

    private void openGoogleAiMode(String query) {
        if (query == null || query.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a query", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String encoded = URLEncoder.encode(query.trim(), "UTF-8");
            String url = "https://www.google.com/search?q=" + encoded + "&udm=50";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            // Close this activity so it doesn't remain in background or recent tasks
            finish();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                finishAndRemoveTask();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Unable to encode query", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
