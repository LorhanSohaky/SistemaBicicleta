package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dao.IAdminDAO;
import br.ufscar.dc.dsw.dao.ICityDAO;
import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.service.spec.IAdminService;
import br.ufscar.dc.dsw.service.spec.ICustomerService;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.StringTokenizer;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.*;

@SpringBootApplication()
public class QueroBikeApplication {

    private static final Logger log = LoggerFactory.getLogger(QueroBikeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QueroBikeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ICityDAO cityDAO, IRentalService rentalService, IAdminService adminService, ICustomerService customerService) throws Exception {
        return args -> {
            if (adminService.listAll().size() < 2) {
                log.info("Populando admins");
                populateAdmins(adminService);
            }

            if (cityDAO.findAll().size() < 5570) {
                log.info("Populando cidades");
                populateCities(cityDAO);
            }

            if (rentalService.listAll().size() < 40) {
                log.info("Populando locadoras");
                populateRentals(rentalService, cityDAO);
            }
            
            if(customerService.listAll().size() < 1) {
                log.info("Populando clientes");
                populateCustomers(customerService);
            }
        };
    }

    private static void populateCities(ICityDAO dao) {
        String line;
        try {
            Resource resource = new ClassPathResource("cities.csv");
            InputStream stream = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            line = reader.readLine();

            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                City city = new City();
                String name = tokenizer.nextToken().trim();
                String state = tokenizer.nextToken().trim();
                city.setName(name);
                city.setState(state);
                dao.save(city);

                line = reader.readLine();
            }
            stream.close();
            isr.close();
            reader.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static void populateRentals(IRentalService service, ICityDAO cityDAO) {
        String line;
        try {
            Resource resource = new ClassPathResource("rentals.csv");
            InputStream stream = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            line = reader.readLine();

            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                String name = tokenizer.nextToken().trim();
                String cnpj = tokenizer.nextToken().trim();
                String email = tokenizer.nextToken().trim();
                String description = tokenizer.nextToken().trim();
                String postalCode = tokenizer.nextToken().trim();
                String streetName = tokenizer.nextToken().trim();
                String neighborhood = tokenizer.nextToken().trim();
                String streetNumber = tokenizer.nextToken().trim();
                int cityId = Integer.parseInt(tokenizer.nextToken().trim());
                String rawPassword = tokenizer.nextToken().trim();

                Rental rental = new Rental();

                rental.setName(name);
                rental.setCnpj(cnpj);
                rental.setEmail(email);
                rental.setDescription(description);
                rental.setPostalCode(postalCode);
                rental.setStreetName(streetName);
                rental.setNeighborhood(neighborhood);
                rental.setStreetNumber(streetNumber);
                rental.setPassword(rawPassword);

                City city = cityDAO.findById(cityId);
                rental.setCity(city);

                service.save(rental);

                line = reader.readLine();
            }
            stream.close();
            isr.close();
            reader.close();
        } catch (IOException | NumberFormatException e) {
            log.error(e.getMessage());
        }
    }

    private static void populateAdmins(IAdminService adminService) {
        Admin admin1 = new Admin();
        admin1.setEmail("admin1@mailinator.com");
        admin1.setName("Lorhan Sohaky");
        admin1.setPassword("password123");
        adminService.save(admin1);

        Admin admin2 = new Admin();
        admin2.setEmail("admin2@mailinator.com");
        admin2.setName("Lucas Martins");
        admin2.setPassword("password123");
        adminService.save(admin2);
    }
    
    private static void populateCustomers(ICustomerService customerService) {
        Customer customer1 = new Customer();
        customer1.setEmail("customer1@mailinator.com");
        customer1.setName("Lorhan Sohaky");
        customer1.setPassword("password123");
        customer1.setCPF("01234567891");
        customer1.setGender("male");
        customer1.setPhone("5511912345678");
        customer1.setBirthdate(new Date(1998, 1, 1));
        customerService.save(customer1);
        
        Customer customer2 = new Customer();
        customer2.setEmail("customer2@mailinator.com");
        customer2.setName("Lucas Martins");
        customer2.setPassword("password123");
        customer2.setCPF("01234567892");
        customer2.setGender("male");
        customer2.setPhone("5511912345678");
        customer2.setBirthdate(new Date(1998, 1, 1));
        customerService.save(customer2);
    }
}
