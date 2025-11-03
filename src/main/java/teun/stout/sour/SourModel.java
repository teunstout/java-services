package teun.stout.sour;

import java.time.LocalDate;

public record SourModel(
        long id,
        String type,
        double rating,
        String store,
        String comment,
        LocalDate drankAt
) {

    public SourModel {
        if (rating > 10 || rating < 0) {
            throw new IllegalArgumentException("Rating has to be between 0 and 10");
        }
    }

}
