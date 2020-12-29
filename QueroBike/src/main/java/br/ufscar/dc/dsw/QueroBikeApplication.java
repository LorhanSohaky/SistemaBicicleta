package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dao.ICityDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.domain.Rental;
import br.ufscar.dc.dsw.service.impl.RentalService;
import br.ufscar.dc.dsw.service.spec.IRentalService;
import java.io.*;
import java.nio.charset.StandardCharsets;
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
    public CommandLineRunner run(ICityDAO cityDAO, IRentalService rentalService) throws Exception {
        return args -> {
            populateCities(cityDAO);
            populateRentals(rentalService, cityDAO);
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
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
