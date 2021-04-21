import org.example.AccountModel;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    @Test
    public void getName(){
        AccountModel accountModel = new AccountModel("henrik", "hejsan", 100);
        Assert.assertEquals("henrik", accountModel.getUserName());
    }

    @Test
    public void getBalance(){
        AccountModel accountModel = new AccountModel("henrik", "hejsan", 100.0);
        Assert.assertEquals(100.0, accountModel.getBalance(), 0.01);
    }
}
