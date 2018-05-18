package net.alice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.types.Predicate;

import net.alice.model.Hotel;

public interface  HotelRepository extends MongoRepository<Hotel, String>,QuerydslPredicateExecutor<Hotel>
{
	public Optional<Hotel> findById(String id);
	public List<Hotel> findByPricePerNightLessThan(int price);
	
	public List<Hotel> findByAddressCity(String city);
	
	@Query(value="{\"address.city\":?0}")
	public List<Hotel> findByCity(String city);
		
}
