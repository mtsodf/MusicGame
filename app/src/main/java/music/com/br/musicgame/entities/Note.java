package music.com.br.musicgame.entities;

/**
 * Created by Mateus on 31/01/2016.
 */
public class Note {

    public static final int C = 0;
    public static int Csus = 1;
    public static int Db = 1;
    public static int D= 2;
    public static final int Dsus =3;
    public static final int Eb = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int Fsus = 7;
    public static final int Gb = 7;
    public static final int G = 7;
    public static final int Gsus = 8;
    public static final int A = 9;
    public static final int Asus = 10;
    public static final int Bb = 7;

    public static int[] increments = {0,2,4,5,7,9,11,12};

    int note;

    public Note(int note){
        this.note = note % 12;
    }


    public Note terca(){
        return adicionarSemitons(increments[2]);
    }

    public Note quarta(){
        return adicionarSemitons(increments[3]);
    }

    public Note quinta(){
        return adicionarSemitons(increments[4]);
    }

    public Note adicionarSemitons(int qtdSemitons) {
        return new Note((this.note + qtdSemitons) % 12);
    }

    @Override
    public String toString() {
        switch (note) {
            case 0:
                return "C";
            case 1:
                return "C#";
            case 2:
                return "D";
            case 3:
                return "D#";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "F#";
            case 7:
                return "G";
            case 8:
                return "G#";
            case 9:
                return "A";
            case 10:
                return "A#";
            case 11:
                return "B";
        }
        return null;
    }
}
