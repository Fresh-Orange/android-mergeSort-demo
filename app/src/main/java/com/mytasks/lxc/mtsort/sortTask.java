package com.mytasks.lxc.mtsort;

import android.content.Intent;
import android.os.AsyncTask;

import java.util.Arrays;

/**
 * Created by LLL on 2017/5/28.
 */
public class sortTask extends AsyncTask<Intent, Intent,Void> {
	int[] whole_arr;
	int[] arrCopy;
	@Override
	protected Void doInBackground(Intent... data) {
		whole_arr = data[0].getIntArrayExtra("unsort");
		int[] temp = new int[whole_arr.length];
		arrCopy = new int[whole_arr.length];
		arrCopy = Arrays.copyOfRange(whole_arr,0,whole_arr.length);
		sort(whole_arr,temp,0,whole_arr.length-1);
		return null;
	}

	@Override
	protected void onProgressUpdate(Intent... data) {

		MainActivity.diagramSingle.setData(arrCopy);
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
		//if (right - left > 1){
			Intent t = new Intent();
			this.publishProgress(t);
			try{
				Thread.sleep(MainActivity.SLEEPTIME);
			}catch (InterruptedException e){

			}
		//}
	}
}
