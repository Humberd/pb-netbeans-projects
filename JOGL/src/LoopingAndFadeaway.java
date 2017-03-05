import java.nio.ByteBuffer;
  import com.jogamp.openal.AL;
  import com.jogamp.openal.ALC;
  import com.jogamp.openal.ALFactory;
  import com.jogamp.openal.util.ALut;


  public class LoopingAndFadeaway {
  	static int[] buffer = new int[1];
  	static int[] source = new int[1];
  	static float[] sourcePos = { 0.0f, 0.0f, 0.0f };
  	static float[] sourceVel = { 0.0f, 0.0f, 10.0f };
  	static float[] listenerPos = { 0.0f, 0.0f, 0.0f };
  	static float[] listenerVel = { 0.0f, 0.0f, 0.0f };
  	static float[] listenerOri = { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f };
  	static AL al;
  	static ALC alc;


    static int loadALData() {
        if (al.alGetError() != AL.AL_NO_ERROR) {
            return AL.AL_FALSE;
        }
        int[] format = new int[1];
        int[] size = new int[1];
        ByteBuffer[] data = new ByteBuffer[1];
        int[] freq = new int[1];
        int[] loop = new int[1];


        // Load wav data into a buffer.
   	
        al.alGenBuffers(1, buffer, 0);
        if (al.alGetError() != AL.AL_NO_ERROR)
            return AL.AL_FALSE;
        ALut.alutLoadWAVFile(
            "Pokemon.wav",
            format,
            data,
            size,
            freq,
            loop);
        al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);


        al.alGenSources(1, source, 0);
        al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
        al.alSourcef(source[0], AL.AL_PITCH, 1.0f);
        al.alSourcef(source[0], AL.AL_GAIN, 1.0f);
        al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
        al.alSourcefv(source[0], AL.AL_POSITION, sourceVel, 0);
        al.alSourcei(source[0], AL.AL_LOOPING, AL.AL_TRUE);
        if (al.alGetError() != AL.AL_NO_ERROR) {
            return AL.AL_FALSE;
        }
        return AL.AL_TRUE;
    }


    static void setListenerValues() {
        al.alListenerfv(AL.AL_POSITION,	listenerPos, 0);
        al.alListenerfv(AL.AL_VELOCITY,    listenerVel, 0);
        al.alListenerfv(AL.AL_ORIENTATION, listenerOri, 0);
    }
	
    static void killALData() {
        al.alDeleteBuffers(1, buffer, 0);
        al.alDeleteSources(1, source, 0);
        ALut.alutExit();
    }


    public static void main(String[] args) {
        ALut.alutInit();
        al = ALFactory.getAL();

        if(loadALData() == AL.AL_FALSE) {
            System.exit(1);
        }; 
         setListenerValues();
        al.alSourcePlay(source[0]);
        long startTime = System.currentTimeMillis();
        long elapsed = 0;
        long ticker = 0;
        long lastTime = 0;
        while (elapsed < 10000) {
            elapsed = System.currentTimeMillis() - startTime;            
            if (ticker > 10) {
                ticker = 0;
                sourcePos[0] += sourceVel[0];
                sourcePos[1] += sourceVel[1];
                sourcePos[2] += sourceVel[2];
                al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
            }
            ticker += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis(); 
        }
        killALData();
    }
}