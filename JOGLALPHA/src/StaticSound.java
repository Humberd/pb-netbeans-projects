import java.nio.ByteBuffer;

import com.jogamp.openal.AL;
import com.jogamp.openal.ALFactory;
import com.jogamp.openal.util.ALut;


public class StaticSound {
	static AL al = ALFactory.getAL();
	static int[] buffer = new int[1];
	static int[] source = new int[1];
	static float[] sourcePos = {0.0f, 1.0f, 0.0f};
	static float[] sourceVel = {0.02f, 0.05f, 0.0f};
	static float[] listenerPos = {0.0f, 0.0f, 0.0f};
	static float[] listenerVel = {0.05f, 0.0f, 0.0f};
	static float[] listenerOri = {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f};
	
	static void init(String string) {
		ALut.alutInit();
		al.alGetError();
		 if (loadALData(string) == AL.AL_FALSE)
			 System.exit(-1);
		 
		 setListenerValues();
		 
		 Runtime runtime = Runtime.getRuntime();
		 runtime.addShutdownHook(
				 new Thread (
						 new Runnable () {
							 public void run() {
								 killALData();
							 }
						 }
				)
		); 
//		 al.alSourcePlay(source[0]);
//		 long startTime = System.currentTimeMillis();
//		 long elapsed = 0;
//		 long ticker = 0;
//		 long lastTime = 0;
//		 while (elapsed < 10000) {
//			 elapsed = System.currentTimeMillis() - startTime;
//			 if (ticker > 1000) {
//				 ticker = 0;
//				 sourcePos[0] += sourceVel[0];
//				 sourcePos[1] += sourceVel[1];
//				 sourcePos[2] += sourceVel[2];
//				 al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
//			 }
//			 ticker += System.currentTimeMillis() - lastTime;
//			 lastTime = System.currentTimeMillis();
//		 }
//		 killALData();
		 
		
	}
	
	static int loadALData(String string) {
		int[] format = new int[1];
		int[] size = new int[1];
		int[] freq = new int[1];
		int[] loop = new int[1];
		ByteBuffer[] data = new ByteBuffer[1];
		///////////
		al.alGenBuffers(1, buffer, 0);
		if (al.alGetError() != AL.AL_NO_ERROR)
			return AL.AL_FALSE;
		
		ALut.alutLoadWAVFile(string, format, data, size, freq, loop);
		al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);
		///////////
		al.alGenSources(1, source, 0);
		if (al.alGetError() != AL.AL_NO_ERROR)
			return AL.AL_FALSE;
		
		al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
		al.alSourcef(source[0], AL.AL_PITCH, 1.0f);
		al.alSourcef(source[0], AL.AL_GAIN, 0.0f);
		al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
		al.alSourcefv(source[0], AL.AL_VELOCITY, sourceVel, 0);
		al.alSourcei(source[0], AL.AL_LOOPING, AL.AL_TRUE);
		al.alSourcei(source[0], AL.AL_SOURCE_RELATIVE, AL.AL_FALSE);
		if (al.alGetError() == AL.AL_NO_ERROR)
			return AL.AL_TRUE;
		
		return AL.AL_FALSE;
	}
	
	static void setListenerValues() {
		al.alListenerfv(AL.AL_POSITION, listenerPos, 0);
		al.alListenerfv(AL.AL_VELOCITY, listenerVel, 0);
		al.alListenerfv(AL.AL_ORIENTATION, listenerOri, 0);
	}
	
	static void killALData() {
		al.alDeleteBuffers(1, buffer,0);
		al.alDeleteSources(1, source,0);
		ALut.alutExit();
	}
	static void play() {
		al.alSourcePlay(source[0]);
	}
	static void pause() {
		al.alSourcePause(source[0]);
	}
	static void stop() {
		al.alSourceStop(source[0]);
	}
	static void volume(float rtriangle) {
		float temp = rtriangle/360;
		if (temp > 1)
			temp = 1;
		else if (temp < 0)
			temp = 0;
		
		System.out.println(temp);
		al.alSourcef(source[0],AL.AL_GAIN, temp);
	}
	static void leftSpeaker() {
		listenerPos[0] +=listenerVel[0];
		if (listenerPos[0]>1)
			listenerPos[0] = 1;
		al.alListenerfv(AL.AL_POSITION, listenerPos,0);
		System.out.println(listenerPos[0]+" , "+listenerPos[1]+" , "+listenerPos[2]);
	}
	static void rightSpeaker() {
		listenerPos[0] -= listenerVel[0];
		if (listenerPos[0]<-1)
			listenerPos[0] = -1;
		al.alListenerfv(AL.AL_POSITION, listenerPos,0);
		System.out.println(listenerPos[0]+" , "+listenerPos[1]+" , "+listenerPos[2]);
	}
	static void leftSpeakerBeta(){
		sourcePos[0] -=sourceVel[0];
		sourcePos[1] = -Math.abs(sourcePos[0]) +1;
		if (sourcePos[0]<-1)
			sourcePos[0] = -1;
		al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
		System.out.println(sourcePos[0]+ " , "+sourcePos[1]+" , "+sourcePos[2]);
	}
	static void rightSpeakerBeta(){
		sourcePos[0] +=sourceVel[0];
		sourcePos[1] =-Math.abs(sourcePos[0]) +1;
		if (sourcePos[0]>1)
			sourcePos[0] = 1;
		
		al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
		System.out.println(sourcePos[0]+ " , "+sourcePos[1]+" , "+sourcePos[2]);
	}

}
