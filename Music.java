import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.Serializable;

public class Music {
    public void PlayMusic(String location){
        try
        {
            File musicPath = new File(location);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e){
            System.getLogger(String.valueOf(e));
        }

    }
}
