package ShoppingCart.Product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Id
    private int id;
    private String userName;
    private String password;


	private String apikey;
    private String transactionToken;
    private String serviceToken;
    
    public User(int id, String userName, String password, String apikey, String transactionToken, String serviceToken) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.apikey = apikey;
		this.transactionToken = transactionToken;
		this.serviceToken = serviceToken;
	}
    public User() {
		super();
	}
	public User(int id, String userName, String password, String apikey) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.apikey = apikey;
	}
	
	
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getTransactionToken() {
		return transactionToken;
	}
	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}
	public String getServiceToken() {
		return serviceToken;
	}
	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
