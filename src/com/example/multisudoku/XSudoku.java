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

public class XSudoku extends Activity implements OnClickListener
{
	
	private static final String TAG = "Sukgludflkgfdkhd";
	

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xsudoku);
		
		

		View btnasteriksudokunewgame = findViewById(R.id.xsudokunewgame);
		btnasteriksudokunewgame.setOnClickListener(this);
		View btnasteriksudokuhowtoplay = findViewById(R.id.xsudokuabout);
		btnasteriksudokuhowtoplay.setOnClickListener(this);
		View btnaxsudokuback = findViewById(R.id.xsudokuback);
		btnaxsudokuback.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
			case R.id.xsudokuabout:
				Intent i = new Intent(this, XSudokuHowtoPlay.class);
		         startActivity(i);
		         break;
		         
			case R.id.xsudokuback:
				finish();
		         break;
		         
			case R.id.xsudokunewgame:
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
	      		startActivity(new Intent(this, PrefsXSudoku.class));
	      		return true;
	      }
	      
	      return false;
	   }
	
	private void openNewGameDialog() 
	   {
	      new AlertDialog.Builder(this)
	           .setTitle(R.string.XSudoku_game_title)
	           .setItems(R.array.difficultyxsudoku,
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
		      Intent intent = new Intent(XSudoku.this, XSudokuGame.class);
		      intent.putExtra(XSudokuGame.KEY_DIFFICULTY_X, i);
		      startActivity(intent);
	   }
	
}


