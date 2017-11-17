package Server;

public class StatusLine {
	private ProtocolVersion protocolVersion;
	private String reason;
	private int statusCode;

	public StatusLine(ProtocolVersion protocolVersion,String reason,int statusCode) {
		this.protocolVersion=protocolVersion;
		this.reason=reason;
		this.statusCode=statusCode;
	}
	public ProtocolVersion getProtocolVersion() {
		return this.protocolVersion;
	}

	public String getReasonPhrase() {
		return this.reason;
	}

	public int getStatusCode() {
		return this.statusCode;
	}
	public String toString() {
		return this.getProtocolVersion()+" "+this.getStatusCode()+" "+this.getReasonPhrase()+" \r\n";
	}
}
