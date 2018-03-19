package eu.dnb.openbankingweb.controller;

import eu.dnb.openbanking.client.OpenBankingService;
import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import eu.dnb.openbankingweb.util.TestDataHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static eu.dnb.openbankingweb.util.TestUtil.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rmang on 19-03-2018.
 */
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OpenBankingService openBankingService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomer() throws Exception {
        String customerId = "12345678902";
        Customer customer = TestDataHelper.createCustomer(customerId);
        when(openBankingService.getCustomer(customerId)).thenReturn(ResponseEntity.ok(customer));
        mockMvc.perform(get("/customers/{customerId}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.customerId", is(customerId)));
        verify(openBankingService, times(1)).getCustomer(customerId);
        verifyNoMoreInteractions(openBankingService);
    }

    @Test
    public void testPatchCustomerSuccess() throws Exception {
        String customerId = "12345678901";
        String newEmail = "b@b.com";
        String newPhone = "+47888888";
        Customer customer = TestDataHelper.createCustomer(customerId);
        Customer updatedCustomer = TestDataHelper.createCustomer(customerId);
        updatedCustomer.setEmail(newEmail);
        updatedCustomer.setPhone(newPhone);

        CustomerPatch customerPatch = new CustomerPatch();
        customerPatch.setEmail(newEmail);
        customerPatch.setPhone(newPhone);

        when(openBankingService.patchCustomer(anyString(), any(CustomerPatch.class))).thenReturn(ResponseEntity.ok(updatedCustomer));
        mockMvc.perform(patch("/customers/{customerId}", customerId)
                .contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON))
                .content(asJsonString(customerPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(newEmail)))
                .andExpect(jsonPath("$.phone", is(newPhone)));
        verify(openBankingService, times(1)).patchCustomer(anyString(), any(CustomerPatch.class));
        verifyNoMoreInteractions(openBankingService);

    }

}
