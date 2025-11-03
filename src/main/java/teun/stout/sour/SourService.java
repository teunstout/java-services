package teun.stout.sour;

public interface SourService {

    /**
     * Create the rating for a sour e.g. lemon sour
     *
     * @return created Sour
     */
    public SourModel createSourRating(CreateSourRequest createSourRequest);
}
