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

    public static String[] characters = {"C","D","E","F","G","A","B"};

    int note;

    boolean notacaoSustenida = true;

    public Note(int note){
        this.note = note % 12;
    }

    public Note terca(){
        return adicionarSemitons(increments[2]);
    }

    public Note quarta(){
        int pos = characterPosition(getCharacter());
        pos +=3;
        pos %= 8;

        Note q = adicionarSemitons(increments[3]);

        if(!q.getCharacter().equals(characters[pos])){
            q.notacaoSustenida = false;
        }

        return q;

    }

    public Note quinta(){
        return adicionarSemitons(increments[4]);
    }

    public String getCharacter(){
        return toString().substring(0,1);
    }

    private int characterPosition(String character){
        int pos = 0;

        while(!character.equals(characters[pos])){
            pos++;
        }

        return pos;
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
                return notacaoSustenida?"C#":"Db";
            case 2:
                return "D";
            case 3:
                return notacaoSustenida?"D#":"Eb";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return notacaoSustenida?"F#":"Gb";
            case 7:
                return "G";
            case 8:
                return notacaoSustenida?"G#":"Ab";
            case 9:
                return "A";
            case 10:
                return notacaoSustenida?"A#":"Bb";
            case 11:
                return "B";
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }

        if(o == this){
            return true;
        }

        if(!(o instanceof Note)){
            return false;
        }

        Note noteO = (Note) o;

        return this.note ==  noteO.note;
    }
}
