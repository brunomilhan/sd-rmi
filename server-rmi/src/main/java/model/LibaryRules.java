package model;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bruno on 07/10/2016.
 */
public class LibaryRules {
    public static final int LOANS_LIMIT = 3;
    public static final long LOAN_PERIOD = TimeUnit.HOURS.toMillis(1);
    public static final long TIME_PENALTY = TimeUnit.MINUTES.toMillis(1);
}
