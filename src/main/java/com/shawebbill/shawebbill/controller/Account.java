package com.shawebbill.shawebbill.controller;

	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import com.fasterxml.jackson.annotation.JsonProperty;

	import java.util.List;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Account {
		public List<AccountData> data;

	    public List<AccountData> getData() {
	        return this.data;
	    }

	}

