package com.example.app;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.example.cmd.Word;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import javafx.scene.control.Alert;

// phát âm
public class Speaker extends Thread{
    private static final String API_KEY = "e087180b714c4af48d7b30dbb9200f06";
    private static final String AUDIO_PATH = String.valueOf(Main.class.getResource("media/audio.wav"));

    public static String language = "en-gb";
    public static String Name = "Linda";

    private static volatile boolean haveInternet = true;

    private static String word;

    public static void speakWord(String w) throws Exception {
        word = w;
        Thread thread = new Speaker();
        thread.start() ;
        Thread.currentThread().sleep(50);
        if(haveInternet == false){
            new ShowInformationAlert("ERROR","Lỗi kết nối mạng !");
        }
    }

    private static void playAudio(byte[] audioData) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000); // Wait for audio to finish playing
        clip.close();
        audioInputStream.close();
    }

    @Override
    public void run(){
        try {
            haveInternet = true;
            VoiceProvider tts = new VoiceProvider(API_KEY);

            VoiceParameters params = new VoiceParameters(word, Languages.English_UnitedStates);
            params.setCodec(AudioCodec.WAV);
            params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
            params.setLanguage(language);
            params.setVoice(Name);
            params.setBase64(false);
            params.setSSML(false);
            params.setRate(0);

            byte[] voice = tts.speech(params);

            // Play the voice
            playAudio(voice);

            FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
            fos.write(voice, 0, voice.length);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            haveInternet = false;
        }
    }
}