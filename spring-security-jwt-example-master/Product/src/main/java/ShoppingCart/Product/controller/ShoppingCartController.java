package ShoppingCart.Product.controller;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ShoppingCart.Product.repository.UserRepository;

import ShoppingCart.Product.entity.Product;
import ShoppingCart.Product.entity.RequestBodyObj;
import ShoppingCart.Product.entity.TokenObj;
import ShoppingCart.Product.entity.User;
import ShoppingCart.Product.entity.RequestBodyObj;
import ShoppingCart.Product.exception.NotEnoughProductsInStockException;
import ShoppingCart.Product.repository.ProductRepository;
import ShoppingCart.Product.service.ShoppingCartService;
import org.apache.commons.codec.binary.Base64;


@RestController
public class ShoppingCartController {
	@Autowired
	private UserRepository repository;
    @Autowired
    private ProductRepository proRepo;
    @Autowired
	private ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to javatechie !!";
    }

  
  	
  //Get all
  	@PostMapping("/products/all")
  	public List<Product> retrieveproducts(@RequestBody TokenObj tokenobj)
  	{
  		this.SaveUser(tokenobj);  		
  		List<Product> products= proRepo.findAll();
  		if (products==null)
  		{
  			try {
				throw new Exception("no records");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  		}
  		return products;		
  	}
  	
	
  	private void SaveUser(TokenObj tokenobj) {
		// TODO Auto-generated method stub
  		System.out.println("*******************");
  		System.out.println(tokenobj.getUserToken());
  		byte[] decoded64=Base64.decodeBase64(tokenobj.getUserToken());
  		String s=new String(decoded64);
  		System.out.println("Hardika*********************"+s);		
  		String[] userdetails=s.split("###");
  		System.out.println(userdetails);
  		User user=new User();
  		user.setId(Integer. parseInt(userdetails[0]));
  		user.setUserName(userdetails[1]);
  		user.setPassword(userdetails[2]);
  		
  		user.setApikey(userdetails[3]);
  		user.setTransactionToken(tokenobj.getTransactionToken().substring(0, 254));
  		user.setServiceToken(tokenobj.getServiceToken().substring(0, 254));
  		repository.save(user);
	}



	@PostMapping("/purchase")
      public  ResponseEntity<String>  purchaseProduct(@RequestBody RequestBodyObj requestBodyObj){
          try {
        	  TokenObj tokenobj =requestBodyObj.getTokenobj();
        	  List<Product> products=requestBodyObj.getProducts();
        	  this.SaveUser(tokenobj);
        	  
			return ResponseEntity.status(200).body("Total amount payable is Rs:"+ this.shoppingCartService.purchaseProduct(products));
		} catch (NotEnoughProductsInStockException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(200).body(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(200).body(e.getMessage());
		}
      }
	
  	@PostMapping("/refund")
      public ResponseEntity<String> refundProduct(@RequestBody RequestBodyObj requestBodyObj) {
          try {
        	  TokenObj tokenobj =requestBodyObj.getTokenobj();
        	  List<Product> products=requestBodyObj.getProducts();
        	  this.SaveUser(tokenobj);
        	  
			return ResponseEntity.status(200).body("Total amount refundable is Rs:"+this.shoppingCartService.refundProduct(products));
		} catch (NotEnoughProductsInStockException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(200).body(e.getMessage());
		}
      }
  	


    
    
    
    
    
    
    
    
}
