package com.example.multisudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class StripeSudokuGame extends Activity  
{
	private static final String TAG = "Sudoku";
	
	public static final String KEY_DIFFICULTY =
		      "com.example.multisudoku.difficultystripesudoku";
	
	
	public static final int DIFFICULTYSTRIPESUDOKU_LOW = 0;
	   public static final int DIFFICULTYSTRIPESUDOKU_MEDIUM = 1;
	   public static final int DIFFICULTYSTRIPESUDOKU_HARD = 2;
	   
	   private int puzzle[] = new int[9 * 9];
	   
	   private final String stripesudokulowPuzzle =
			      "600091005503000108000340020" +
			      "400103809089400030001009050" +
			      "060907080870060903100200507";
			   private final String stripesudokumediumPuzzle =
			      "010090000420317005500000002" +
			      "000020001750000006060040070" +
			      "000203020080070000095408230";
			   private final String stripesudokuhardPuzzle =
			      "000008094800000000000000837" +
			      "300600040000070205650000003" +
			      "906000000700000006020450789";
			   
			   private PuzzleViewStripe puzzleView;
			   
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			  super.onCreate(savedInstanceState);
			  Log.d(TAG, "onCreate");

			  int diff = getIntent().getIntExtra(KEY_DIFFICULTY,
					  DIFFICULTYSTRIPESUDOKU_LOW);
			      puzzle = getPuzzle(diff);
			      calculateUsedTiles();

			      puzzleView = new PuzzleViewStripe(this);
			      setContentView(puzzleView);
			      puzzleView.requestFocus();
		}
		
		 private int[] getPuzzle(int diff) 
		   {
		      String puz;
		      switch (diff) 
		      {
		      	 case DIFFICULTYSTRIPESUDOKU_MEDIUM:
		      	 puz= stripesudokumediumPuzzle;
		         break;
		      	 case DIFFICULTYSTRIPESUDOKU_HARD:
		         puz = stripesudokuhardPuzzle;
		         break;
		      	 case DIFFICULTYSTRIPESUDOKU_LOW:
		      	 default:
		         puz = stripesudokulowPuzzle;
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
		         Dialog v = new Keypad(this, tiles, puzzleView);
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
