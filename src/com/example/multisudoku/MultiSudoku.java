package com.example.multisudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiSudoku extends Activity 
{

	Button multisudoku,  howtoplay, emailhelp, sudokuquiz, other, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		multisudoku = (Button) findViewById(R.id.multisudoku);
		howtoplay = (Button) findViewById(R.id.how_to_play);
		emailhelp = (Button) findViewById(R.id.emailhelp);
		sudokuquiz = (Button) findViewById(R.id.sudoku_quiz);
		other = (Button) findViewById(R.id.other);
		exit = (Button) findViewById(R.id.exit);
		
		multisudoku.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MultiSudoku.this, SelectSudoku.class);
				startActivity(i);
			}
		});

		
		
		howtoplay.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MultiSudoku.this, HowToPlay.class);
				startActivity(i);
			}
		});
		
		emailhelp.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MultiSudoku.this, EmailSahajjo.class);
				startActivity(i);
			}
		});
		
		sudokuquiz.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MultiSudoku.this, Quiz.class);
				startActivity(i);
			}
		});
		
		other.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MultiSudoku.this, Other.class);
				startActivity(i);
			}
		});
		
		exit.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
	}
}

