package teun.stout.sour;

import org.springframework.stereotype.Service;

@Service
class SourServiceImpl implements SourService {

    private final SourRepository sourRepository;

    SourServiceImpl(SourRepository sourRepository) {
        this.sourRepository = sourRepository;
    }

    public SourModel createSourRating() {
        return sourRepository.createSourRating();
    }

}
