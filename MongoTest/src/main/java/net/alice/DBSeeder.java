package net.alice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.alice.model.Address;
import net.alice.model.Hotel;
import net.alice.model.Review;
import net.alice.repository.HotelRepository;

@Component
public class DBSeeder  implements CommandLineRunner{
	
	private HotelRepository hotelRepository;		
	
	public DBSeeder(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Hotel hotelMariot=new Hotel("Marriot", 130, new Address("Paris", "France"), Arrays.asList(
				new Review("John", 8, false),
				new Review("Mary", 9, true) ));
		
		Hotel hotelIbis=new Hotel("IBIS", 90, new Address("Bucharest", "Romaina"), Arrays.asList(
				new Review("Teddy", 9, true)));
		
		
		Hotel hotelSofitel=new Hotel("Sofitel", 80, new Address("Rome", "Italy"), new ArrayList<>());
		
		
		hotelRepository.deleteAll();
		List<Hotel> hotels=Arrays.asList(hotelMariot,hotelIbis,hotelSofitel);
		
		hotelRepository.saveAll(hotels);
		
	}
	
}
