public class Main {

    public static void main(String[] args) {

        int numGames = 3;
        char gameLetter = 'A';
        GamePlay gamePlay;

        for (int i = 0; i < numGames; i++) {
            System.out.printf("**** GAME %c ****%n", gameLetter);
            if (i == 0) {
                gamePlay = new GamePlay();
            } else if (i == 1) {
                gamePlay = new GamePlay(new Wizard());
                gamePlay.player.health = 10;
            } else {
                gamePlay = new GamePlay(new Barbarian(), new Wizard());
                gamePlay.opponents.get(0).health = 5;
            }
            for (int round = 1; round < 9; round++) {
                System.out.println("\tRound " + round);
                if (gamePlay.player.health > 0) {
                    System.out.println("\t\tYou gained " + gamePlay.play()
                            + " experience points during this round!!!!\n");
                }
                if (gamePlay.player.health <= 0) {
                    System.out.println("\t\tBut your player died. Better luck next time.\n\n");
                    break;
                }
            }
            gameLetter++;
        }
    }
}