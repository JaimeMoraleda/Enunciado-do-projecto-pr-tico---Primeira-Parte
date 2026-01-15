package pt.ulusofona.lp2.greatprogrammingjourney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Test para funcionamiento de GameManager
public class TestGameManager {

    //Creo el tablero bien?
    @Test
    public void testCreateInitialBoard() {
        GameManager gm = new GameManager();
        gm.createInitialBoard(5, 5, 2);
        assertFalse(gm.gameIsOver());
    }

    //pilla la id bien ?
    @Test
    public void testCurrentPlayerId() {
        GameManager gm = new GameManager();
        gm.createInitialBoard(5, 5, 2);
        assertEquals(0, gm.getCurrentPlayerID());
    }

    //Mueve al jugador bien?
    @Test
    public void testMovePlayer() {
        GameManager gm = new GameManager();
        gm.createInitialBoard(5, 5, 2);
        boolean moved = gm.moveCurrentPlayer(1, 0);
        assertTrue(moved);
    }

    //termina el juego?
    @Test
    public void testGameNotOverInitially() {
        GameManager gm = new GameManager();
        gm.createInitialBoard(5, 5, 2);
        assertFalse(gm.gameIsOver());
    }
}
