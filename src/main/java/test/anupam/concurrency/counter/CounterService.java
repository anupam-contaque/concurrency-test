package test.anupam.concurrency.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final CounterRepo counterRepo;

    @Autowired
    CounterService(CounterRepo counterRepo) {
        this.counterRepo = counterRepo;
    }

    /**
     * Increments current counter in database
     * and returns current value to user by adding 1 in current value of counter.
     *
     * @return a CounterResponse object with updated value of counter
     */
    CounterResponse incrementCounter() {
        int currentCounter = counterRepo.incrementCounter() + 1;
        return new CounterResponse("success", "Counter updated to " + currentCounter);
    }

    /**
     * Creates a CounterResponse object after querying database for counter.
     *
     * @return CounterResponse object with current value of counter
     */
    CounterResponse getCurrentCounter() {
        Integer counter = counterRepo.getCurrentCounter();
        return new CounterResponse("success", "Current value for counter is " + counter);
    }

    /**
     * Creates a CounterResponse object if any row was updated in database.
     *
     * @return CounterResponse object with success/failure status after reset
     */
    CounterResponse resetCounter() {
        int updatedRows = counterRepo.resetCounter();
        if (updatedRows > 0) {
            return new CounterResponse("success", "Counter reset to 0");
        } else {
            return new CounterResponse("failure", "No rows to reset counter");
        }
    }
}
