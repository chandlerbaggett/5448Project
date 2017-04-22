package spring.models;

public class OrderMemento {

	private Order order;
	
	public OrderMemento(Order order){
		this.order = order;
	}


	public OrderMemento() {
		
	}


	public Order getOrder(){
		return order;
	}
}
