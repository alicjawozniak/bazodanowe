package alicjawozniak;

import alicjawozniak.model.*;
import alicjawozniak.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Service
public class Bootstrap {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompanyBranchRepository companyBranchRepository;
    @Autowired
    private CompanyCarRepository companyCarRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void generateData() {


        createAdmins();


        Employee employee1 = createEmployee(1);
        Client client1 = createClient(1);
        createEmployee(2);
        createClient(2);
        createEmployee(3);
        createClient(3);
        Employee employee0 = createEmployee(0);
        Client client0 = createClient(0);
        employee0.setClients(Arrays.asList(client0, client1));
        employeeRepository.save(employee0);

        client0.setEmployees(Arrays.asList(employee0, employee1));
        clientRepository.save(client0);

    }

    private void createAdmins() {
        Admin admin = new Admin();
        admin.setLogin("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        adminRepository.save(admin);
    }


    private Employee createEmployee(int i) {
        Employee employee1 = new Employee();

        employee1.setFirstName("Jan" + i);
        employee1.setLastName("Nowak" + i);
        employee1.setEmail("emp" + i + "@test.com");
        employee1.setPhoneNo("111783347" + i);
        employee1.setPesel("11663784936" + i);

        Address address = new Address();
        address.setCity("Warszawa");
        address.setStreet("Marszałkowska" + i);
        address.setStreetNumber("1" + i);
        address.setZipCode("11-111" + i);
        address = addressRepository.save(address);
        employee1.setAddress(address);

        Position position = new Position();
        position.setName("Robak" + i);
        position.setSalary(1000);
        position = positionRepository.save(position);
        employee1.setPosition(position);

        CompanyCar companyCar = new CompanyCar();
        companyCar.setRegistrationNo("1cdfjin" + i);
        companyCar.setMileage(10l + i);
        companyCar = companyCarRepository.save(companyCar);
        employee1.setCompanyCar(companyCar);

        employee1.setClients(Collections.emptyList());

        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("13464736782" + i);
        bankAccount.setActive(true);
        bankAccount = bankAccountRepository.save(bankAccount);
        employee1.setBankAccount(bankAccount);


        CompanyBranch companyBranch = new CompanyBranch();
        companyBranch.setName("Oddzial 1" + i);
        companyBranch.setCity("Warszawa");
        companyBranch = companyBranchRepository.save(companyBranch);
        employee1.setCompanyBranch(companyBranch);

        employee1 = employeeRepository.save(employee1);


        companyCar.setEmployee(employee1);
        companyCarRepository.save(companyCar);

        return employee1;
    }


    private Client createClient(int i) {
        Client client = new Client();
        client.setName("Orange" + i);
        client.setPhoneNo("23862393" + i);
        client.setEmail("orange" + i + "@test.com");

        Contract contract = new Contract();
        contract.setSignDate(LocalDate.now().minusYears(1l + i));
        contract.setValue(2672 + i);
        contract = contractRepository.save(contract);

        Contract contract2 = new Contract();
        contract2.setSignDate(LocalDate.now().minusYears(2l + i));
        contract2.setValue(378389 + i);
        contract2 = contractRepository.save(contract2);


        client.setContracts(Arrays.asList(contract, contract2));
        client.setEmployees(Collections.emptyList());
        return clientRepository.save(client);
    }
}
