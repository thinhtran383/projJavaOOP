package proj1;

public interface AccountManagerment {
    boolean signup(Account account);

    boolean login(Account account);

    void logout(Account account);

    boolean remove(Account account);

    boolean recoverAccount(Account account);

    boolean changePassword(Account account);

    void changeAccountInfor(Account account);

    /**
     * Su dung de them mot phuong thuc nao day khong anh huong den he thong dang
     * chay hien tai
     * 
     * Van co the override lai trong class implements interface nay
     * 
     * @param account
     */
    default void deactiveAccount(Account account) {
        System.out.println("Da vo hieu hoa");
    }

    default String radomPassword(Account account) {
        return "";
    }
}
