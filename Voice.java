
public class Voice {
    public static void main(String[] args) {
        TextToSpeech tts=new TextToSpeech();
        String message="Hello from another class";
        System.out.println(message);
        tts.speak(message);
        tts.deallocate();
    }
}
