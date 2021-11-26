package br.com.wellmmjr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wellmmjr.converter.DozerConverter;
import br.com.wellmmjr.data.model.Books;
import br.com.wellmmjr.data.vo.v1.BooksVO;
import br.com.wellmmjr.exception.ResourceNotFoundException;
import br.com.wellmmjr.repository.BooksRepository;

@Service
public class BooksServices {

	@Autowired
	BooksRepository repository;
	
	public BooksVO createBooks(BooksVO books) {
		
		var entity = DozerConverter.parseObject(books, Books.class);
		repository.save(entity);
		var entityVO = DozerConverter.parseObject(entity, BooksVO.class);
		
//		var entityVO = DozerConverter.parseObject(repository.save(entity), BooksVO.class); <-- maneira alternativa de realizar a conversão já salvando
		
		return entityVO;
	}
	
	public BooksVO updateBooks(BooksVO books) {
		var entity = repository.findById(books.getKey()). // atenção: este findById retorna um Books, visto que é da classe BooksRepository e ñ metodo desta propria
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;

		entity.setAuthor(books.getAuthor());
		entity.setLaunchDate(books.getLaunchDate());
		entity.setPrice(books.getPrice());
		entity.setTitle(books.getTitle());
		
		return DozerConverter.parseObject(repository.save(entity), BooksVO.class);
	}
	
	public void deleteBooks(Long id) {
		Books entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		repository.delete(entity);
	}
	
	public BooksVO findById(Long id) {

		var entity = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("no records found for this id")) ;
		return DozerConverter.parseObject(entity, BooksVO.class);
	}
	
	
	public List<BooksVO> findAll() {
		
		return DozerConverter.parseListObjects(repository.findAll(), BooksVO.class);
	}
	
}
