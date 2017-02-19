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

public class CenterDotSudoku extends Activity implements OnClickListener
{
	
	private static final String TAG = "Sukgludflkgfdkhd";
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.centerdotsudoku);
		
		

		View btnasteriksudokunewgame = findViewById(R.id.centerdotsudokunewgame);
		btnasteriksudokunewgame.setOnClickListener(this);
		View btnasteriksudokuhowtoplay = findViewById(R.id.centerdotsudokuabout);
		btnasteriksudokuhowtoplay.setOnClickListener(this);
		View btncenterdotsudokuback = findViewById(R.id.centerdotsudokuback);
		btncenterdotsudokuback.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
			case R.id.centerdotsudokuabout:
				Intent i = new Intent(this, CenterDotSudokuHowtoPlay.class);
		         startActivity(i);
		         break;
		         
			case R.id.centerdotsudokuback:
				finish();
		         break;
		         
			case R.id.centerdotsudokunewgame:
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
	      		startActivity(new Intent(this, PrefsCenterdotSudoku.class));
	      		return true;
	      }
	      
	      return false;
	   }
	
	private void openNewGameDialog() 
	   {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.CenterdotSudoku_game_title)
	           .setItems(R.array.difficultycenterdotsudoku,
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
		      Intent intent = new Intent(CenterDotSudoku.this, CenterDotSudokuGame.class);
		      intent.putExtra(CenterDotSudokuGame.KEY_DIFFICULTY_CENTERDOT, i);
		      startActivity(intent);
	   }
	
}

