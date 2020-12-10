package br.ufscar.dc.dsw.validator;

import br.ufscar.dc.dsw.dao.CityDAO;
import br.ufscar.dc.dsw.domain.City;
import br.ufscar.dc.dsw.utils.ErrorList;

import javax.servlet.http.HttpServletRequest;

public class RentalValidator {

    public static ErrorList registerRentalValidation(
            HttpServletRequest request
    ) {
        ErrorList errors = new ErrorList();

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String cnpj = request.getParameter("cnpj");
        String email = request.getParameter("email");
        String postalCode = request.getParameter("postalCode");
        String streetName = request.getParameter("streetName");
        String neighborhood = request.getParameter("neighborhood");
        String streetNumber = request.getParameter("streetNumber");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        if (password == null || password.length() == 0) {
            errors.add("É obrigatório informar uma senha");
        }

        if (email == null || email.length() == 0) {
            errors.add("É obrigatório informar o e-mail");
        }

        if (cnpj == null || cnpj.length() == 0) {
            errors.add("É obrigatório informar o CPF");
        } else {
            if (cnpj.length() != 14) {
                errors.add("CPF deve conter 14 dígitos");
            }
        }

        if (name == null || name.length() == 0) {
            errors.add("É obrigatório informar o nome");
        }

        if (postalCode == null || postalCode.length() == 0) {
            errors.add("É obrigatório informar o CEP");
        } else {
            if (postalCode.length() != 8) {
                errors.add("CPF deve conter 8 dígitos");
            }
        }

        if (streetName == null || streetName.length() == 0) {
            errors.add("É obrigatório informar a rua");
        }

        if (neighborhood == null || neighborhood.length() == 0) {
            errors.add("É obrigatório informar o bairro");
        }

        if (streetNumber == null || streetNumber.length() == 0) {
            errors.add("É obrigatório informar o número da localização");
        }

        if (city == null || city.length() == 0) {
            errors.add("É obrigatório informar a cidade");
        }

        if (state == null || state.length() == 0) {
            errors.add("É obrigatório informar o estado");
        }

        if (state != null && city != null) {
            City cityItem = new City(city, state);

            CityDAO cityDao = new CityDAO();
            if (!cityDao.hasCity(cityItem)) {
                errors.add("Cidade inválida");
            }
        }

        return errors;
    }

    public static ErrorList editRentalValidation(
            HttpServletRequest request
    ) {
        ErrorList errors = new ErrorList();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String cnpj = request.getParameter("cnpj");
        String email = request.getParameter("email");
        String postalCode = request.getParameter("postalCode");
        String streetName = request.getParameter("streetName");
        String neighborhood = request.getParameter("neighborhood");
        String streetNumber = request.getParameter("streetNumber");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        if (id == null || id.length() == 0) {
            errors.add("É obrigatório informar o id");
        }

        if (email == null || email.length() == 0) {
            errors.add("É obrigatório informar o e-mail");
        }

        if (cnpj == null || cnpj.length() == 0) {
            errors.add("É obrigatório informar o CPF");
        } else {
            if (cnpj.length() != 14) {
                errors.add("CPF deve conter 14 dígitos");
            }
        }

        if (name == null || name.length() == 0) {
            errors.add("É obrigatório informar o nome");
        }

        if (postalCode == null || postalCode.length() == 0) {
            errors.add("É obrigatório informar o CEP");
        } else {
            if (postalCode.length() != 8) {
                errors.add("CPF deve conter 8 dígitos");
            }
        }

        if (streetName == null || streetName.length() == 0) {
            errors.add("É obrigatório informar a rua");
        }

        if (neighborhood == null || neighborhood.length() == 0) {
            errors.add("É obrigatório informar o bairro");
        }

        if (streetNumber == null || streetNumber.length() == 0) {
            errors.add("É obrigatório informar o número da localização");
        }

        if (city == null || city.length() == 0) {
            errors.add("É obrigatório informar a cidade");
        }

        if (state == null || state.length() == 0) {
            errors.add("É obrigatório informar o estado");
        }

        if (state != null && city != null) {
            City cityItem = new City(city, state);

            CityDAO cityDao = new CityDAO();
            if (!cityDao.hasCity(cityItem)) {
                errors.add("Cidade inválida");
            }
        }

        return errors;
    }
}
