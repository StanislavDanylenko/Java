package sessions;

public class AccountServiceController implements AccountServiceControllerMBean {

    private final AccountManager accountManager;

    public AccountServiceController(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public int getUsers() {
        return accountManager.getUserCount();
    }
}
