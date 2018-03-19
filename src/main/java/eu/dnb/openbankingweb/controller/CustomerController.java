package eu.dnb.openbankingweb.controller;

import eu.dnb.openbanking.client.OpenBankingService;
import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rmang on 17-03-2018.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private OpenBankingService openBankingService;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        ResponseEntity<Customer> customerResponseEntity = openBankingService.getCustomer(customerId);
        return customerResponseEntity.getBody();
    }

    @PatchMapping("/{customerId}")
    public Customer patchCustomer(@PathVariable String customerId, @RequestBody CustomerPatch customerPatch) {
        ResponseEntity<Customer> customerResponseEntity = openBankingService.patchCustomer(customerId, customerPatch);
        return customerResponseEntity.getBody();
    }
}