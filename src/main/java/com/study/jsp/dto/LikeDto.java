package com.study.jsp.dto;

public class LikeDto
{
		private String likeId;
		private String mId;
		private String bId;
		
		public LikeDto()
		{

		}

		public LikeDto(String likeId, String mId, String bId)
		{

			this.likeId = likeId;
			this.mId = mId;
			this.bId = bId;
		}

		public String getLikeId()
		{
			return likeId;
		}

		public void setLikeId(String likeId)
		{
			this.likeId = likeId;
		}

		public String getmId()
		{
			return mId;
		}

		public void setmId(String mId)
		{
			this.mId = mId;
		}

		public String getbId()
		{
			return bId;
		}

		public void setbId(String bId)
		{
			this.bId = bId;
		}

}
