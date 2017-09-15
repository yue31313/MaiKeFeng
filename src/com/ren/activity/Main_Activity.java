package com.ren.activity;

import com.ren.methed.Run_Methed;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Main_Activity extends Activity {

	// ��˷翪��ͼƬButton
	private ImageButton state;
	Run_Methed run_Metned = new Run_Methed();
	Thread thread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// �󶨿ؼ�
		this.state = (ImageButton) findViewById(R.id.state);
		this.state.setOnClickListener(new ocl());
	}

	// ��¼��˷�״̬�Ĳ���ֵ
	private boolean is = false;

	// ������
	class ocl implements OnClickListener {

		@Override
		public void onClick(View v) {

			if (v.getId() == R.id.state && is == true) {
				// �л�ͼƬ��ť�ı���ͼ���������������������ء�
				state.setImageDrawable(getResources().getDrawable(
						R.drawable.close));
				run_Metned.no();

				// �ı�״̬
				is = false;
			} else if (v.getId() == R.id.state && is == false) {
				// �л�ͼƬ��ť�ı���ͼ���ء�������������������
				state.setImageDrawable(getResources().getDrawable(
						R.drawable.open));
				// �ı�״̬
				try {
					Thread thread = new Thread(run_Metned);

					thread.start();
				} catch (Exception e) {

				}
				is = true;
			}
		}
	}

	int i = 0;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			i++;
			if (i == 3) {
				finish();
			}

		}
		return true;
	}
}
