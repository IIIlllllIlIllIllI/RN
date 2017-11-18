package Server;


public class Header {
private String name;
private String value;
public Header(String name,String value) {
	this.name=name;
	this.value=value;
}
public String getName() {
	return this.name;
}
public String getValue() {
	return this.value;
}
public String toString() {
	return this.getName()+": "+this.getValue()+"\r\n";
}
}
