public class Gambling {
    public static final int STARTING_DAILY_STAKE = 100;
    public static final int BET = 1;
    public static final int DAYS = 30;

    //Global Variables
    public static int totalWin = 0;
    public static int totalLoss = 0;

    //method to check win or loss
    private static int checkWin() {
        return (int) Math.floor(Math.random() * 10) % 2;
    }

    //method for checking resign
    private static int checkresign(int startingStake, int endDayAmount) {
        //initialising control variable
        double winResignAmt = 1.5 * startingStake;
        double lossResignAmt = 0.5 * startingStake;
        if (endDayAmount >= winResignAmt || endDayAmount <= lossResignAmt)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {

        int month = 0;
        do {
            System.out.println("...............Month" + (++month) + "...........");
            //variables to check lucky day and unluckyday
            int netTotal = 0;
            int luckyDayAmt = 0;
            int unluckyDayAmt = 0;
            int luckyDay = 0;
            int unluckyDay = 0;

            for (int day = 1; day <= DAYS; day++) {

                int endDayAmount = STARTING_DAILY_STAKE;
                int dailyWin = 0;
                int dailyLoss = 0;

                while (checkresign(STARTING_DAILY_STAKE, endDayAmount) != 1) {

                    if (checkWin() == 1) {
                        endDayAmount += BET;
                        dailyWin += BET;
                    } else {
                        endDayAmount -= BET;
                        dailyLoss += BET;
                    }
                }
                totalWin += dailyWin;
                totalLoss += dailyLoss;
                netTotal += (dailyWin - dailyLoss);

                //functionality to check lucky day and unlucky day
                if (luckyDayAmt < netTotal) {
                    luckyDayAmt = netTotal;
                    luckyDay = day;
                }

                if (unluckyDayAmt > netTotal) {
                    unluckyDayAmt = netTotal;
                    unluckyDay = day;
                }

                System.out.println("On day " + day + "\tgambler won:\t" + dailyWin + "\tand lost:\t" + dailyLoss + "\tand day result:\t");

            }
            System.out.println("Total amount won : " + totalWin);
            System.out.println("Total amount lost : " + totalLoss);
            System.out.println("Net amount : " + (totalWin - totalLoss));
            System.out.println("Lucky day is day " + luckyDay + " amount won is " + luckyDayAmt + ".");
            System.out.println("unlucky day is day " + unluckyDay + " amount loss is " + unluckyDayAmt + ".");

        } while ((totalWin - totalLoss) > 0); //if not amount is greater than 0 then continue
    }
}

