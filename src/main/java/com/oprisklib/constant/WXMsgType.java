package com.oprisklib.constant;

public enum WXMsgType {
	TEXT("text",1),
	IMAGE("image",2),
	VOICE("voice", 3),
	VIDEO("video",4),
	SHORTVIDEO("shortvideo",5),
	LOCATION("location", 6),
	EVENT("event", 7);
	
	private String name;
	private int index;
	
	private WXMsgType(String name,int index){
		this.name = name;
		this.index = index;
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public static WXMsgType getWXMsgTypeByName(String name){
		for (WXMsgType key : WXMsgType.values()) {
            if (key.getName().equalsIgnoreCase(name)) {
                return key;
            }
        }
		
		return null;
	}
	
}
