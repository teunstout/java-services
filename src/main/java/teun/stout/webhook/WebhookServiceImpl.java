package teun.stout.webhook;

public class WebhookServiceImpl implements WebhookService {
    private final TaskExecutor taskExecutor;
    private final EventRepository eventRepository; // JPA to store events and idempotencyKey

    public WebhookServiceImpl(TaskExecutor taskExecutor, EventRepository eventRepository) {
        this.taskExecutor = taskExecutor;
        this.eventRepository = eventRepository;
    }

    public void enqueueEvent(String payload, String idempotencyKey) {
        if (idempotencyKey != null && eventRepository.existsByIdempotencyKey(idempotencyKey)) {
            return; // already queued/processed
        }
        WebhookEvent event = new WebhookEvent(payload, idempotencyKey, EventStatus.QUEUED, Instant.now());
        eventRepository.save(event);
        taskExecutor.execute(() -> processEvent(event.getId()));
    }

    private void processEvent(Long eventId) {
        // load, mark processing, handle business logic, mark complete/fail, implement retry/backoff
    }

}
