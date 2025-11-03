package teun.stout.sour;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

interface SourRepository extends CrudRepository<SourModel, Integer> {

    @Query("INSERT INTO sour_ratings (rating, comment, created_at) VALUES (:rating, :comment, :createdAt)")
    @Modifying
    SourModel createSourRating();
}
