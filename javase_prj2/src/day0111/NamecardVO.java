package day0111;

public class NamecardVO {
	private String name, addr, img;

	public NamecardVO() {
	}

	public NamecardVO(String name, String addr, String img) {
		this.name = name;
		this.addr = addr;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public String getAddr() {
		return addr;
	}

	public String getImg() {
		return img;
	}
	
}//NamecardVO
