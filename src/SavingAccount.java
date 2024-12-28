
public class SavingAccount extends BankAccount {
	private double interestRate;

	public SavingAccount(int accountNumber, double balance, double interestRate) {
		super(accountNumber, balance);
		this.interestRate=interestRate;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return getBalance()*interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		super.withdraw(amount);
	}

}
