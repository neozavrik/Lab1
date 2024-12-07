package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

	private static List<Flight> flights = new ArrayList<>();

	// Ініціалізуйте список рейсів
	static {
		flights.add(new Flight(1L, "Kyiv", "Lviv",
				LocalDateTime.of(2024, 12, 1, 10, 0),
				LocalDateTime.of(2024, 12, 1, 11, 0),
				150.0, "FL123", LocalDateTime.now()));
	}

	// Метод для отримання списку рейсів
	@GetMapping
	public List<Flight> getAllFlights() {
		return flights;
	}

	// Метод для отримання рейсу за ID
	@GetMapping("/{id}")
	public Flight getFlightById(@PathVariable Long id) {
		return flights.stream()
				.filter(flight -> flight.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
	}

	// Метод для створення нового рейсу
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Flight createFlight(@RequestBody Flight flight) {
		flight.setId((long) (flights.size() + 1)); // Автогенерація ID
		flight.setCreationDate(LocalDateTime.now());
		flights.add(flight);
		return flight;
	}

	// Метод для видалення рейсу за ID
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFlight(@PathVariable Long id) {
		flights.removeIf(flight -> flight.getId().equals(id));
	}
}
