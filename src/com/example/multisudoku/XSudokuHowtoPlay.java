package com.example.multisudoku;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.*;
import android.view.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.net.*;
import android.content.Intent;
import android.webkit.WebView;

public class XSudokuHowtoPlay extends Activity 
{
	WebView howtoplaywebview;
   @Override
   protected void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.xhowtoplay);
      
      howtoplaywebview = (WebView) findViewById(R.id.xhowtoplay);
      
      String text = "<html><body>" + "<p align=\"justify\" color=\"#0000FF\">"
				+ getString(R.string.xhowtoplay_text) + "</p> " + "</body></html>";
      
      howtoplaywebview.loadData(text, "text/html", "utf-8");


	
   }
}
