package com.example.multisudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSudoku extends Activity 
{

	Button stripesudoku, xsudoku, hypersudoku, centerdotsudoku, asteriksudoku;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectsudoku);

		stripesudoku = (Button) findViewById(R.id.stripesudoku);
		xsudoku = (Button) findViewById(R.id.xsudoku);
		hypersudoku = (Button) findViewById(R.id.hypersudoku);
		centerdotsudoku = (Button) findViewById(R.id.centerdotsudoku);
		asteriksudoku = (Button) findViewById(R.id.asteriksudoku);
		
		
		stripesudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(SelectSudoku.this, StripeSudoku.class);
				startActivity(i);
			}
		});
		
		xsudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(SelectSudoku.this, XSudoku.class);
				startActivity(i);
			}
		});
		
		hypersudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(SelectSudoku.this, HyperSudoku.class);
				startActivity(i);
			}
		});
		
		centerdotsudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(SelectSudoku.this, CenterDotSudoku.class);
				startActivity(i);
			}
		});
		
		asteriksudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(SelectSudoku.this, AsterikSudoku.class);
				startActivity(i);
			}
		});

		
	}
}

