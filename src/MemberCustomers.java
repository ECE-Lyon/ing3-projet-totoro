public class MemberCustomers extends Customers{
    public enum enumCategorieAge {REGULAR, SENIOR, CHILDREN}
    private enumCategorieAge categorieAge;
    private String login;
    private String password;

    public MemberCustomers() {

    }
    public MemberCustomers(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public MemberCustomers(String login, String password, enumCategorieAge categorieAge) {
        this.login = login;
        this.password = password;
        this.categorieAge = categorieAge;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}