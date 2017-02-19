package com.example.multisudoku;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailSahajjo extends Activity {

	private TextView recipient;
	private EditText subject;
	private EditText body;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.email);

      recipient = (TextView) findViewById(R.id.recipient);
      subject = (EditText) findViewById(R.id.subject);
      body = (EditText) findViewById(R.id.body);
      
      Button sendBtn = (Button) findViewById(R.id.sendEmail);
      sendBtn.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
        	 sendEmail();
        	 
        	 recipient.setText("");
        	 subject.setText("");
        	 body.setText("");
         }
   });

   }
   protected void sendEmail() {

      String[] recipients = {recipient.getText().toString()};
      Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
      
      email.setType("message/rfc822");

      email.putExtra(Intent.EXTRA_EMAIL, recipients);
      email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
      email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

      try {
	    
         startActivity(Intent.createChooser(email, "Choose an email client from..."));
     
      } catch (android.content.ActivityNotFoundException ex) {
         Toast.makeText(EmailSahajjo.this, "No email client installed.",
        		 Toast.LENGTH_LONG).show();
      }
   }
   
}
