package com.mytasks.lxc.mtsort;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	static public final int SLEEPTIME = 15;

	//int[] data = new int[20010];
	final static int LENGTH = 100;
	static int[] arr1 = new int [LENGTH];
	static int[] arr2 = new int [LENGTH];
	static int[] leftArray;
	static int[] rightArray;
	public static Diagram diagramSingle;
	Button buttonSingle;
	public static Diagram diagramTow;
	Button buttonTow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		diagramSingle = (Diagram) findViewById(R.id.diagramSingle);
		buttonSingle = (Button)findViewById(R.id.startSingleThreadButton);
		diagramTow = (Diagram) findViewById(R.id.diagramTow);
		buttonTow = (Button)findViewById(R.id.startTowThreadButton);
		for (int i = 0; i < LENGTH; i++) {
			arr1[i] = (int)(Math.random()*100);
			arr2[i] = arr1[i];
		}
		diagramSingle.setData(arr1);
		diagramTow.setData(arr2);
		buttonSingle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					singleSort();
				}catch (InterruptedException e){

				}
			}
		});

		buttonTow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					towThreadMergeSort();
				}catch (InterruptedException e){

				}
			}
		});


	}
	private void singleSort() throws InterruptedException{

		Intent t = new Intent();
		t.putExtra("unsort",arr1);
		sortTask task = new sortTask();
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,t);
	}

	private static void towThreadMergeSort() throws InterruptedException{


		Intent t = new Intent();
		t.putExtra("unsort",arr2);
		TowsortTask task = new TowsortTask();
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,t);
	}
}
