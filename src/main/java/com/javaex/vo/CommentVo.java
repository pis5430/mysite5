package com.javaex.vo;

public class CommentVo {
	
	public int no; //글번호 pk
	public int user_no; //회원식별번호 fk
	public String title; //제목
	public String content; //sodyd
	public int hit; //조회수
	public String reg_date; //등록일
	public int group_no; //그룹번호
	public int order_no; //그룹내 글순서
	public int depth; // 깊이(들여쓰기)
	
	public String name;// users name
	
	//기본생성자
	public CommentVo() {}

	//전체
	public CommentVo(int no, int user_no, String title, String content, int hit, String reg_date, int group_no,
			int order_no, int depth , String name) {
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	//일반메소드
	@Override
	public String toString() {
		return "CommentBoardVo [no=" + no + ", user_no=" + user_no + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no=" + order_no
				+ ", depth=" + depth + "]";
	}
	
	
	
	
	

}
