package teun.stout.v1;

import org.springframework.stereotype.Service;
import teun.stout.v1.model.CreateSourRequest;
import teun.stout.v1.model.GetSourRequestParams;
import teun.stout.common.exception.SourNotFoundException;
import teun.stout.v1.model.SourModel;

import java.util.List;
import java.util.Optional;

@Service
class SourServiceImpl implements SourService {

    private final SourRepository sourRepository;

    SourServiceImpl(SourRepository sourRepository) {
        this.sourRepository = sourRepository;
    }

    @Override
    public SourModel getSourById(Integer id) {
        Optional<SourModel> sour = sourRepository.findById(id);
        if (sour.isEmpty()) {
            throw new SourNotFoundException(String.format("Could not find sour with id '%s'", id));
        }
        return sour.get();
    }

    @Override
    public List<SourModel> getSours(GetSourRequestParams params) {
        // Todo: Implement
        return List.of();
    }

    @Override
    public SourModel createSour(CreateSourRequest createSourRequest) {
        return sourRepository.createSourRating(createSourRequest);
    }

    @Override
    public void deleteSourById(Integer id) {
        sourRepository.deleteById(id);
    }
}
