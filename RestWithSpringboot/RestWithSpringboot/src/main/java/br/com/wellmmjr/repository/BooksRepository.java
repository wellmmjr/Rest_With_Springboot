package br.com.wellmmjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wellmmjr.data.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>{

}
