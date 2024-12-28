
public class InvestmentAccount extends BankAccount {
	private double investmentRate;
	private String riskLevel;

	public InvestmentAccount(int accountNumber, double balance, String riskLevel) {
		super(accountNumber, balance);
		this.riskLevel=riskLevel.toLowerCase();
		// TODO Auto-generated constructor stub
		
		switch(this.riskLevel) {
		case "low":
			this.investmentRate=0.003;
			break;
		case "medium":
			this.investmentRate=0.005;
			break;
		case "high":
			this.investmentRate=0.008;
			break;
		default:
            System.out.println("Invalid risk level! Defaulting to 'low'.");
            this.riskLevel = "low";
            this.investmentRate = 0.03;
		}
	}

	public double getInvestmentRate() {
		return investmentRate;
	}

	public String getRiskLevel() {
		return riskLevel;
	}
	

	@Override
	public double calculateInterest() {
		// TODO Auto-generated method stub
		return getBalance()*investmentRate;
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		super.withdraw(amount);
	}
	

}
