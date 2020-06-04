package ShoppingCart.Product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ShoppingCart.Product.entity.Product;
import ShoppingCart.Product.exception.NotEnoughProductsInStockException;
import ShoppingCart.Product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

	private final ProductRepository productRepository;


	@Autowired
	public ShoppingCartService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	
	BigDecimal totalreturn;
	Long i;
	public BigDecimal refundProduct(List<Product> products2) throws NotEnoughProductsInStockException {
		
		// TODO Auto-generated method stub
		  i=(long)0;
		List<Product> productReturn=new ArrayList<>();
		totalreturn=new BigDecimal(0);
		products2.forEach(e->{
			Optional<Product>	proItr=productRepository.findById(e.getId());
			if (!proItr.isPresent() )
			i=e.getId();	
			else
			{	
				Product p = new Product(proItr.get().getId(),proItr.get().getName(),proItr.get().getDescription(),proItr.get().getQuantity()+e.getQuantity(),proItr.get().getPrice());
				totalreturn=totalreturn.add((new BigDecimal(e.getQuantity()).multiply(p.getPrice())));
				productReturn.add(p);
			}
		});
		if(i!=0)
			throw new NotEnoughProductsInStockException();
		
				  productRepository.saveAll(productReturn); 
				  productRepository.flush();
		return totalreturn;
	}
	BigDecimal total;
	
	public BigDecimal purchaseProduct(List<Product> products2) throws NotEnoughProductsInStockException{
		List<Product> productReturn=new ArrayList<>();
		i=(long)0;
		total=new BigDecimal(0);
		products2.forEach(e->{
		Optional<Product> proItr = productRepository.findById(e.getId());
			if (!proItr.isPresent() || proItr.get().getQuantity() < e.getQuantity())
				i=e.getId();
			else
			{	
				Product p = new Product(proItr.get().getId(),proItr.get().getName(),proItr.get().getDescription(),proItr.get().getQuantity()-e.getQuantity(),proItr.get().getPrice());
				total= total.add((new BigDecimal(e.getQuantity()).multiply(p.getPrice())));
				productReturn.add(p);
			}
		});
		if(i!=0)
			throw new NotEnoughProductsInStockException();
		productRepository.saveAll(productReturn); 
		productRepository.flush();
		
		return total;
	}
}
