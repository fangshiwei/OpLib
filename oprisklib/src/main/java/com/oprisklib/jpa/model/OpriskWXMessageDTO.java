package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPRISK_WX_MESSAGE", catalog ="OPRISKLIB")
public class OpriskWXMessageDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPRISK_WX_MESSAGE_ID")
	private Integer opriskWxMessageId;
	
	@Column(name="TO_USER_NAME")
	private String ToUserName;
	
	@Column(name="FROM_USER_NAME")
	private String FromUserName;
	
	@Column(name="CREATE_TIME")
	private Integer CreateTime;
	
	@Column(name="MSG_TYPE")
	private String MsgType;
	
	@Column(name="MSG_ID")
	private Integer MsgId;
	
	@Column(name="EVENT")
	private String Event;
	
	@Column(name="EVENT_KEY")
	private String EventKey;
	
	@Column(name="TICKET")
	private String Ticket;
	
	@Column(name="LATITUDE")
	private String Latitude;
	
	@Column(name="LONGITUDE")
	private String Longitude;
	
	@Column(name="PRECISION")
	private String Precision;
	
	@Column(name="PIC_URL")
	private String PicUrl;
	
	@Column(name="MEDIA_ID")
	private Integer MediaId;
	
	@Column(name="TITLE")
	private String Title;
	
	@Column(name="DESCRIPTION")
	private String Description;
	
	@Column(name="URL")
	private String Url;
	
	@Column(name="LOCATION_X")
	private String Location_X;
	
	@Column(name="LOCATION_Y")
	private String Location_Y;
	
	@Column(name="SCALE")
	private String Scale;
	
	@Column(name="LABEL")
	private String Label;
	
	@Column(name="CONTENT")
	private String Content;
	
	@Column(name="FORMAT")
	private String Format;
	
	@Column(name="RECOGNITION")
	private String Recognition;
	
	@Column(name="SCAN_CODE_INFO")
	private String ScanCodeInfo;
	
	@Column(name="SCAN_TYPE")
	private String ScanType;
	
	@Column(name="SCAN_RESULLT")
	private String ScanResult;
	
	@Column(name="AGENT_ID")
	private Integer AgentID;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Integer getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Integer createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Integer getMsgId() {
		return MsgId;
	}
	public void setMsgId(Integer msgId) {
		MsgId = msgId;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public Integer getMediaId() {
		return MediaId;
	}
	public void setMediaId(Integer mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}
	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	public Integer getAgentID() {
		return AgentID;
	}
	public void setAgentID(Integer agentID) {
		AgentID = agentID;
	}
	public Integer getOpriskWxMessageId() {
		return opriskWxMessageId;
	}
	public void setOpriskWxMessageId(Integer opriskWxMessageId) {
		this.opriskWxMessageId = opriskWxMessageId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
			
		
}
