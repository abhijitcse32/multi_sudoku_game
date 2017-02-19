package com.example.multisudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class AsterikSudoku extends Activity implements OnClickListener
{
	
	private static final String TAG = "Sukgludflkgfdkhd";
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asteriksudoku);
		
		

		View btnasteriksudokunewgame = findViewById(R.id.asteriksudokunewgame);
		btnasteriksudokunewgame.setOnClickListener(this);
		View btnasteriksudokuhowtoplay = findViewById(R.id.asteriksudokuabout);
		btnasteriksudokuhowtoplay.setOnClickListener(this);
		View btnasteriksudokuback = findViewById(R.id.asteriksudokuback);
		btnasteriksudokuback.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
			case R.id.asteriksudokuabout:
				Intent i = new Intent(this, AsterikSudokuHowtoPlay.class);
		         startActivity(i);
		         break;
		         
			case R.id.asteriksudokuback:
				finish();
		         break;
		         
			case R.id.asteriksudokunewgame:
				openNewGameDialog();
		         break;
		}
	}
	
	@Override
	   public boolean onCreateOptionsMenu(Menu menu) 
	   {
	      super.onCreateOptionsMenu(menu);
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.menu, menu);
	      return true;
	   }
	
	@Override
	   public boolean onOptionsItemSelected(MenuItem item) 
	   {
	      switch (item.getItemId()) 
	      {
	      		case R.id.settings:
	      		startActivity(new Intent(this, PrefsAsterikSudoku.class));
	      		return true;
	      }
	      
	      return false;
	   }
	
	private void openNewGameDialog() 
	   {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.AsterikSudoku_game_title)
	           .setItems(R.array.difficultyasteriksudoku,
	            new DialogInterface.OnClickListener() 
	            {
	               public void onClick(DialogInterface dialoginterface,
	                     int i) 
	               {
	                  startGame(i);
	               }
	            })
	           .show();
	   }
	
	private void startGame(int i) 
	   {
		      Log.d(TAG, "clicked on " + i);
		      Intent intent = new Intent(AsterikSudoku.this, AsterikSudokuGame.class);
		      intent.putExtra(AsterikSudokuGame.KEY_DIFFICULTY_ASTERIK, i);
		      startActivity(intent);
	   }
	
}

