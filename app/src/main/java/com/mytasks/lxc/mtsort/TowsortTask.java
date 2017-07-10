package com.mytasks.lxc.mtsort;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by LLL on 2017/5/28.
 */
public class TowsortTask extends AsyncTask<Intent, Intent,Void> {
	int[] whole_arr;
	int[] arrCopy;
	boolean isFirst = true;
	oneTask task1;
	oneTask task2;
	@Override
	protected Void doInBackground(Intent... data) {
		whole_arr = data[0].getIntArrayExtra("unsort");
		arrCopy = new int[whole_arr.length];
		arrCopy = Arrays.copyOfRange(whole_arr,0,whole_arr.length);
		publishProgress(new Intent());
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){

		}

		while(task1.getStatus() != Status.FINISHED || task2.getStatus() != Status.FINISHED){

		}
		int left = 0;
		int right = whole_arr.length-1;
		int middle = (left+right)/2;
		int[] temp = new int[whole_arr.length];
		merge(whole_arr,temp,left,middle,right);
		return null;
	}

	@Override
	protected void onProgressUpdate(Intent... data) {
		if (isFirst){
			int left = 0;
			int right = whole_arr.length-1;
			int middle = (left+right)/2;
			Log.d("towThread","startChild");
			task1 = new oneTask();
			task2 = new oneTask();
			task1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,left,middle);
			task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,middle+1,right);
			/*while(task1.getStatus() != Status.FINISHED || task2.getStatus() != Status.FINISHED){

			}*/

			isFirst = false;
		}
		MainActivity.diagramTow.setData(arrCopy);
	}

	public void merge(int arr[], int temp[], int left, int middle, int right) {
		arrCopy = Arrays.copyOfRange(whole_arr,0,whole_arr.length);

		int i=left;
		int j=middle+1;
		int k=0;
		while ( i<=middle && j<=right){
			if (arr[i] <=arr[j]){
				temp[k++] = arr[i++];
				arrCopy[left+k-1] = arr[i-1];
				drawOnUI(left,right);
			}
			else{
				temp[k++] = arr[j++];
				arrCopy[left+k-1] = arr[j-1];
				drawOnUI(left,right);
			}
		}
		while (i <=middle){
			temp[k++] = arr[i++];
			arrCopy[left+k-1] = arr[i-1];
			drawOnUI(left,right);
		}
		while ( j<=right){
			temp[k++] = arr[j++];
			arrCopy[left+k-1] = arr[j-1];
			drawOnUI(left,right);
		}
		//把数据复制回原数组
		for (i=0; i<k; ++i){
			arr[left+i] = temp[i];
		}


	}

	void drawOnUI(int left,int right){
		//if (right - left > 5){
		Intent t = new Intent();
		publishProgress(t);
		try{
			Thread.sleep(MainActivity.SLEEPTIME);
		}catch (InterruptedException e){

		}
		//}
	}




	class oneTask extends AsyncTask<Integer,Intent,Void>{
		@Override
		protected Void doInBackground(Integer... range) {
			int leng = range[1]-range[0]+1;
			int[] temp = new int[leng];
			sort(whole_arr,temp,range[0],range[1]);
			return null;
		}

		@Override
		protected void onProgressUpdate(Intent... data) {
			MainActivity.diagramTow.setData(arrCopy);
		}

		public void sort(int[] intArr,int[] temp, int left, int right){
			if (left < right) {
				int middle = (left+right)/2;
				sort(intArr,temp,left,middle);
				sort(intArr,temp,middle+1,right);

				// 合并且排序
				merge(intArr, temp,left,middle,right);
			}
		}

		public void merge(int arr[], int temp[], int left, int middle, int right) {
			//arrCopy = Arrays.copyOfRange(whole_arr,0,whole_arr.length);

			int i=left;
			int j=middle+1;
			int k=0;
			while ( i<=middle && j<=right){
				if (arr[i] <=arr[j]){
					temp[k++] = arr[i++];
					arrCopy[left+k-1] = arr[i-1];
					drawOnUI(left,right);
				}
				else{
					temp[k++] = arr[j++];
					arrCopy[left+k-1] = arr[j-1];
					drawOnUI(left,right);
				}
			}
			while (i <=middle){
				temp[k++] = arr[i++];
				arrCopy[left+k-1] = arr[i-1];
				drawOnUI(left,right);
			}
			while ( j<=right){
				temp[k++] = arr[j++];
				arrCopy[left+k-1] = arr[j-1];
				drawOnUI(left,right);
			}
			//把数据复制回原数组
			for (i=0; i<k; ++i){
				arr[left+i] = temp[i];
			}


		}

		void drawOnUI(int left,int right){
			//if (right - left > 5){
				Intent t = new Intent();
				publishProgress(t);
				try{
					Thread.sleep(MainActivity.SLEEPTIME);
				}catch (InterruptedException e){

				}
			//}
		}
	}
}
