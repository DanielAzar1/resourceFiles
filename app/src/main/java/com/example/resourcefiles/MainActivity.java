package com.example.resourcefiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Daniel Azar
 * @version 1.0
 * @since 14/03/2025
 */
public class MainActivity extends AppCompatActivity {
    Button rfBtn, txtBtn;
    TextView tV1;
    EditText textEnter;
    private final String FILE_NAME = "rawtxt.txt";
    int resID;

    /**
     * @param savedInstanceState The saved instance state
     * The function creates the main activity
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV1 = findViewById(R.id.TV1);
        textEnter = findViewById(R.id.textEnter);

        // Getting the raw file ID
        String filename = FILE_NAME.substring(0, FILE_NAME.length() - 4);
        resID = getResources().getIdentifier(filename, "raw", getPackageName());
    }

    /**
     * @param view The button that is clicked
     * Function loads the written text and displays it
     * @return void
     */
    public void regText(View view)
    {
        String text = textEnter.getText().toString();
        tV1.setText(text);
    }

    /**
     * @param view The button that is clicked
     * @throws IOException The exception that is thrown
     * the function loads the raw file content and displays it
     * @return void
     */
    public void rawText(View view) throws IOException {
        // Opening raw file
        InputStream is = this.getResources().openRawResource(resID);

        // Reading raw file
        InputStreamReader isR = new InputStreamReader(is);

        // Copying raw file to a buffer
        BufferedReader br = new BufferedReader(isR);

        // Copying buffer to a string builder line by line
        StringBuilder sB = new StringBuilder();
        String line = br.readLine();
        String text = " ";
        while (line != null) {
            sB.append(line + '\n');
            line = br.readLine();
        }

        // Displaying the string
        tV1.setText(sB.toString());

        // Closing all the streams
        br.close();
        isR.close();
        is.close();
    }

    /**
     * @param menu The menu
     * @return True if the menu is created
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * @param item The item that is clicked
     * @return True if the item is clicked
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        Intent ti = new Intent(this, Credits.class);
        startActivity(ti);
        return true;
    }
}