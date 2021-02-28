package test.anupam.concurrency.counter;

public class CounterResponse {
    private final String status;
    private final String message;

    /**
     * Creates a new CounterResponse object that will be sent back to REST client.
     *
     * @param status - status of the response, like success or failure
     * @param message - a brief message with respect to status
     */
    public CounterResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CounterResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
