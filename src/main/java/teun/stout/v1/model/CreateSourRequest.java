package teun.stout.v1.model;

import java.time.LocalDate;

public record CreateSourRequest(
        String type,
        double rating,
        String store,
        String comment,
        LocalDate drankAt
) {

    public CreateSourRequest {
        if (rating > 10 || rating < 0) {
            throw new IllegalArgumentException("Rating has to be between 0 and 10");
        }
    }

}
