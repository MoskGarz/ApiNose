package co.edu.uco.nose.test;

import java.util.UUID;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.CityDTO;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import co.edu.uco.nose.dto.UserDTO;
import co.edu.uco.nose.entity.UserEntity;

public class TestUserRegistration {
    public static void main(String[] args) {

        try {
            var user = new UserDTO();
            //Colocar todos los parametros, menos el id
            UUID tipoId = UUID.fromString("CB5F0DDF-3CE7-4DCF-BFFF-EB75ED331DED"); // Cédula de Ciudadanía
            UUID ciudadId = UUID.fromString("B4F32926-415A-41BE-AE68-B6EAF062B8F5"); // Bogotá


            user.setIdentificationType(new IdentificationTypeDTO(tipoId));
            user.setCity(new CityDTO(ciudadId));


            user.setIdentificationNumber("39443435");
            user.setFirstName("Mary"); 
            user.setSecondName(null);    
            user.setFirstLastname("Mosquera");  
            user.setSecondLastname("Garzón");   
            user.setEmail("santimosk@gmail.com");  
            user.setPhoneNumber("3137943391"); 
            user.setEmailVerified(false);
            user.setPhoneNumberVerified(false);

            var dao = co.edu.uco.nose.data.dao.factory.DAOFactory.getFactory().getUserDAO();

            // 1) Probar duplicado por correo
            var filtroCorreo = new co.edu.uco.nose.entity.UserEntity();
            filtroCorreo.setEmail("moskgarz@example.com");
            System.out.println("findByFilter correo -> " + dao.findByFilter(new UserEntity()).size());

            // 2) Probar duplicado por tel
            var filtroTel = new co.edu.uco.nose.entity.UserEntity();
            filtroTel.setPhoneNumber("3332499998");
            System.out.println("findByFilter tel -> " + dao.findByFilter(filtroTel).size());

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
