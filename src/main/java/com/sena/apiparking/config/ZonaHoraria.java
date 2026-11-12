package com.sena.apiparking.config;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class ZonaHoraria {
	public static final ZoneId ZONA = ZoneId.of("America/Bogota");

	private ZonaHoraria() {

	}

	public static LocalDateTime ahora() {
		return LocalDateTime.now(ZONA);
	}
}
