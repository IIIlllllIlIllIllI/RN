package Server;

public class Message {

	private String method;
	private ProtocolVersion protocolVersion;
	private Header[] headers;
	private StatusLine statusLine;
	private String body;

	public Message(String body,String type) {
		this.method = "GET";
		this.headers=new Header[0];
		this.protocolVersion=new ProtocolVersion("HTTP", 0, 9);
		this.statusLine=new StatusLine(this.protocolVersion, this.method, 200);
		this.body=body;
		switch(type) {
		case "html":
			this.addHeader("Content-Type", "text/html");
			break;
		case "gif":
			this.addHeader("Content-Type", "image/gif");
			break;
		case "png":
			this.addHeader("Content-Type", "image/png");
			break;
		default:
			this.addHeader("Content-Type", "text/plain");
		}
		
//		this.addHeader("Content-Length", ""+this.body.length());
//		this.addHeader("Connection" ,"Closed");
	}

	public byte[] getBytes() {
		String response=this.statusLine.toString();
		for (int i = 0; i < headers.length; i++) {
			response+=headers[i].toString();
		}
		response+="\r\n";
		response+=this.body;
		return response.getBytes();
	}

	public void addHeader(Header header) {
		Header[] oldHeaders=this.getAllHeaders();
		this.headers=new Header[this.headers.length+1];
		int i=0;
		for(Header h:oldHeaders) {
			this.headers[i++]=h;
		}
		this.headers[i]=header;
	}

	public void addHeader(String name, String value) {
		Header header=new Header(name,value);
		this.addHeader(header);
	}

	public Header[] getAllHeaders() {
		return this.headers.clone();
	}

	public ProtocolVersion getProtocolVersion() {
		return this.protocolVersion;
	}

}
