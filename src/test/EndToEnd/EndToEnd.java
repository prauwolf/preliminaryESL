package test.EndToEnd;

import sim.engine.SimState;
import BalanceSheet.BalanceSheet;

public class EndToEnd extends SimState {

    public EndToEnd(long seed)
    {
        super(seed);
    }

    public void start()
    {
        super.start();

        BalanceSheet b1 = new BalanceSheet();
        BalanceSheet b2 = new BalanceSheet();

        /*
         * for (int i = 0; i < numStudents; i++)
         * {
         * Student student = new Student();
         * yard.setObjectLocation(student,
         * new Double2D(yard.getWidth() * 0.5 + random.nextDouble()
         * - 0.5,
         * yard.getHeight() * 0.5 + random.nextDouble() - 0.5));
         * schedule.scheduleRepeating(student);
         * }
         */
    }

    public static void main(String[] args)
    {
        doLoop(EndToEnd.class, args);
        System.exit(0);
    }

}
