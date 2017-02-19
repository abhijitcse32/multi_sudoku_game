package com.example.multisudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class HyperSudokuGame extends Activity  
{
	private static final String TAG = "Sudoku";
	
	public static final String KEY_DIFFICULTY_HYPER =
		      "com.example.multisudoku.difficultyhypersudoku";
	
	
	public static final int DIFFICULTYHYPERSUDOKU_LOW = 0;
	   public static final int DIFFICULTYHYPERSUDOKU_MEDIUM = 1;
	   public static final int DIFFICULTYHYPERSUDOKU_HARD = 2;
	   
	   private int puzzle[] = new int[9 * 9];
	   
	   private final String hypersudokulowPuzzle =
			      "106040700030700156709016000" +
			      "060107030040060907801304020" +
			      "025078301600400530017020068";
			   private final String hypersudokumediumPuzzle =
			      "040002700102097030738050290" +
			      "010706503005310080260008009" +
			      "007905460000063000090000300";
			   private final String hypersudokuhardPuzzle =
			      "000180000064035007100040790" +
			      "915803000600090012000000000" +
			      "390650078040000050000208000";
			   
			   private PuzzleViewHyper puzzleView;
			   
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			  super.onCreate(savedInstanceState);
			  Log.d(TAG, "onCreate");

			  int diff = getIntent().getIntExtra(KEY_DIFFICULTY_HYPER,
					  DIFFICULTYHYPERSUDOKU_LOW);
			      puzzle = getPuzzle(diff);
			      calculateUsedTiles();

			      puzzleView = new PuzzleViewHyper(this);
			      setContentView(puzzleView);
			      puzzleView.requestFocus();
		}
		
		 private int[] getPuzzle(int diff) 
		   {
		      String puz;
		      switch (diff) 
		      {
		      	 case DIFFICULTYHYPERSUDOKU_MEDIUM:
		      	 puz= hypersudokumediumPuzzle;
		         break;
		      	 case DIFFICULTYHYPERSUDOKU_HARD:
		         puz = hypersudokuhardPuzzle;
		         break;
		      	 case DIFFICULTYHYPERSUDOKU_LOW:
		      	 default:
		         puz = hypersudokulowPuzzle;
		         break;
		      }
		      return fromPuzzleString(puz);
		   }
		 
		 static private String toPuzzleString(int[] puz) 
		   {
		      StringBuilder buf = new StringBuilder();
		      for (int element : puz) 
		      {
		         buf.append(element);
		      }
		      return buf.toString();
		   }
		 
		 static protected int[] fromPuzzleString(String string) 
		   {
		      int[] puz = new int[string.length()];
		      for (int i = 0; i < puz.length; i++) 
		      {
		         puz[i] = string.charAt(i) - '0';
		      }
		      return puz;
		   }
		 
		 private int getTile(int x, int y) 
		   {
		      return puzzle[y * 9 + x];
		   }
		 
		 private void setTile(int x, int y, int value) 
		   {
		      puzzle[y * 9 + x] = value;
		   }
		 
		 protected String getTileString(int x, int y) 
		   {
		      int v = getTile(x, y);
		      if (v == 0)
		         return "";
		      else
		         return String.valueOf(v);
		   }
		 
		 protected boolean setTileIfValid(int x, int y, int value) 
		   {
		      int tiles[] = getUsedTiles(x, y);
		      if (value != 0) {
		        for (int tile : tiles) 
		        {
		            if (tile == value)
		               return false;
		         }
		      }
		      setTile(x, y, value);
		      calculateUsedTiles();
		      return true;
		   }
		 
		 protected void showKeypadOrError(int x, int y) 
		   {
		      int tiles[] = getUsedTiles(x, y);
		      if (tiles.length == 9) 
		      {
		         Toast toast = Toast.makeText(this,
		               R.string.no_moves_label, Toast.LENGTH_SHORT);
		         toast.setGravity(Gravity.CENTER, 0, 0);
		         toast.show();
		      }
		      else
		      {
		         Log.d(TAG, "showKeypad: used=" + toPuzzleString(tiles));
		         Dialog v = new KeypadHyper(this, tiles, puzzleView);
		         v.show();
		      }
		   }
		 
		 private final int used[][][] = new int[9][9][];

		   protected int[] getUsedTiles(int x, int y) 
		   {
		      return used[x][y];
		   }
		   
		   private void calculateUsedTiles() 
		   {
		      for (int x = 0; x < 9; x++) 
		      {
		         for (int y = 0; y < 9; y++) 
		         {
		            used[x][y] = calculateUsedTiles(x, y);
		         }
		      }
		   }
		   
		   private int[] calculateUsedTiles(int x, int y) 
		   {
		      int c[] = new int[9];
		   
		      for (int i = 0; i < 9; i++) 
		      { 
		         if (i == y)
		            continue;
		         int t = getTile(x, i);
		         if (t != 0)
		            c[t - 1] = t;
		      }
		      
		      for (int i = 0; i < 9; i++) 
		      { 
		         if (i == x)
		            continue;
		         int t = getTile(i, y);
		         if (t != 0)
		            c[t - 1] = t;
		      }
		      
		      int startx = (x / 3) * 3; 
		      int starty = (y / 3) * 3;
		      for (int i = startx; i < startx + 3; i++) 
		      {
		         for (int j = starty; j < starty + 3; j++) 
		         {
		            if (i == x && j == y)
		               continue;
		            int t = getTile(i, j);
		            if (t != 0)
		               c[t - 1] = t;
		         }
		      }
		      int nused = 0; 
		      for (int t : c) 
		      {
		         if (t != 0)
		            nused++;
		      }
		      int c1[] = new int[nused];
		      nused = 0;
		      for (int t : c) 
		      {
		         if (t != 0)
		            c1[nused++] = t;
		      }
		      return c1;
		   }
}
