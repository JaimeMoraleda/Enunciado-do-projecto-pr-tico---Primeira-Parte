package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    private Board board;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private boolean gameOver;
    private ArrayList<String> gameResults;
    private boolean someoneMovedThisRound;


    public GameManager() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        gameOver = false;
        gameResults = new ArrayList<>();
    }

    // ===================== CREATE BOARD =====================

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {
        return createInitialBoard(playerInfo, worldSize, null);
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize, String[][] abyssesAndTools) {

        board = new Board(worldSize);
        players.clear();
        currentPlayerIndex = 0;
        gameOver = false;
        gameResults.clear();
        someoneMovedThisRound = false;


        for (int i = 0; i < playerInfo.length; i++) {
            int id = Integer.parseInt(playerInfo[i][0]);
            String name = playerInfo[i][1];

            Programmer programmer = new Programmer(id, name, 1);
            Player player = new Player(id, programmer);
            players.add(player);

            board.getSlot(0).setElement(programmer);
        }

        if (abyssesAndTools != null) {
            for (String[] entry : abyssesAndTools) {
                int position = Integer.parseInt(entry[0]);
                int id = Integer.parseInt(entry[1]);

                BoardElement element = createElementById(id);
                if (element != null) {
                    board.getSlot(position).setElement(element);
                }
            }
        }

        return true;
    }

    // ===================== FACTORY =====================

    private BoardElement createElementById(int id) {

        if (id == 20) {
            return new LLMAbyss();
        }

        if (id == 8) {
            return new InfiniteLoopAbyss();
        }

        if (id >= 0 && id <= 9) {
            return new Abyss("Abyss " + id);
        }

        if (id == 5) {
            return new ProfessorHelpTool();
        }

        if (id >= 0 && id <= 5) {
            return new SkillTool();
        }

        return null;
    }

    // ===================== MOVEMENT =====================

    public int getCurrentPlayerID() {
        return players.get(currentPlayerIndex).getId();
    }

    public boolean moveCurrentPlayer(int nrSpaces) {

        if (gameOver) return false;

        Player player = players.get(currentPlayerIndex);
        // fin de ronda -> verificar empate por bloqueo
        if (currentPlayerIndex == players.size() - 1) {

            if (!someoneMovedThisRound) {
                gameOver = true;
                gameResults.clear();

                for (Player p : players) {
                    gameResults.add("Draw - " + p.getProgrammer().getName()
                            + " at position " + p.getCurrentPosition());
                }
                return true;
            }

            // reset para próxima ronda
            someoneMovedThisRound = false;
        }

        if (!player.isAlive()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            return true;
        }

        int oldPos = player.getCurrentPosition();
        int newPos = oldPos + nrSpaces;

        if (newPos >= board.getSize()) {
            newPos = board.getSize() - 1;
        }

        Slot oldSlot = board.getSlot(oldPos);
        if (oldSlot != null) oldSlot.setElement(null);

        player.moveTo(newPos, nrSpaces);
        if (newPos != oldPos) {
            someoneMovedThisRound = true;
        }


        reactToAbyssOrTool();

        Slot newSlot = board.getSlot(player.getCurrentPosition());
        if (newSlot != null && newSlot.isEmpty()) {
            newSlot.setElement(player.getProgrammer());
        }

        if (player.getCurrentPosition() == board.getSize() - 1) {
            gameOver = true;
            gameResults.clear();
            gameResults.add("Winner: " + player.getProgrammer().getName()
                    + " at position " + player.getCurrentPosition());
        }

        boolean anyoneAlive = false;
        for (Player p : players) {
            if (p.isAlive()) {
                anyoneAlive = true;
                break;
            }
        }

        if (!anyoneAlive) {
            gameOver = true;
            gameResults.clear();
            for (Player p : players) {
                gameResults.add("Draw - " + p.getProgrammer().getName()
                        + " at position " + p.getCurrentPosition());
            }
            return true;
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return true;
    }

    // ===================== REACTION =====================

    public String reactToAbyssOrTool() {

        Player player = players.get(currentPlayerIndex);

        if (!player.isAlive()) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            return "";
        }

        Slot slot = board.getSlot(player.getCurrentPosition());

        if (slot == null || slot.getElement() == null) {
            return "";
        }

        BoardElement element = slot.getElement();
        element.react(this, player);
        return element.getType();
    }

    // ===================== INFO =====================

    public String[] getSlotInfo(int position) {

        Slot slot = board.getSlot(position);
        if (slot == null || slot.getElement() == null) {
            return null;
        }

        return slot.getElement().getInfo();
    }


    public String[] getProgrammerInfo(int id) {

        Player p = players.get(id);
        Programmer pr = p.getProgrammer();

        return new String[]{
                String.valueOf(pr.getId()),
                pr.getName(),
                String.valueOf(pr.getSkillLevel())
        };
    }

    public String getProgrammerInfoAsStr(int id) {
        Programmer p = players.get(id).getProgrammer();
        return p.getName() + " | Skill: " + p.getSkillLevel();
    }

    public String getProgrammersInfo() {
        StringBuilder sb = new StringBuilder();
        for (Player p : players) {
            sb.append(getProgrammerInfoAsStr(p.getId())).append("\n");
        }
        return sb.toString();
    }

    // ===================== RESULTS =====================

    public boolean gameIsOver() {
        return gameOver;
    }

    public ArrayList<String> getGameResults() {
        return gameResults;
    }

    // ===================== SAVE / LOAD =====================

    public boolean saveGame(File file) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            // tamaño tablero
            bw.write(String.valueOf(board.getSize()));
            bw.newLine();

            // jugador actual
            bw.write(String.valueOf(currentPlayerIndex));
            bw.newLine();

            // jugadores
            bw.write(String.valueOf(players.size()));
            bw.newLine();

            for (Player p : players) {
                bw.write(p.getId() + "," + p.getCurrentPosition() + "," + p.isAlive()
                        + "," + p.getMoveCount() + "," + p.getLastMove()
                        + "," + p.getProgrammer().getSkillLevel()
                        + "," + p.getProgrammer().hasProfessorHelp());
                bw.newLine();
            }

            // tablero
            for (int i = 0; i < board.getSize(); i++) {
                Slot s = board.getSlot(i);
                if (s.getElement() == null) {
                    bw.write("E");
                } else {
                    bw.write(s.getElement().getType() + ":" + String.join(",", s.getElement().getInfo()));
                }
                bw.newLine();
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }


    public void loadGame(File file) throws InvalidFileException, FileNotFoundException {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            int size = Integer.parseInt(br.readLine());
            board = new Board(size);

            currentPlayerIndex = Integer.parseInt(br.readLine());

            int playerCount = Integer.parseInt(br.readLine());
            players.clear();

            for (int i = 0; i < playerCount; i++) {
                String[] parts = br.readLine().split(",");

                int id = Integer.parseInt(parts[0]);
                int pos = Integer.parseInt(parts[1]);
                boolean alive = Boolean.parseBoolean(parts[2]);
                int moveCount = Integer.parseInt(parts[3]);
                int lastMove = Integer.parseInt(parts[4]);
                int skill = Integer.parseInt(parts[5]);
                boolean hasHelp = Boolean.parseBoolean(parts[6]);

                Programmer pr = new Programmer(id, "Programmer " + id, skill);
                if (hasHelp) pr.giveProfessorHelp();

                Player p = new Player(id, pr);
                p.forceMoveTo(pos);
                for (int m = 0; m < moveCount; m++) p.moveTo(pos, lastMove);
                if (!alive) p.kill();

                players.add(p);
            }

            for (int i = 0; i < size; i++) {
                String line = br.readLine();
                if (!line.equals("E")) {

                    String[] parts = line.split(":");
                    String[] info = parts[1].split(",");

                    if (parts[0].equals("Abyss")) {
                        board.getSlot(i).setElement(new Abyss(info[1]));
                    } else if (parts[0].equals("Tool")) {
                        board.getSlot(i).setElement(new SkillTool());
                    }
                }
            }

            gameOver = false;
            gameResults.clear();

        } catch (Exception e) {
            throw new InvalidFileException("Invalid file");
        }
    }


    // ===================== VISUAL =====================

    public String getImagePng(int nrSquare) {
        return "slot.png";
    }

    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("The Great Programming Journey - Authors"));
        return panel;
    }

    public HashMap<String, String> customizeBoard() {

        HashMap<String, String> map = new HashMap<>();
        map.put("hasNewAbyss", "true");
        map.put("hasNewTool", "true");
        return map;
    }

    // ===================== SUPPORT =====================

    public Board getBoard() {
        return board;
    }
}
