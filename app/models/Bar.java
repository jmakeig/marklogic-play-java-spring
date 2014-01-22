package models;

import javax.xml.bind.annotation.XmlRootElement;


//@Entity
@XmlRootElement
public class Bar {

	// @Id
	// @GeneratedValue
	private String id;

	// @Constraints.Required(message = "The name is required")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

}