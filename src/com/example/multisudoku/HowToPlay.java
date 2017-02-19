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

public class HowToPlay extends Activity 
{
	WebView howtoplaywebview;
   @Override
   protected void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.howtoplay);
      
      howtoplaywebview = (WebView) findViewById(R.id.howtoplaydescribe);
      
      String text = "<html><body>" + "<p align=\"justify\" color=\"#0000FF\">"
				+ getString(R.string.howtoplay_text) + "</p> " + "</body></html>";
      
      howtoplaywebview.loadData(text, "text/html", "utf-8");


	
   }
}
