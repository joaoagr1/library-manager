package com.manager.library.model.service;

import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.domain.Users;
import com.manager.library.model.enums.Category;
import com.manager.library.model.repository.BookRepository;
import com.manager.library.model.repository.LoanRepository;
import com.manager.library.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getRecommendedBooks(UUID userId) {
        Users user = usersRepository.getReferenceById(userId);
        List<Loan> loans = loanRepository.findAllByUserId(userId);

        Set<Category> loanCategories = loans.stream()
                .map(Loan::getBook)
                .map(Book::getCategory)
                .collect(Collectors.toSet());

        List<Category> allCategories = Arrays.asList(Category.values());

        List<Category> recommendedCategories = allCategories.stream()
                .filter(category -> !loanCategories.contains(category))
                .collect(Collectors.toList());

        List<Book> recommendedBooks = recommendedCategories.stream()
                .map(category -> bookRepository.findFirstByCategory(category))
                .filter(book -> book != null)
                .collect(Collectors.toList());

        return recommendedBooks;
    }
}