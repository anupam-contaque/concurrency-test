package test.anupam.concurrency.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    CounterRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Query and get value of current counter in database.
     *
     * @return value of counter stored in database
     */
    Integer getCurrentCounter() {
        return jdbcTemplate.queryForObject("SELECT counter FROM concurrency_test", Integer.class);
    }

    /**
     * Increments current counter in database through MySQL variable,
     * it sets current value of counter in a variable and increments in same query.
     *
     * This can also be achieved by acquiring transaction but that would be slower than single UPDATE statement.
     *
     * @return value of current_counter variable i.e. value of counter before UPDATE was performed
     */
    Integer incrementCounter() {
         jdbcTemplate.update("UPDATE concurrency_test SET counter = (@current_count := counter) + 1");
         return jdbcTemplate.queryForObject("SELECT @current_count", Integer.class);
    }

    /**
     * Update counter value in database to 0.
     *
     * @return number of rows updated in database
     */
    int resetCounter() {
        return jdbcTemplate.update("UPDATE concurrency_test SET counter = 0");
    }
}
