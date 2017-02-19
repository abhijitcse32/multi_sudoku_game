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

public class StripeSudoku extends Activity implements OnClickListener
{
	
	private static final String TAG = "Sukgludflkgfdkhd";
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stripesudoku);
		
		

		View btnstripesudokunewgame = findViewById(R.id.stripesudokunewgame);
		btnstripesudokunewgame.setOnClickListener(this);
		View btnstripesudokuhowtoplay = findViewById(R.id.stripesudokuabout);
		btnstripesudokuhowtoplay.setOnClickListener(this);
		View btnstripesudokuback = findViewById(R.id.stripesudokuback);
		btnstripesudokuback.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
			case R.id.stripesudokuabout:
				Intent i = new Intent(this, StripeSudokuHowtoPlay.class);
		         startActivity(i);
		         break;
		         
			case R.id.stripesudokuback:
				finish();
				break;
		    
		         
			case R.id.stripesudokunewgame:
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
	      		startActivity(new Intent(this, PrefsStripeSudoku.class));
	      		return true;
	      }
	      
	      return false;
	   }
	
	private void openNewGameDialog() 
	   {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.StripeSudoku_game_title)
	           .setItems(R.array.difficultystripesudoku,
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
		      Intent intent = new Intent(StripeSudoku.this, StripeSudokuGame.class);
		      intent.putExtra(StripeSudokuGame.KEY_DIFFICULTY, i);
		      startActivity(intent);
	   }
	
}

