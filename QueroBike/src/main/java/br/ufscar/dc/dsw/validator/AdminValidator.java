package br.ufscar.dc.dsw.validator;

import br.ufscar.dc.dsw.utils.ErrorList;

import javax.servlet.http.HttpServletRequest;

public class AdminValidator {

    
    public static ErrorList loginAdminValidation(HttpServletRequest request) {
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
    
    public static ErrorList updateAdminValidation(
            HttpServletRequest request
    ) {
        ErrorList errors = new ErrorList();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String idString = request.getParameter("id");
        

        if (idString == null || idString.length() == 0) {
            errors.add("É obrigatório informar o id");
        }

        if (email == null || email.length() == 0) {
            errors.add("É obrigatório informar o e-mail");
        }

        if (password == null || password.length() == 0) {
            errors.add("É obrigatório informar a senha");
        }

        if (name == null || name.length() == 0) {
            errors.add("É obrigatório informar o nome");
        }

        return errors;
    }
}
