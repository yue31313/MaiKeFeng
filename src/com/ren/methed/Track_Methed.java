package com.ren.methed;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class Track_Methed implements Runnable {

	// �����ݴ�
	int out_size;
	// ��������
	AudioTrack track = null;

	public void run() {

	}

	// ���ͷ���
	public void track() {
		out_size = android.media.AudioTrack.getMinBufferSize(8000,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);

		track = new AudioTrack(AudioManager.STREAM_MUSIC, 8000,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, out_size,
				AudioTrack.MODE_STREAM);
	}
}
