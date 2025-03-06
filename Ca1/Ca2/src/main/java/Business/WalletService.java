package Business;

import Business.models.Customer;
import Business.models.Person;
import Business.models.Transaction;

import java.util.Optional;

public class WalletService {
    public String AddCredit(Transaction transaction) {
        Optional<Person> person = DataLoader.getUserByUsername(transaction.username());
        if (transaction.credit()<1000){
            throw new IllegalArgumentException("credit must be at least 1000");

        }
        if (person.isEmpty()){
            throw new IllegalArgumentException("user not found");
        }
        Person p = person.get();
        if (!(p instanceof Customer customer)){
            throw new IllegalArgumentException("user not found");
        }

        customer.AddCredit(transaction.credit());

        return "Credit added successfully";
    }
}
