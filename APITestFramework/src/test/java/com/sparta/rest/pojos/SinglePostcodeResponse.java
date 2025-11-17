package com.sparta.rest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SinglePostcodeResponse{

	@JsonProperty("result")
	private Result result;

	@JsonProperty("status")
	private int status;

	public Result getResult(){
		return result;
	}

	public int getStatus(){
		return status;
	}
}