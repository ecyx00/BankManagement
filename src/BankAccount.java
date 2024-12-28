
public abstract class BankAccount {
	protected int accountNumber;
	protected double balance;

	public BankAccount(int accountNumber, double balance) {
		this.accountNumber=accountNumber;
		this.balance=balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount) {
		if(amount>0) {
			balance+=amount;
			System.out.println("Deposited: " +amount);
		} else {
			System.out.println("Invalid deposit amount.");
		}
	}
	
	public void withdraw(double amount) {
		if(amount>0&&amount<=balance) {
			balance-=amount;
			System.out.println("Withdrew: "+amount);
		} else {
			System.out.println("Invalid withdrawal amount!");
		}
	}
	
	public abstract double calculateInterest();
	
}
