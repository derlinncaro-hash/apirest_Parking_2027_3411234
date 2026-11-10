package com.sena.apiparking.dto;

import java.time.LocalDateTime;

import com.sena.apiparking.model.RegistroPaqueo;
import com.sena.apiparking.model.TipoVehiculo;

public record RegistroResponse(Long idRegistro, String placa, TipoVehiculo tipoVehiculo, Integer numeroEspacio,
		LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Double valorPagado, cobroDetalle cobro) {
	public static RegistroResponse de(RegistroPaqueo r, cobroDetalle cobro) {

		return new RegistroResponse(r.getId_registro(), r.getVehiculo().getPlaca(), r.getVehiculo().getTipo(),
				r.getEspacio().getNumero(), r.getFechaEntrada(), r.getSalidaEntrada(), r.getValorPagado(), cobro);
	}
}
