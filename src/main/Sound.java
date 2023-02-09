package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound () {
        soundURL[0] = getClass().getResource("/res/sound/music.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/drink.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/sound/receivedamage.wav");
        soundURL[6] = getClass().getResource("/res/sound/swing.wav");
        soundURL[7] = getClass().getResource("/res/sound/levelup.wav");
        soundURL[8] = getClass().getResource("/res/sound/cursor.wav");
//        soundURL[9] = getClass().getResource("/res/sound/fireball.wav");
//        soundURL[10] = getClass().getResource("/res/sound/cuttree.wav");
    }

    public void setFile (int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play () {

        clip.start();
    }

    public void loop () {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop () {

        clip.stop();
    }

    public void checkVolume () {

        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
    }
}
