
public class CheckingAccount extends BankAccount {
	private double transactionFee;

	public CheckingAccount(int accountNumber, double balance, double transactionFee) {
		super(accountNumber, balance);
		this.transactionFee=transactionFee;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		double totalAmount=amount+transactionFee;
		if(totalAmount<=getBalance()) {
		super.withdraw(totalAmount);
		System.out.println("Transaction Fee: "+transactionFee);
		} else {
			System.out.println("Insufficient balance for withdrawal including transaction fee!");
		}
	}
}
