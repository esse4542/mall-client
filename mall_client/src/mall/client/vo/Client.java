package mall.client.vo;

public class Client {
	private int clientNo;
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public String getClientPw() {
		return clientPw;
	}
	public void setClientPw(String clientPw) {
		this.clientPw = clientPw;
	}
	public String getClientDate() {
		return clientDate;
	}
	public void setClientDate(String clientDate) {
		this.clientDate = clientDate;
	}
	private String clientMail;
	private String clientPw;
	private String clientDate;
	@Override
	public String toString() {
		return "Client [clientNo=" + clientNo + ", clientMail=" + clientMail + ", clientPw=" + clientPw
				+ ", clientDate=" + clientDate + "]";
	}
}
