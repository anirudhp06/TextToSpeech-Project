import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Synthesizer;

public class TextToSpeech{
    private Synthesizer tts;
    public TextToSpeech() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            tts = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            tts.allocate();
        } catch (Exception e) {
            System.out.println("Error occured during text to speech");
            e.printStackTrace();
        }
    }

    public void speak(String message) {
        try {
            tts.resume();
            tts.speakPlainText(message, null);
            tts.waitEngineState(tts.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deallocate(){
        try{
            tts.deallocate();
        }catch(EngineException | EngineStateError e){
            e.printStackTrace();
        }
    }
}