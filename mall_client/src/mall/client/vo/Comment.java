package mall.client.vo;

public class Comment {
	private int commentNo;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	private int noticeNo;
	private String managerId; // fk?Œ“ê¸? ì§??š¸?•Œ ?•„?š”
	private String commentContent;
	private String commentDate;
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", noticeNo=" + noticeNo + ", managerId=" + managerId
				+ ", commentContent=" + commentContent + ", commentDate=" + commentDate + "]";
	}
	
}	
