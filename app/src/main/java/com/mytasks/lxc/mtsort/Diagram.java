package com.mytasks.lxc.mtsort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LLL on 2017/5/28.
 */
public class Diagram extends View {
	Paint pen = new Paint();
	int[] data = new int[110];
	public Diagram(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		pen.setColor(Color.BLUE);
		pen.setStrokeWidth(6);
		for (int i = 0; i < 100; i++){
			data[i] = 90;
		}

	}

	public void setData(int[] _data) {
		int cnt = 0;
		if (_data.length <= 100) {
			for(int i = 0;i<_data.length;i++)
				data[i] = _data[i];
		}
		else{
			for (int i = 0; i < _data.length && cnt < 100; i += _data.length/100){
				data[cnt++] = _data[i];
			}
		}
		normalize(data);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int x = 10;
		int i = 0;
		while(i < 100){
			canvas.drawLine(x,550-data[i],x,550,pen);
			i++;
			x += 10;
		}
	}

	//把数组转换成在0到500的范围内
	private void normalize(int[] _data){
		int max_data = -9999;
		for (int i = 0; i < 100; i++){
			max_data = _data[i] > max_data ? _data[i] :max_data;
		}

		for (int i = 0; i < 100; i++){
			_data[i] = _data[i]*500/max_data;
		}
	}
}
