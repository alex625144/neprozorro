package com.neprozorro.rest;

import com.neprozorro.model.LotInfo;
import com.neprozorro.rest.dto.LotInfoCriteriaRequestDto;
import com.neprozorro.rest.dto.LotInfoResponseDTO;
import com.neprozorro.service.LotInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lot-info")
@RequiredArgsConstructor
@Tag(name = "lot information")
public class LotInfoResource {

    private final LotInfoService lotInfoService;

    @Operation(
            summary = "information of lot with parameters",
            description = "push button (Try it out), you can get information about lot for special parameters "
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public ResponseEntity<Page<LotInfoResponseDTO>> findByCriteria(
            @Parameter(
                    name = "lot criteria",
                    description = """
                                    The criteria for filtering lot information.\n                                    
                                      Delete or use any field to change criteria after click (Try it out)\n
                                      Criteria of "lotStatus" can be more than 1, list them with commas.                                      
                                      Examples are in chapter Schemas -> criteria of lot information -> lotstatus -> Enum -> Array\n
                                      Set range "lotTotalPrice" with comma.                               
                                    """
            ) @Validated LotInfoCriteriaRequestDto lotInfoCriteriaRequestDto,
            @Parameter(example = "{\n" +
                    "  \"page\": 0,\n" +
                    "  \"size\": 10,\n" +
                    "  \"sort\": [\n" +
                    "  \"lotTotalPrice,desc\"\n" +
                    "  ]\n" +
                    "}",
                    name = "page info",
                    description = "Pagination information. Use any field to change criteria"

            ) Pageable pageable) {

        return ResponseEntity.ok(lotInfoService.findByCriteria(lotInfoCriteriaRequestDto, pageable));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Hidden
    public ResponseEntity<List<LotInfo>> findAll() {
        return ResponseEntity.ok(lotInfoService.findAll());
    }
}
