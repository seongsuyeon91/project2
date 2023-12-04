package com.study.jsp.dto;

import java.sql.Timestamp;

public class MDto
{
	private String mId;
	private String mName;
	private String mPwd;
	private String mPhone;
	private String mEmail;
	
	public MDto()
	{
		super();
	}

	public MDto(String mId, String mName, String mPwd, String mPhone, String mEmail)
	{
		super();
		this.mId = mId;
		this.mName = mName;
		this.mPwd = mPwd;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
	}

	public String getmId()
	{
		return mId;
	}

	public void setmId(String mId)
	{
		this.mId = mId;
	}

	public String getmName()
	{
		return mName;
	}

	public void setmName(String mName)
	{
		this.mName = mName;
	}

	public String getmPwd()
	{
		return mPwd;
	}

	public void setmPwd(String mPwd)
	{
		this.mPwd = mPwd;
	}

	public String getmPhone()
	{
		return mPhone;
	}

	public void setmPhone(String mPhone)
	{
		this.mPhone = mPhone;
	}

	public String getmEmail()
	{
		return mEmail;
	}

	public void setmEmail(String mEmail)
	{
		this.mEmail = mEmail;
	}

}