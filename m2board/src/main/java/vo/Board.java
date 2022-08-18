package vo;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardDetail;
	private String boardWriter;
	private String createDate;
	private int viewer;
	private int nice;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardDetail() {
		return boardDetail;
	}
	public void setBoardDetail(String boardDetail) {
		this.boardDetail = boardDetail;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getViewer() {
		return viewer;
	}
	public void setViewer(int viewer) {
		this.viewer = viewer;
	}
	public int getNice() {
		return nice;
	}
	public void setNice(int nice) {
		this.nice = nice;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardDetail=" + boardDetail
				+ ", boardWriter=" + boardWriter + ", createDate=" + createDate + ", viewer=" + viewer + ", nice="
				+ nice + "]";
	}
	
	

}
