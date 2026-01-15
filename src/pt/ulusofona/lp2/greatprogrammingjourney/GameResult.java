package pt.ulusofona.lp2.greatprogrammingjourney;
//Resultado partida
public class GameResult {
    //ID ganador
    private int winnerPlayerId;
    //Descripcion resultado
    private String description;


    public GameResult(int winnerPlayerId, String description) {
        this.winnerPlayerId = winnerPlayerId;
        this.description = description;
    }

    public int getWinnerPlayerId() {
        return winnerPlayerId;
    }

    public String getDescription() {
        return description;
    }
}
