package com.example.multisudoku;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;
public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); 
		bar.setNumStars(5);
		bar.setStepSize(0.5f);

		TextView t=(TextView)findViewById(R.id.textResult);

		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");

		bar.setRating(score);
		switch (score)
		{
		case 1: t.setText("You solve 1 out of 5.....");
		break;
		case 2: t.setText("You solve 2 out of 5.....");
		break;
		case 3: t.setText("You solve 3 out of 5.....");
		break;
		case 4: t.setText("You solve 4 out of 5.....");
		break;
		case 5: t.setText("Owwww!!! You solve 5 out of 5.....");
		break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
