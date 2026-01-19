package pt.ulusofona.lp2.greatprogrammingjourney;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Administra tablero, jugadores y lógica del juego
public class GameManager {

    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean gameOver;
    private GameResult gameResult;

    public GameManager() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        gameOver = false;
        gameResult = null;
    }

    public void createInitialBoard(int rows, int columns, int playerCount) {
        board = new Board(rows, columns);
        players.clear();

        for (int i = 0; i < playerCount; i++) {
            Programmer programmer = new Programmer(i, "Programmer " + i, 1);
            Player player = new Player(i, programmer);
            players.add(player);
        }

        // Colocar un abismo y una herramienta de ejemplo
        if (rows > 1 && columns > 1) {
            board.placeElement(1, 1, new Abyss("Basic Abyss"));
        }

        if (rows > 2 && columns > 2) {
            board.placeElement(2, 2, new SkillTool());
        }

        currentPlayerIndex = 0;
        gameOver = false;
        gameResult = null;
    }


    public int getCurrentPlayerID() {
        return players.get(currentPlayerIndex).getId();
    }

    public boolean moveCurrentPlayer(int row, int column) {
        if (gameOver) {
            return false;
        }

        Player player = players.get(currentPlayerIndex);

        if (!player.isAlive()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            return false;
        }

        player.moveTo(row, column);

        // Reaccionar a abismo o herramienta (polimorfismo)
        reactToAbyssOrTool(player);

        if (row == board.getRows() - 1 && column == board.getColumns() - 1) {
            gameOver = true;
            gameResult = new GameResult(player.getId(), "Winner reached the final slot");
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return true;
    }

    public void saveGame(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        // Guardar tamaño del tablero
        writer.write(board.getRows() + "," + board.getColumns());
        writer.newLine();

        // Guardar jugador actual
        writer.write(String.valueOf(currentPlayerIndex));
        writer.newLine();

        // Guardar jugadores
        writer.write(String.valueOf(players.size()));
        writer.newLine();

        for (Player player : players) {
            Programmer p = player.getProgrammer();
            writer.write(
                    player.getId() + "," +
                            player.getCurrentRow() + "," +
                            player.getCurrentColumn() + "," +
                            p.getName() + "," +
                            p.getSkillLevel()
            );
            writer.newLine();
        }

        writer.close();
    }
    public void loadGame(String filename) throws InvalidFileException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            // Leer tablero
            String[] boardData = reader.readLine().split(",");
            int rows = Integer.parseInt(boardData[0]);
            int columns = Integer.parseInt(boardData[1]);

            board = new Board(rows, columns);
            players.clear();

            // Leer jugador actual
            currentPlayerIndex = Integer.parseInt(reader.readLine());

            // Leer número de jugadores
            int playerCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < playerCount; i++) {
                String[] data = reader.readLine().split(",");

                int id = Integer.parseInt(data[0]);
                int row = Integer.parseInt(data[1]);
                int column = Integer.parseInt(data[2]);
                String name = data[3];
                int skill = Integer.parseInt(data[4]);

                Programmer programmer = new Programmer(id, name, skill);
                Player player = new Player(id, programmer);
                player.moveTo(row, column);

                players.add(player);
                board.getSlot(row, column).setElement(programmer);
            }

            gameOver = false;
            gameResult = null;

            reader.close();

        } catch (Exception e) {
            throw new InvalidFileException("Invalid save file");
        }
    }

    public void reactToAbyssOrTool(Player player) {
        Slot slot = board.getSlot(player.getCurrentRow(), player.getCurrentColumn());

        if (slot == null) {
            return;
        }

        BoardElement element = slot.getElement();

        if (element != null) {
            element.react(this, player);
        }
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
        return "The Great Programming Journey\nAuthors: TBD";
    }

    public Board getBoard() {
        return board;
    }
}
