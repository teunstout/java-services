package teun.stout.sour;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface SourRepository extends CrudRepository<SourModel, Integer> {

    @Query("""
                INSERT INTO sour (type, rating, store, comment, drank_at)
                    VALUES (
                        :#{#request.type},
                        :#{#request.rating},
                        :#{#request.store},
                        :#{#request.comment},
                        :#{#request.drankAt}
                    )
            """)
    @Modifying
    SourModel createSourRating(@Param("request") CreateSourRequest createSourRequest);
}
