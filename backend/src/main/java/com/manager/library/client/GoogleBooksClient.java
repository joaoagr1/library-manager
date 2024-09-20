package com.manager.library.client;

import com.manager.library.model.dtos.GoogleBooksResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name = "googleBooksClient", url = "https://www.googleapis.com/books/v1")
public interface GoogleBooksClient {
    @GetMapping("/volumes")
    GoogleBooksResponseDTO getBookByIsbn(@RequestParam("q") String isbn);
}