package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.List;
//Administra--> Tablero, jugadores y logica
public class GameManager {

    //tablero
    private Board board;
    //Lista jugadore
    private List<Player> players;
    //ID jugador
    private int currentPlayerIndex;
    //Partida terminada?
    private boolean gameOver;
    //Resultado
    private GameResult gameResult;

    //Constructor
    public GameManager() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        gameOver = false;
        gameResult = null;
    }

    //Tablero, Jugadores e Inicio
    public void createInitialBoard(int rows, int columns, int playerCount) {
        board = new Board(rows, columns);
        players.clear();

        //crea jugadores y los pone en el inicio
        for (int i = 0; i < playerCount; i++) {
            Programmer programmer = new Programmer(i, "Programmer " + i, 1);
            Player player = new Player(i, programmer);
            players.add(player);
            board.getSlot(0, i).setProgrammer(programmer);
        }

        currentPlayerIndex = 0;
        gameOver = false;
        gameResult = null;
    }

    public int getCurrentPlayerID() {
        return players.get(currentPlayerIndex).getId();
    }

    //Mueve jugador
    public boolean moveCurrentPlayer(int row, int column) {
        if (gameOver) {
            return false;
        }

        Player player = players.get(currentPlayerIndex);
        Slot targetSlot = board.getSlot(row, column);

        if (!targetSlot.isEmpty()) {
            return false;
        }

        Slot currentSlot = board.getSlot(player.getCurrentRow(), player.getCurrentColumn());
        currentSlot.setProgrammer(null);

        targetSlot.setProgrammer(player.getProgrammer());
        player.moveTo(row, column);

        //Ha llegado a la ultima casilla?
        if (row == board.getRows() - 1 && column == board.getColumns() - 1) {
            gameOver = true;
            gameResult = new GameResult(player.getId(), "Winner reached the final slot");
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return true;
    }

    public boolean gameIsOver() {
        return gameOver;
    }

    public GameResult getGameResults() {
        return gameResult;
    }

    public Slot getSlotInfo(int row, int column) {
        return board.getSlot(row, column);
    }

    public Programmer getProgrammerInfo(int playerId) {
        return players.get(playerId).getProgrammer();
    }

    public String getProgrammerInfoAsStr(int playerId) {
        Programmer p = players.get(playerId).getProgrammer();
        return p.getName() + " (Skill " + p.getSkillLevel() + ")";
    }

    public String getImagePng(int playerId) {
        return "player" + playerId + ".png";
    }

    public String getAuthorsPanel() {
        return "Great Programming Journey\nAuthors: TBD";
    }
}
