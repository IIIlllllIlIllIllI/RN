package Server;

public class ProtocolVersion {
protected int major;
protected int minor;
protected String protocol;
public ProtocolVersion(String protocol,int major,int minor) {
	this.protocol=protocol;
	this.major=major;
	this.minor=minor;
}
public int getMajor() {
	return this.major;
}
public int getMinor() {
	return this.minor;
}
public String getProtocol() {
	return this.protocol;
}
public String toString() {
	return this.getProtocol()+"/"+this.getMajor()+"."+this.getMinor();
}
}
