package ca.ulaval.glo4002.vet.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.ulaval.glo4002.vet.application.BreedingService;
import ca.ulaval.glo4002.vet.domain.Breeding;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RepositoryRestController
@RequestMapping("/breed")
public class BreedingController {
    private final BreedingService service;

    @Autowired
    public BreedingController(BreedingService service) {
        this.service = service;
    }

    @PostMapping(value = "", produces = {"application/json"}, consumes = {"application/json"})
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Breeding worked",
                    response = BreedingResult.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Impossibles to breed these species",
                    response = ErrorInfo.class
            )
    })
    public @ResponseBody
    BreedingResult breed(@RequestBody BreedingRequest request) {
        Breeding breeding = service.breed(request.fatherSpecies, request.motherSpecies);
        return new BreedingResult(breeding.getOffspring(), breeding.getGender());
    }

    @ExceptionHandler({ImpossibleBreedingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorInfo breedingError(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(ex.getMessage());
    }
}
