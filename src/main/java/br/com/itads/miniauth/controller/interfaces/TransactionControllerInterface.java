package br.com.itads.miniauth.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import br.com.itads.miniauth.dto.TransactionDTO;
import br.com.itads.miniauth.enums.TransactionEnum;
import br.com.itads.miniauth.responses.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author marioromeu
 * @email mario.romeu@gmail.com
 *
 */
@Tag(name = "Transations", description = "API to process a transaction")
public interface TransactionControllerInterface {

  /**
   * 
   * @param header
   * @param body
   * @return
   */
  @Operation(summary = "Process Transaction", description = "Process Transaction", tags={  })
  @ApiResponses(value = { 
          @ApiResponse(responseCode = "201", description = "Processed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))),
          
          @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))) 
      }
  )
  @PostMapping(value = "/transacoes", produces = { "application/json" }, consumes = { "application/json" })
  ResponseEntity<TransactionEnum> createNewTransaction(
      @RequestHeader(value = "header", required = true) MultiValueMap<String, String> header,
      @RequestBody TransactionDTO body);

}
