package com.ren.methed;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

public class Run_Methed implements Runnable {
	// �Ƿ�¼�ŵı��
	boolean isRecording = false;
	static final int frequency = 44100;
	@SuppressWarnings("deprecation")
	static final int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	// ����¼������������С
	int recBufSize, playBufSize;
	// ʵ����¼������
	AudioRecord audioRecord;
	// ʵ�������Ŷ���
	AudioTrack audioTrack;

	public Run_Methed() {
		// ����getMinBufferSize�������¼������С����ռ�
		recBufSize = AudioRecord.getMinBufferSize(frequency,
				channelConfiguration, audioEncoding);

		// ����getMinBufferSize������÷�����С�Ļ�������С
		playBufSize = AudioTrack.getMinBufferSize(frequency,
				channelConfiguration, audioEncoding);
		// -----------------------------------------��
		// ���ù��캯��ʵ����¼������
		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency,
				channelConfiguration, audioEncoding, recBufSize);

		// ���ù��캯��ʵ������������
		audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency,
				channelConfiguration, audioEncoding, playBufSize,
				AudioTrack.MODE_STREAM);
	}

	// �߳���
	public void run() {

		try {
			byte[] buffer = new byte[recBufSize];
			audioRecord.startRecording();// ��ʼ¼��
			audioTrack.play();// ��ʼ����
			this.isRecording = true;
			while (isRecording) {
				// ��MIC�������ݵ�������
				int bufferReadResult = audioRecord.read(buffer, 0, recBufSize);

				byte[] tmpBuf = new byte[bufferReadResult];
				System.arraycopy(buffer, 0, tmpBuf, 0, bufferReadResult);
				// д�����ݾͲ���
				audioTrack.write(tmpBuf, 0, tmpBuf.length);
			}
			audioTrack.stop();
			audioRecord.stop();
		} catch (Throwable t) {
		}
	}

	// ֹͣ����
	public void no() {
		isRecording = false;
	}

}
