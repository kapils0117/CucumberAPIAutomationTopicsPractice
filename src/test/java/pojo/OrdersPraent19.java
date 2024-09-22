package pojo;

import java.util.List;

public class OrdersPraent19 {
	

	/*
	 {"orders":[ 
	 {"country":"Australia",
	 "productOrderedId":"66b06329ae2afd4c0b3f7aa3" } 
	  ] 
	 
	  }
	   In this JSON Order has nested array so we will create Order variable as String */

private List<Orderdetails19Child> orders;

public List<Orderdetails19Child> getOrders() {
	return orders;
}

public void setOrders(List<Orderdetails19Child> orders) {
	this.orders = orders;
}



}
