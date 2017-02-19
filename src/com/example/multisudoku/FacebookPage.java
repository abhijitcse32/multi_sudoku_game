package com.example.multisudoku;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FacebookPage extends Activity 
{

	static public class MyWebViewFragment extends Fragment 
	{
		
		WebView myWebView;
		final static String myBlogAddr = "https://www.facebook.com/pages/Multi-Sudoku-Game-for-Smartphone/300894416737077";
		String myUrl;
		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.layout_webfragment, container, false);
			myWebView = (WebView)view.findViewById(R.id.mywebview);
			
			myWebView.getSettings().setJavaScriptEnabled(true);                
			myWebView.setWebViewClient(new MyWebViewClient());
			
			if(myUrl == null){
				myUrl = myBlogAddr;
			}
			myWebView.loadUrl(myUrl);
		     
	        return view;

		}
		
		private class MyWebViewClient extends WebViewClient {
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        	myUrl = url;
	            view.loadUrl(url);
	            return true;
	        }
	    }

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			setRetainInstance(true);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook);
	}

	@Override
	public void onBackPressed() {
		MyWebViewFragment fragment = 
				(MyWebViewFragment)getFragmentManager().findFragmentById(R.id.myweb_fragment);
		WebView webView = fragment.myWebView;
		
		if(webView.canGoBack()){
			webView.goBack();
		}else{
			super.onBackPressed();
		}
	}

}



