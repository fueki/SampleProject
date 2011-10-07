package g.g.g.gamesystem.action;

import java.util.Random;

public class ActionManage {
	private static final int actionTimes = 3;
    public static int getInstance()
    {
        return actionTimes;
    }
    public static int throwDice(){
    	Random rnd = new Random();
    	return rnd.nextInt(6) + 1;
    }

}
