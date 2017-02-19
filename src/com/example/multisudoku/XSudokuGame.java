package com.example.multisudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class XSudokuGame extends Activity  
{
	private static final String TAG = "Sudoku";
	
	public static final String KEY_DIFFICULTY_X =
		      "com.example.multisudoku.difficultyxsudoku";
	
	
	public static final int DIFFICULTYXSUDOKU_LOW = 0;
	   public static final int DIFFICULTYXSUDOKU_MEDIUM = 1;
	   public static final int DIFFICULTYXSUDOKU_HARD = 2;
	   
	   private int puzzle[] = new int[9 * 9];
	   
	   private final String xsudokulowPuzzle =
			      "103700406450120080709050120" +
			      "030807064064231000807000200" +
			      "012078045605302070008040300";
			   private final String xsudokumediumPuzzle =
			      "089004000400860259015020300" +
			      "906102084070000900304080010" +
			      "040605000503008401000040000";
			   private final String xsudokuhardPuzzle =
			      "703000000020053014105040500" +
			      "000006089030007160602090053" +
			      "057004000000070208000000510";
			   
			   private PuzzleViewX puzzleView;
			   
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			  super.onCreate(savedInstanceState);
			  Log.d(TAG, "onCreate");

			  int diff = getIntent().getIntExtra(KEY_DIFFICULTY_X,
					  DIFFICULTYXSUDOKU_LOW);
			      puzzle = getPuzzle(diff);
			      calculateUsedTiles();

			      puzzleView = new PuzzleViewX(this);
			      setContentView(puzzleView);
			      puzzleView.requestFocus();
		}
		
		 private int[] getPuzzle(int diff) 
		   {
		      String puz;
		      switch (diff) 
		      {
		      	 case DIFFICULTYXSUDOKU_MEDIUM:
		      	 puz= xsudokumediumPuzzle;
		         break;
		      	 case DIFFICULTYXSUDOKU_HARD:
		         puz = xsudokuhardPuzzle;
		         break;
		      	 case DIFFICULTYXSUDOKU_LOW:
		      	 default:
		         puz = xsudokulowPuzzle;
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
		         Dialog v = new KeypadX(this, tiles, puzzleView);
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
