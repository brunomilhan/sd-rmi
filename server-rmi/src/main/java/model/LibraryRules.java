package model;

import java.util.concurrent.TimeUnit;

/**
 * Constantes contendo as regras da biblioteca.
 * Created by Bruno on 07/10/2016.
 */
class LibraryRules {
    static final int LOANS_LIMIT = 3;
    static final long LOAN_PERIOD = TimeUnit.HOURS.toMillis(1);
    static final long TIME_PENALTY = TimeUnit.MINUTES.toMillis(1);
}
