package com.shawebbill.shawebbill.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
	public class AccountData {
		public int accountId;
		public String accountNumber;
		public String accountType;
		public String description;
		public int bankAccountId;

		public String getAccountName() {
			return this.accountNumber;
		}
	}
