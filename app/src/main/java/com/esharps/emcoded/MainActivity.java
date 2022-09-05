package com.esharps.emcoded;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.esharps.emcoded.data.CodeProject;
import com.esharps.emcoded.model.CodeLine;

/**
 * MainActivity for running the EmCoded Android app
 * This class handles the following UI interactions for the app:
 * - Button clicks
 * - Implement line number feature in code editor
 * - User typing code in editor
 */
public class MainActivity extends AppCompatActivity {

    // UI Elements
    private TextView lineNumberRows;
    private EditText codeEditorText;
    private EditText consoleOutput;
    private Button runCodeBtn;
    private Button clearBtn;
    private Button saveBtn;
    final String CONSOLE_PROMPT = "[emcoded]:> ";

    String command = "x = 'Hello World!'\nprint(x)"; //default value for MVP
    String language = "python"; //default value for MVP

    public RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineNumberRows = findViewById(R.id.lineNumberRows);
        codeEditorText = findViewById(R.id.codeEditorText);
        consoleOutput = findViewById(R.id.consoleOutput);
        runCodeBtn = findViewById(R.id.runBtn);
        clearBtn = findViewById(R.id.clearBtn);
        saveBtn = findViewById(R.id.saveBtn);

        CodeProject myCodeProject = new CodeProject();
        myCodeProject.setCodeLanguage(language);

        queue = Volley.newRequestQueue(this);

        codeEditorText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int numLines = codeEditorText.getLineCount();
                String lineText = "";

                for (int y = 1; y <= numLines; y++) {
                    lineText = lineText + y + "\n";
                }
                lineNumberRows.setText(lineText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CodeLine newLine = new CodeLine("python", codeEditorText.toString());
                myCodeProject.setCodeLine(newLine);
            }
        });

        runCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String response = "";
                response = myCodeProject.submitCode(language, command, queue);
                consoleOutput.setText(CONSOLE_PROMPT + response);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consoleOutput.setText(CONSOLE_PROMPT);
            }
        });
    } // end of onCreate()

}

