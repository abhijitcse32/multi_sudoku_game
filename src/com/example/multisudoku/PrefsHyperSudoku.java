package com.example.multisudoku;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsHyperSudoku extends PreferenceActivity 
{
   @Override
   protected void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.layout.setting);
   }
}