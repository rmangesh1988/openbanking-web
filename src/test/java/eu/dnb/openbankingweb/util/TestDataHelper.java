package eu.dnb.openbankingweb.util;

import eu.dnb.openbanking.domain.Address;
import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.CustomerEngagement;

import java.util.Arrays;

/**
 * Created by rmang on 19-03-2018.
 */
public class TestDataHelper {
    public static Customer createCustomer(String customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerType(Customer.CustomerType.PERSON);
        customer.setFirstName("Rune");
        customer.setLastName("Bjerke");
        customer.setAddress(createAddress());
        customer.setEmail("a@a.com");
        customer.setPhone("+474445555");
        customer.setCountryCitizenship("NO");
        customer.setCountryOfBirth("NO");
        customer.setCountryTax("NO");
        customer.setCustomerEngagements(Arrays.asList(createCustomerEngagement()));
        return customer;
    }

    private static CustomerEngagement createCustomerEngagement() {
        CustomerEngagement customerEngagement = new CustomerEngagement();
        customerEngagement.setAccountNumber("12345678901");
        customerEngagement.setCorporate(true);
        customerEngagement.setEngagementId("2222");
        customerEngagement.setFriendlyName("Mastercard");
        return customerEngagement;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setId(2222L);
        address.setPostalAddressLine1("postal address 1");
        address.setPostalAddressLine2("postal address 2");
        address.setPostalAddressLine3("postal address 3");
        address.setPostCode("0357");
        address.setPostCity("Oslo");
        address.setPostCountry("NO");
        return address;
    }


}
