package com.example.multisudoku;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "multisudoku";

	private static final String TABLE_QUEST = "multitable";

	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer";
	private static final String KEY_OPTA= "opta";
	private static final String KEY_OPTB= "optb";
	private static final String KEY_OPTC= "optc";
	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
		db.execSQL(sql);		
		addQuestions();
		
	}
	private void addQuestions()
	{
		Question q1=new Question("1. In 9x9, no duplicates of the numbers 1-9?",
								"horizontally", "vertically", "both", "both");
		this.addQuestion(q1);
		Question q2=new Question("2. Which sudoku variant with the aditional rule that in each of the two each number from 1 to 9 can only appear once?",
								"x sudoku", "hyper sudoku", "asterisk sudoku", "x sudoku");
		this.addQuestion(q2);
		Question q3=new Question("3. Which sudoku generate a row or column or region in the solution arranged all the numbers in order or reverse order?",
								"x sudoku", "asterisk sudoku","stripe sudoku","stripe sudoku");
		this.addQuestion(q3);
		Question q4=new Question("4. Which sudoku define four regions overlap with the nine standard regions?",	
								"center dot sudoku", "hyper sudoku", "stripe sudoku","hyper sudoku");
		this.addQuestion(q4);
		Question q5=new Question("5. Which sudoku generate central cells of each region form an extra region that must also contain the numbers 1 through 9?",
								"stripe sudoku","center dot sudoku","asterisk sudoku","center dot sudoku");
		this.addQuestion(q5);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);

		onCreate(db);
	}

	public void addQuestion(Question quest) {

		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());

		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();

		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
	
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
