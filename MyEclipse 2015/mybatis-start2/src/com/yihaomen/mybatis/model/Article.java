package com.yihaomen.mybatis.model;

public class Article {
	  private int aid;
	    private int userId;
	    private String title;
	    private String content;
	    
	    
		public Article(int userId, String title, String content) {
			super();
			this.userId = userId;
			this.title = title;
			this.content = content;
		}
		
		public Article() {
			super();
		}

		public int getAid() {
			return aid;
		}

		public void setAid(int aid) {
			this.aid = aid;
		}


		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
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
}
