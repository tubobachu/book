package com.btl.book.repository;

import com.btl.book.entity.Book;
import com.btl.book.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

}
