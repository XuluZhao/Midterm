package com.deitel.midterm;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import java.text.NumberFormat; 

import android.text.Editable; 
import android.text.TextWatcher; 
import android.widget.EditText; 
import android.widget.SeekBar; 
import android.widget.SeekBar.OnSeekBarChangeListener; 
import android.widget.TextView; 
import android.widget.ImageView;
import android.content.Intent;



public class MainActivity extends Activity {
	
	private static final NumberFormat currencyFormat = 
			NumberFormat.getCurrencyInstance();
	private static final NumberFormat integerFormat =
			NumberFormat.getIntegerInstance();
	
	private double distance=0;
	private double mpg=25.0;
	private double price=2.5;
	private double cost = 0.0;
	
	private TextView distanceDisplay;
	private TextView costDisplay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		distanceDisplay = (TextView) findViewById(R.id.distanceDisplay);
		costDisplay = (TextView) findViewById(R.id.costDisplay);
		
		costDisplay.setText(currencyFormat.format(cost));
		
		updateInfo();
		
		EditText distanceEdit = (EditText) findViewById(R.id.distanceEdit);
		distanceEdit.addTextChangedListener(distanceEditWatcher);
		
		SeekBar mpgSeekBar = 
		         (SeekBar) findViewById(R.id.mpgSeekBar);
		mpgSeekBar.setOnSeekBarChangeListener(mpgSeekBarListener);
		
		SeekBar gasPriceSeekBar = 
		         (SeekBar) findViewById(R.id.gasPriceSeekBar);
		gasPriceSeekBar.setOnSeekBarChangeListener(gasPriceSeekBarListener);
		
		ImageView camaroImageView = (ImageView) findViewById(R.id.camaroImageView);
		camaroImageView.setOnClickListener(camaroImageViewListener);
	}
	
	private void updateInfo(){
		cost = (price/mpg)*distance;
		costDisplay.setText(currencyFormat.format(cost));
	}
	
	public OnClickListener camaroImageViewListener = new OnClickListener() 
	{
		@Override
		public void onClick(View v){
				String urlString = "http://en.wikipedia.org/wiki/Chevrolet_Camaro";
				Intent webIntent = new Intent(Intent.ACTION_VIEW, 
					Uri.parse(urlString));
			
			startActivity(webIntent);
		}
	};
	private OnSeekBarChangeListener mpgSeekBarListener = 
		      new OnSeekBarChangeListener() 
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) 
		{
			mpg = progress;
			updateInfo(); 
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		}
	};
	
	private OnSeekBarChangeListener gasPriceSeekBarListener = 
		      new OnSeekBarChangeListener() 
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) 
		{
			price = progress;
			updateInfo(); 
		}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) 
		{
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) 
		{
		}
	};
	private TextWatcher distanceEditWatcher = new TextWatcher() 
	{
		@Override
		public void onTextChanged(CharSequence s, int start, 
				int before, int count) 
		{         
			try
			{
	            distance = Double.parseDouble(s.toString());
	            }
			catch (NumberFormatException e)
			{
	            distance = 0.0; 
	            }

	         distanceDisplay.setText(integerFormat.format(distance));
	         updateInfo(); 
	    } 

	      @Override
	      public void afterTextChanged(Editable s) 
	      {
	      } 

	      @Override
	      public void beforeTextChanged(CharSequence s, int start, int count,
	         int after) 
	      {
	      }
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
