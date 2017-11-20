package Server;

public class Message {

	private ProtocolVersion protocolVersion;
	private Header[] headers;
	private StatusLine statusLine;
	private byte[] body;

	public Message(byte[] readAllBytes, String type) {
		this.headers = new Header[0];
		this.protocolVersion = new ProtocolVersion("HTTP", 0, 9);
		this.statusLine = new StatusLine(this.protocolVersion, "OK", 200);
		type = type.toLowerCase();
		this.body = readAllBytes;
		switch (type) {
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
		this.addHeader("Content-Length", "" + this.body.length);
		this.addHeader("Connection", "Closed");
	}

	public byte[] getBytes() {
		String response = this.statusLine.toString();
		for (int i = 0; i < headers.length; i++) {
			response += headers[i].toString();
		}
		response += "\r\n";
		byte[] statusANDheaders = response.getBytes();
		byte[] all = new byte[statusANDheaders.length + this.body.length];
		for (int i = 0; i < statusANDheaders.length; i++) {
			all[i] = statusANDheaders[i];
		}
		for (int i = 0; i < this.body.length; i++) {
			all[i + statusANDheaders.length] = this.body[i];
		}
		return all;
	}

	public void addHeader(Header header) {
		Header[] oldHeaders = this.getAllHeaders();
		this.headers = new Header[this.headers.length + 1];
		int i = 0;
		for (Header h : oldHeaders) {
			this.headers[i++] = h;
		}
		this.headers[i] = header;
	}

	public void addHeader(String name, String value) {
		Header header = new Header(name, value);
		this.addHeader(header);
	}

	public Header[] getAllHeaders() {
		return this.headers.clone();
	}

	public ProtocolVersion getProtocolVersion() {
		return this.protocolVersion;
	}

}
