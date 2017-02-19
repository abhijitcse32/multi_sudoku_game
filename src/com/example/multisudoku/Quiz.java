package com.example.multisudoku;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class Quiz extends Activity {
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		DbHelper db=new DbHelper(this);
		quesList=db.getAllQuestions();
		currentQ=quesList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		butNext.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
				if(currentQ.getANSWER().equals(answer.getText()))
				{
					score++;
					Log.d("score", "Your score"+score);
				}
				if(qid<5){					
					currentQ=quesList.get(qid);
					setQuestionView();
				}else{
					Intent intent = new Intent(Quiz.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score);
					intent.putExtras(b);
					startActivity(intent);
					finish();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}
	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		qid++;
	}
}
