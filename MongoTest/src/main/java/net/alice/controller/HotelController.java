package net.alice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import net.alice.model.Hotel;
import net.alice.model.QHotel;
import net.alice.repository.HotelRepository;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	private HotelRepository hotelRepository;
	
	public HotelController (HotelRepository hotelRepository) {
		this.hotelRepository=hotelRepository;
	} 
	
	@GetMapping("/all")
	public List<Hotel> getAll(){
		return hotelRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Hotel getById(@PathVariable("id") String id){
		return hotelRepository.findById(id).get();
	}
	
	
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getById(@PathVariable("maxPrice") int maxPrice){
		return hotelRepository.findByPricePerNightLessThan(maxPrice);
	}
	
	@GetMapping("/city/{city}")
	public List<Hotel> getByCity(@PathVariable("city") String city){
		//return hotelRepository.findByAddressCity(city);
		return hotelRepository.findByCity(city);
	}
	
	
	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country") String country){
		//return hotelRepository.findByAddressCity(city);
		//return hotelRepository.findByCity(city);
		QHotel qHotel=new QHotel("hotel");
		BooleanExpression filterByCountry=qHotel.address.country.eq(country);
		
		return (List<Hotel>) hotelRepository.findAll(filterByCountry);
	}
	
	

	@GetMapping("/recommended")
	public List<Hotel> getRecommended(){
		//return hotelRepository.findByAddressCity(city);
		//return hotelRepository.findByCity(city);
		final int maxPrice=100;
		final int minRating=5;
		
		QHotel qHotel=new QHotel("hotel");
		
		BooleanExpression filterByPrice=qHotel.pricePerNight.lt(maxPrice);
		BooleanExpression filterByRating=qHotel.reviews.any().rating.gt(minRating);
		
		return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
	}
	
	
	@PostMapping
	public void update(@RequestBody Hotel hotel){
		hotelRepository.save(hotel);
	}
	
	
	@PutMapping
	public void insert(@RequestBody Hotel hotel){
		hotelRepository.insert(hotel);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id ){
		hotelRepository.deleteById(id);
	}
	
}
