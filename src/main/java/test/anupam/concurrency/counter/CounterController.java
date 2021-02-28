package test.anupam.concurrency.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle different endpoints related to counter,
 * each endpoint returns response of type application/json.
 *
 **/
@RestController
@RequestMapping(value = "/api/counter", produces = MediaType.APPLICATION_JSON_VALUE)
public class CounterController {

    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    /**
     * Get current value of counter.
     *
     * @return JSON response with current value of counter in database
     */
    @GetMapping(value = "/current-value")
    public CounterResponse getCurrentCounter() {
        return counterService.getCurrentCounter();
    }


    /**
     * Increment counter by 1.
     *
     * @return JSON response with current value of counter after increment
     */
    @PostMapping(value = "/increment")
    public CounterResponse incrementCounter() {
        return counterService.incrementCounter();
    }

    /**
     * Reset counter to 0.
     *
     * @return JSON response with success/failure status after reset
     */
    @PostMapping(value = "/reset")
    public CounterResponse resetCounter() {
        return counterService.resetCounter();
    }
}
