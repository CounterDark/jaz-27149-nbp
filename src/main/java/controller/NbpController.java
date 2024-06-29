package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import model.Nbp;
import model.NbpRequestData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.NbpService;

import java.sql.Date;

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
    @GetMapping("getMidForSection")
    public ResponseEntity<Nbp> queryNbp(@RequestParam @Parameter(name = "nbpRequestData") String code, @RequestParam Date startDate, @RequestParam Date endDate) {
        NbpRequestData nbpRequestData = new NbpRequestData();
        nbpRequestData.currencyCode = code;
        nbpRequestData.sectionStart = startDate;
        nbpRequestData.sectionEnd = endDate;
        Nbp nbp = nbpService.queryCurrencyMidForSection(nbpRequestData);
        return ResponseEntity.ok(nbp);
    }
}
