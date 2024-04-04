package com.learning.urlshortenerchallenge.repository;

import com.learning.urlshortenerchallenge.model.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Long> {

    public Optional<Url> findByShortenedUrl(String shortenedUrl);

}
