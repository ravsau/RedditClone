package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface RedditRepository extends CrudRepository<Reddit,Integer> {
	
	Iterable<Reddit> findByUsername( String username);

}
