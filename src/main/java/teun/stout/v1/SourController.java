package teun.stout.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teun.stout.v1.model.CreateSourRequest;
import teun.stout.v1.model.GetSourRequestParams;
import teun.stout.common.ApiError;
import teun.stout.v1.model.SourModel;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/v1/sours")
class SourController {

    private final SourService sourService;
    private final Logger log = Logger.getLogger(SourController.class.getName());

    SourController(SourService sourService) {
        this.sourService = sourService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a lemon sour by id")
    @ApiResponse(
            responseCode = "200",
            description = "Lemon sour by id",
            content = @Content(
                    schema = @Schema(implementation = SourModel.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Sour is not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    ResponseEntity<SourModel> getSourById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(sourService.getSourById(id));
    }

    @GetMapping
    @Operation(summary = "Get all sours with optional filters")
    @ApiResponse(
            responseCode = "200",
            description = "List of sours matching the filters",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SourModel.class))
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid filter parameters",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "No sours found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    ResponseEntity<List<SourModel>> getSours(@ModelAttribute GetSourRequestParams params) {
        return ResponseEntity.ok(sourService.getSours(params));
    }

    @PostMapping("/sour")
    @Operation(summary = "Create a sour")
    @ApiResponse(
            responseCode = "201",
            description = "Sour created",
            content = @Content(
                    schema = @Schema(implementation = SourModel.class)
            )
    )
    ResponseEntity<SourModel> createSour(@RequestBody CreateSourRequest createSourRequest) {
        log.info("Create a sour");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sourService.createSour(createSourRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a sour")
    @ApiResponse(
            responseCode = "200",
            description = "Sour deleted"
    )
    @ApiResponse(
            responseCode = "404",
            description = "No sours found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    ResponseEntity<Void> createSour(@PathVariable("id") Integer id) {
        log.info("Delete sour");
        return ResponseEntity.ok(null);
    }

}
