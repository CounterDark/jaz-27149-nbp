package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import model.Nbp;
import model.NbpRequestData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.NbpService;

@RestController
@RequestMapping("/")
public class NbpController {
    private NbpService nbpService;

    public NbpController(NbpService nbpService) { this.nbpService = nbpService; }

    @Operation(summary = "Create new movie from JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = { @Content()}, description = "Success!"),
            @ApiResponse(responseCode = "400", content = { @Content()}, description = "Wrong JSON!"),
            @ApiResponse(responseCode = "500", content = { @Content()}, description = "Internal server error!")
    })
    @PostMapping("getMidForSection")
    public ResponseEntity<Nbp> queryNbp(@RequestBody @Parameter(name = "nbpRequestData") NbpRequestData nbpRequestData) {
        Nbp nbp = nbpService.queryCurrencyMidForSection(nbpRequestData);
        return ResponseEntity.ok(nbp);
    }
}
