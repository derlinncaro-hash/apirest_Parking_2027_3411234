package com.sena.apiparking.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sena.apiparking.model.Tarifa;
import com.sena.apiparking.model.TipoVehiculo;
import com.sena.apiparking.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TarifaInicializador implements ApplicationRunner {
	private final TarifaRepository tarifaRepo;
	private final TarifaProperties props;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for (TipoVehiculo tipo : TipoVehiculo.values()) {
			if (tarifaRepo.existsByTipoAndVigenteHastaIsNull(tipo)) {
				continue;
			}
			TarifaProperties.Valores v = props.getTipos().get(tipo);
			tarifaRepo.save(Tarifa.builder().tipo(tipo).valoHora(v.getHora()).valorFraccion(v.getFraccion())
					.fraccionMinutos(props.getFraccionMinutos()).vigenteDesde(ZonaHoraria.ahora()).build());
			log.info("Tarifa inicial creada para {}", tipo);
		}

	}

}
