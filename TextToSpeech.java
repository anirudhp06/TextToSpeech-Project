import javax.speech.Engine;
import java.util.Locale;
import javax.speech.Central;
import java.util.Scanner;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Synthesizer;

public class TextToSpeech {
    public static void main(String[] args) {
        System.out.println("Hello from testing");
        try {
            // Set Property as kevin directory
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

            // Create Synthesizer
            Synthesizer synthesize = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesize.allocate();
            Scanner scan = new Scanner(System.in);
            synthesize.resume();
            while (true) {
                System.out.println("Enter word/line to convert to speech:");
                String str = scan.nextLine();
                System.out.println("You typed:" + str);
                synthesize.speakPlainText(str, null);
                synthesize.waitEngineState(synthesize.QUEUE_EMPTY);
                if (str.toLowerCase().equals("exit")) {
                    synthesize.speakPlainText("Bye Bye admin", null);
                    synthesize.waitEngineState(synthesize.QUEUE_EMPTY);
                    break;
                }
            }
            scan.close();
            synthesize.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}