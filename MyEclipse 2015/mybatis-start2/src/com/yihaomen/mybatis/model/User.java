package com.yihaomen.mybatis.model;

public class User {
    
    private int id;
    private String userName;
    private String userAge;
    private String userAddress;
    
    
    public User() {
		super();
	}

    
	public User(int id) {
		super();
		this.id = id;
	}


	public User(String userName, String userAge, String userAddress) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.userAddress = userAddress;
	}
    
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAge() {
        return userAge;
    }
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

}