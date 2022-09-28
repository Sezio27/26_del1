package spil;

public class Player {
    private int playerNumber;
    private int points;

    public Player(int playerNumber, int points) {
        this.playerNumber = playerNumber;
        this.points = points;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPoints(int newPoints) {
        points = newPoints;
    }

    public int getPoints(){
        return points;
    }
}
