package com.restaurante.marmitas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema para lidar com respostas de sucesso da API")
@Data @AllArgsConstructor
public class ResponseDto {

	@Schema(description = "Status code da resposta")
    private String statusCode;
	
	@Schema(description = "Status message da resposta")
    private String statusMsg;
    
}