package spring.models;

//@MappedSuperclass
public abstract class Model {

	//@Id
	//@GeneratedValue
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
