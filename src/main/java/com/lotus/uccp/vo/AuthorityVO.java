package  com.lotus.uccp.vo;

public class AuthorityVO   {

	private int id;
	private String name;
	

	public AuthorityVO() {
	}

	public AuthorityVO(int id) {
		this.id = id;
	}

	public AuthorityVO(int id, String name) {
		this.id = id;
		this.name = name;
		
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
