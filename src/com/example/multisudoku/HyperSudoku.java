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

public class HyperSudoku extends Activity implements OnClickListener
{
	
	private static final String TAG = "Sukgludflkgfdkhd";
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hypersudoku);
		
		

		View btnasteriksudokunewgame = findViewById(R.id.hypersudokunewgame);
		btnasteriksudokunewgame.setOnClickListener(this);
		View btnasteriksudokuhowtoplay = findViewById(R.id.hypersudokuabout);
		btnasteriksudokuhowtoplay.setOnClickListener(this);
		View btnhypersudokuback = findViewById(R.id.hypersudokuback);
		btnhypersudokuback.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
			case R.id.hypersudokuabout:
				Intent i = new Intent(this, HyperSudokuHowtoPlay.class);
		         startActivity(i);
		         break;
		         
			case R.id.hypersudokuback:
				finish();
		         break;
		         
			case R.id.hypersudokunewgame:
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
	      		startActivity(new Intent(this, PrefsHyperSudoku.class));
	      		return true;
	      }
	      
	      return false;
	   }
	
	private void openNewGameDialog() 
	   {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.HyperSudoku_game_title)
	           .setItems(R.array.difficultyhypersudoku,
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
		      Intent intent = new Intent(HyperSudoku.this, HyperSudokuGame.class);
		      intent.putExtra(HyperSudokuGame.KEY_DIFFICULTY_HYPER, i);
		      startActivity(intent);
	   }
	
}



