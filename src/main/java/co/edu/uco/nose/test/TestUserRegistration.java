package co.edu.uco.nose.test;

import java.util.UUID;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import co.edu.uco.nose.dto.UserDTO;

public class TestUserRegistration {
    public static void main(String[] args) {

        try {
            var user = new UserDTO();
            UUID tipoId = UUID.fromString("CB5F0DDF-3CE7-4DCF-BFFF-EB75ED331DED"); // Cédula de Ciudadanía
            UUID ciudadId = UUID.fromString("B4F32926-415A-41BE-AE68-B6EAF062B8F5"); // Bogotá


            user.setIdentificationType(new IdentificationTypeDTO(tipoId));
            user.setCity(new CityDTO(ciudadId));


            user.setIdentificationNumber("1035971461");
            user.setFirstName("Felipe");
            user.setSecondName("");
            user.setFirstLastname("Mosquera");
            user.setSecondLastname("Garzón");
            user.setEmail("felipemoskg@gmail.com");
            user.setPhoneNumber("3332499998");
            user.setEmailVerified(false);
            user.setPhoneNumberVerified(false);

            var facade = new UserFacadeImpl();
            facade.registerNewUser(user);

            System.out.println("Gane el semestre");
        }catch (NoseException e){
            System.out.println(e.getUserMessage());
            System.out.println(e.getTechnicalMessage());
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
