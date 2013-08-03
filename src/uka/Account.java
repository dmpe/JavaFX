package uka;

public class Account {
	private int accountNumber;
	private double overdraft;
	private double bankDeposit;

	/**
	 * This constructor allows a fast creation of accounts
	 * 
	 * @param accountNumber
	 * @param overdraft
	 * @param bankDeposit
	 */

	public Account(int accountNumber, double overdraft, double bankDeposit) {
		this.accountNumber = accountNumber;
		this.overdraft = overdraft;
		this.bankDeposit = bankDeposit;
	}

	/**
	 * Saves the account number
	 * 
	 * @param value
	 *            accountNumber
	 */
	public void setAccountNumber(int value) {
		this.accountNumber = value;
	}

	/**
	 * 
	 * @return accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Credit you could withdraw. You set a limit here
	 * 
	 * @param value2
	 *            overdraft
	 */
	public void setOverdraft(double value2) {
		this.overdraft = value2;
	}

	/**
	 * 
	 * @return overdraft
	 */
	public double getOverdraft() {
		return overdraft;
	}

	/**
	 * Your initial balance in the account
	 * 
	 * @param value3
	 *            bankDeposit
	 */
	public void setBankDeposit(double value3) {
		this.bankDeposit = value3;
	}

	/**
	 * @return bankDeposit
	 */
	public double getBankDeposit() {
		return bankDeposit;
	}
}
