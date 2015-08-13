package com.oprisklib.constant;

public enum WXEventType {
	SUBSCRIBE("subscribe",1),
	UNSUBSCRIBE("unsubscribe",2),
	LOCATION("LOCATION", 3),
	CLICK("CLICK", 4),
	VIEW("VIEW", 5),
	SCANCODE_PUSH("scancode_push", 6),
	SCANCODE_WAITMSG("scancode_waitmsg", 7),
	PIC_SYSPHOTO("pic_sysphoto", 8),
	PIC_PHOTO_OR_ALBUM("pic_photo_or_album", 9),
	PIC_WEIXIN("pic_weixin", 10),
	LOCATION_SELECT("location_select", 11),
	ENTER_AGENT("enter_agent", 12),
	BATCH_JOB_RESULT("batch_job_result", 13);
	
	
	private String name;
	private int index;
	
	private WXEventType(String name,int index){
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
	
	public static WXEventType getWXEventTypeByName(String name){
		for (WXEventType key : WXEventType.values()) {
            if (key.getName().equalsIgnoreCase(name)) {
                return key;
            }
        }
		
		return null;
	}
}
