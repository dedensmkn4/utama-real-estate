package com.utama.deden.reza.dao.api;

import com.utama.deden.reza.entity.dao.Booking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {

}
