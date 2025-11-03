package teun.stout.sour.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teun.stout.sour.CreateSourRequest;
import teun.stout.sour.SourModel;
import teun.stout.sour.SourService;

@RestController
@RequestMapping(path = "v1/sours")
class SourController {

    private final SourService sourService;

    SourController(SourService sourService) {
        this.sourService = sourService;
    }

    @PostMapping("/sour/rating")
    SourModel createSourRating(@RequestBody CreateSourRequest createSourRequest) {
        return sourService.createSourRating(createSourRequest);
    }

}
