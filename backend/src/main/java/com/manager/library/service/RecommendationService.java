package com.manager.library.service;

import com.manager.library.domain.Book;
import com.manager.library.domain.Loan;
import com.manager.library.domain.Users;
import com.manager.library.enums.Category;
import com.manager.library.repository.BookRepository;
import com.manager.library.repository.LoanRepository;
import com.manager.library.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

        usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        
        List<Loan> userLoans = loanRepository.findAllByUserId(userId);
        Set<Category> userLoanCategories = extractLoanCategories(userLoans);
        List<Category> recommendedCategories = findRecommendedCategories(userLoanCategories);
        return findBooksByCategories(recommendedCategories);

    }

    private Set<Category> extractLoanCategories(List<Loan> loans) {
        return loans.stream()
                .map(Loan::getBook)
                .map(Book::getCategory)
                .collect(Collectors.toSet());
    }

    private List<Category> findRecommendedCategories(Set<Category> loanCategories) {
        List<Category> allCategories = Arrays.asList(Category.values());
        return allCategories.stream()
                .filter(category -> !loanCategories.contains(category))
                .collect(Collectors.toList());
    }

    private List<Book> findBooksByCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> bookRepository.findFirstByCategory(category))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}