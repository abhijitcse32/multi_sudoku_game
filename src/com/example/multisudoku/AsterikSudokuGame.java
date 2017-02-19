package com.example.multisudoku;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class AsterikSudokuGame extends Activity  
{
	private static final String TAG = "Sudoku";
	
	public static final String KEY_DIFFICULTY_ASTERIK =
		      "com.example.multisudoku.difficultyasteriksudoku";
	
	
	public static final int DIFFICULTYASTERIKSUDOKU_LOW = 0;
	   public static final int DIFFICULTYASTERIKSUDOKU_MEDIUM = 1;
	   public static final int DIFFICULTYASTERIKSUDOKU_HARD = 2;
	   
	   private int puzzle[] = new int[9 * 9];
	   
	   private final String asteriksudokulowPuzzle =
			      "008601053300840201010723004" +
			      "952004060030950120701060049" +
			      "097030802060209470105080906";
			   private final String asteriksudokumediumPuzzle =
			      "901020700058000602060708030" +
			      "006090007800302400520040010" +
			      "002903006090200104105007090";
			   private final String asteriksudokuhardPuzzle =
			      "010040060309006100080300002" +
			      "100070030003008700060100004" +
			      "800061000004000021030900400";
			   
			   private PuzzleViewAsterik puzzleView;
			   
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			  super.onCreate(savedInstanceState);
			  Log.d(TAG, "onCreate");

			  int diff = getIntent().getIntExtra(KEY_DIFFICULTY_ASTERIK,
					  DIFFICULTYASTERIKSUDOKU_LOW);
			      puzzle = getPuzzle(diff);
			      calculateUsedTiles();

			      puzzleView = new PuzzleViewAsterik(this);
			      setContentView(puzzleView);
			      puzzleView.requestFocus();
		}
		
		 private int[] getPuzzle(int diff) 
		   {
		      String puz;
		      switch (diff) 
		      {
		      	 case DIFFICULTYASTERIKSUDOKU_MEDIUM:
		      	 puz= asteriksudokumediumPuzzle;
		         break;
		      	 case DIFFICULTYASTERIKSUDOKU_HARD:
		         puz = asteriksudokuhardPuzzle;
		         break;
		      	 case DIFFICULTYASTERIKSUDOKU_LOW:
		      	 default:
		         puz = asteriksudokulowPuzzle;
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
		         Dialog v = new KeypadAsterik(this, tiles, puzzleView);
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
