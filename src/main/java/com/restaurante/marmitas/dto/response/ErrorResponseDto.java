package com.restaurante.marmitas.dto.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "ErrorResponse", description = "Schema para lidar com respostas de falha da API")
@Data
@AllArgsConstructor
public class ErrorResponseDto {

	@Schema(description = "Caminho da API invocado pelo cliente")
	private String apiPath;

	@Schema(description = "Código de erro representando o erro ocorrido")
	private HttpStatus errorCode;

	@Schema(description = "Mensagem de erro representando o erro ocorrido")
	private String errorMessage;

	@Schema(description = "Horário representando quando o erro ocorreu")
	private LocalDateTime errorTime;

}
