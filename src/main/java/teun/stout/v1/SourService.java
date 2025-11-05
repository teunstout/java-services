package teun.stout.v1;

import teun.stout.v1.model.CreateSourRequest;
import teun.stout.v1.model.GetSourRequestParams;
import teun.stout.v1.model.SourModel;

import java.util.List;

public interface SourService {

    /**
     * This will retrieve a sour by id or throw a
     *
     * @param id of the Sour
     * @return A sour model.
     * @throws teun.stout.sour.common.exception.SourNotFoundException if no sour with the given ID exists
     */
    public SourModel getSourById(Integer id);


    /**
     * This function will get all sours based on the filters provided in {@link GetSourRequestParams}
     *
     * @param params filters for request
     * @return lemon sours
     */
    public List<SourModel> getSours(GetSourRequestParams params);

    /**
     * Create the rating for a sour e.g. lemon sour
     *
     * @return created Sour
     */
    public SourModel createSour(CreateSourRequest createSourRequest);

    /**
     * Delete a sour by id
     *
     * @param id of the Sour
     * @throws teun.stout.sour.common.exception.SourNotFoundException if no sour with the given ID exists
     */
    public void deleteSourById(Integer id);
}
