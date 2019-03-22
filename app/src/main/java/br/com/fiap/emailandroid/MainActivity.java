package br.com.fiap.emailandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText toEditText;
    private EditText subjectEditText;
    private EditText messageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toEditText = findViewById(R.id.toEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        messageEditText = findViewById(R.id.messageEditView);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    private void sendEmail() {
        String recipientList = toEditText.getText().toString();
        String[] recipients = recipientList.split(",");

        for (int i = 0; i < recipients.length; i++) {
            String r = recipients[i];
            if(!isEmailValid(r)){
                Toast.makeText(MainActivity.this, "Incorrect Email", Toast.LENGTH_LONG).show();
                return;
            }
        }

        String subject = subjectEditText.getText().toString();

        String message = messageEditText.getText().toString();


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));


    }

    boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
