package com.sena.apiparking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.apiparking.model.EspacioParqueo;
import com.sena.apiparking.model.RegistroPaqueo;

import jakarta.persistence.LockModeType;

public interface RegistroParqueoRepository extends JpaRepository<RegistroPaqueo, Long> {
	@Query("select count(r) > 0 from RegistroParqueo r join r.vehiculo V where v.placa =:placa and r.fechaSalida is null")
	boolean existsByVehiculoPlacaAndFechaSalidaIsNull(@Param("placa") String placa);

	@Query("select count(r) > 0 from RegistroParqueo r where r.espacio =: espacio")
	boolean existsByEspacio(@Param("espacio") EspacioParqueo espacio);

	Optional<RegistroPaqueo> findVehiculoPlacaAndFechaSalidaIsNull(String placa);

	List<RegistroPaqueo> findByFechaSalidaIsNotNullOrderByFechaEntradaDesc();

	List<RegistroPaqueo> findByFechaSalidaIsNullOrderByFechaEntradaDesc();

	List<RegistroPaqueo> findByVehiculoPlacaOrderByFechaEntradaDesc(String placa);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from RegistroParqueo r where r.id_registro = :id")
	Optional<RegistroPaqueo> bloquearPorId(@Param("id") Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from RegistroParqueo r join r.vehiculo where v.placa = :placa " + "and r.fechaSalida is null")
	Optional<RegistroPaqueo> bloquearActivoPorPlaca(@Param("placa") String placa);

}
