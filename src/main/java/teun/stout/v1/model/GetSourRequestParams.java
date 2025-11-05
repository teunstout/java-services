package teun.stout.v1.model;

import teun.stout.common.exception.SourParamException;

import java.time.LocalDate;

public record GetSourRequestParams(
        String type,
        Double ratingAbove,
        Double ratingBelow,
        String store,
        LocalDate drankAfter,
        LocalDate drankBefore
) {

    public GetSourRequestParams {
        // Validate the ratings
        if (ratingAbove != null && (ratingAbove < 0 || ratingAbove > 10)) {

            throw new SourParamException("ratingAbove must be between 0 and 10");
        }
        if (ratingBelow != null && (ratingBelow < 0 || ratingBelow > 10)) {
            throw new SourParamException("ratingBelow must be between 0 and 10");
        }
        if (ratingAbove != null && ratingBelow != null && ratingAbove > ratingBelow) {
            throw new SourParamException("ratingAbove cannot be greater than ratingBelow");
        }

        // Validate dates
        if (drankAfter != null && drankBefore != null && drankAfter.isAfter(drankBefore)) {
            throw new SourParamException("drankAfter cannot be after drankBefore");
        }
    }

}
