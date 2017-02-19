package com.example.multisudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Other extends Activity 
{

	Button website, facebook, aboutus, back;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);

		website = (Button) findViewById(R.id.website);
		facebook = (Button) findViewById(R.id.facebookpage);
		aboutus = (Button) findViewById(R.id.aboutus);
		back = (Button) findViewById(R.id.otherback);
		
		
		website.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Other.this, Website.class);
				startActivity(i);
			}
		});

		
		facebook.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Other.this, FacebookPage.class);
				startActivity(i);
			}
		});
		
		
		
		aboutus.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(Other.this, AboutUs.class);
				startActivity(i);
			}
		});
		
		

		back.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
	}
}

