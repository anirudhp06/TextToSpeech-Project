import javax.speech.Engine;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import java.util.Scanner;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Synthesizer;

public class TextToSpeech {
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
    public static void main(String[] args) {
        System.out.println("Hello from testing");
        TextToSpeech tts=new TextToSpeech();
        System.out.println("Welcome to text to speech:");
        Scanner scan=new Scanner(System.in);
        while(true){
            System.out.print("Enter word/line to make pc speak it:");
            String msg=scan.nextLine();
            System.out.println("You entered:"+msg);
            tts.speak(msg);
            if(msg.toLowerCase().equals("exit")){
                tts.speak("Bye admin");
                System.out.println("Bubyee!!");
                break;
            }
        }
        scan.close();
        tts.deallocate();
    }
}