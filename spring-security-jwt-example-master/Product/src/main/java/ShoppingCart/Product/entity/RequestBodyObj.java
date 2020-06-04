package ShoppingCart.Product.entity;

import java.util.List;

public class RequestBodyObj {
	TokenObj tokenobj;
	List<Product> products;
	public RequestBodyObj(TokenObj tokenobj, List<Product> products) {
		super();
		this.tokenobj = tokenobj;
		this.products = products;
	}
	public TokenObj getTokenobj() {
		return tokenobj;
	}
	public void setTokenobj(TokenObj tokenobj) {
		this.tokenobj = tokenobj;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
