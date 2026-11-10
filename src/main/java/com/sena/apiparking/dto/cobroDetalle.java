package com.sena.apiparking.dto;

public record cobroDetalle(Long idTarifa, long minutosTotales, long horasCompletas, long fraccionCobradas,
		long tarifaHora, long tarifaFraccion, int tarifaMinutos, long valorTotal) {

}
