package music.com.br.musicgame;

import org.junit.Test;

import music.com.br.musicgame.entities.Note;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class NoteTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Note note = new Note(Note.C);

        assertEquals(note.toString(), "C");

        assertEquals(note.adicionarSemitons(2).toString(), "D");
        assertEquals(note.adicionarSemitons(4).toString(), "E");
        assertEquals(note.adicionarSemitons(5).toString(), "F");
        assertEquals(note.adicionarSemitons(7).toString(), "G");
        assertEquals(note.adicionarSemitons(9).toString(), "A");
        assertEquals(note.adicionarSemitons(11).toString(), "B");
        assertEquals(note.adicionarSemitons(12).toString(), "C");


        assertEquals(note.terca().toString(), "E");
        assertEquals(note.quarta().toString(), "F");
        assertEquals(note.quinta().toString(), "G");

    }

    @Test
    public void notation_for_forths_and_third(){
        Note note = new Note(Note.D);
        assertEquals(note.toString(),"D");
        assertEquals(note.terca().toString(), "F#");

        note = new Note(Note.F);
        assertEquals(note.toString(), "F");
        assertEquals(note.quarta().toString(),"Bb");



    }

    @Test
    public void NoteEquals(){
        for(int i = 0; i < 12; i++)
            for(int j = 0; j < 3; j ++){
                assertEquals(new Note(i), new Note(12*j+i));
                assertEquals(new Note(12*j+i), new Note(i));
            }

    }
}