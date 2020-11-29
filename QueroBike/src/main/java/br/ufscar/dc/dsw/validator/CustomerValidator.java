package br.ufscar.dc.dsw.validator;

import br.ufscar.dc.dsw.utils.DateParser;
import br.ufscar.dc.dsw.utils.ErrorList;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CustomerValidator {

    public static ErrorList registerCustomerValidation(
            HttpServletRequest request
    ) {
        ErrorList errors = new ErrorList();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpf = request.getParameter("cpf");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdateString = request.getParameter("birthdate");

        if (email == null || email.length() == 0) {
            errors.add("É obrigatório informar o e-mail");
        }

        if (password == null || password.length() == 0) {
            errors.add("É obrigatório informar a senha");
        }

        if (cpf == null || cpf.length() == 0) {
            errors.add("É obrigatório informar o CPF");
        } else {
            if (cpf.length() != 11) {
                errors.add("CPF deve conter 11 dígitos");
            }
        }

        if (name == null || name.length() == 0) {
            errors.add("É obrigatório informar o nome");
        }

        if (phone == null || phone.length() == 0) {
            errors.add("É obrigatório informar o telefone");
        } else {
            final String regex = "^55\\d{10,11}$";

            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(phone);

            if (!matcher.matches()) {
                errors.add(
                        "O telefone deve estar no formato: 55XXYYYYYYYYY ou 55XXYYYYYYYY"
                );
            }
        }

        if (gender == null || gender.length() == 0) {
            errors.add("É obrigatório informar o gênero");
        }

        if (birthdateString == null || birthdateString.length() == 0) {
            errors.add("É obrigatório informar a data de nascimento");
        } else {
            try {
                Date birthdate = DateParser.format(birthdateString, "dd/MM/yyyy");

                if (birthdate.compareTo(new Date()) >= 0) {
                    errors.add("Data de nascimento inválida");
                }
            } catch (ParseException e) {
                errors.add(
                        "O formato da data de nascimento é inválido. Deve ser dia/mês/ano"
                );
            }
        }

        return errors;
    }

    public static ErrorList loginCustomerValidation(HttpServletRequest request) {
        ErrorList errors = new ErrorList();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.length() == 0) {
            errors.add("É obrigatório informar o e-mail");
        }

        if (password == null || password.length() == 0) {
            errors.add("É obrigatório informar a senha");
        }

        return errors;
    }
    
    public static ErrorList deleteCustomerValidation(HttpServletRequest request){
        ErrorList errors = new ErrorList();

        String idString = request.getParameter("id");
        

        if (idString == null || idString.length() == 0) {
            errors.add("É obrigatório informar o id");
        }

        return errors;
    }
}
