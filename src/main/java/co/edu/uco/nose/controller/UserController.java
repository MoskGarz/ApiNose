package co.edu.uco.nose.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.nose.dto.UserDTO;
import co.edu.uco.nose.dto.IdentificationTypeDTO;
import co.edu.uco.nose.dto.CityDTO;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@GetMapping("/dummy")
	public UserDTO getUserDTODummy() {
		return new UserDTO();
	}

    @GetMapping
    public ResponseEntity<Response<UserDTO>> findByFilter(
		@RequestParam(name = "identification-type-id", required = false) UUID identificationTypeId,
        @RequestParam(name = "identification-number", required = false) String identificationNumber,
        @RequestParam(name = "first-name", required = false) String firstName,
        @RequestParam(name = "second-name", required = false) String secondName,
        @RequestParam(name = "first-last-name", required = false) String firstLastName,
        @RequestParam(name = "second-last-name", required = false) String secondLastName,
        @RequestParam(name = "email", required = false) String email,
        @RequestParam(name = "phone-number", required = false) String phoneNumber,
        @RequestParam(name = "city-id", required = false) UUID cityId

    ){

		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
            var userDTO = new UserDTO();
            userDTO.setIdentificationNumber(identificationNumber);
            userDTO.setIdentificationType(new IdentificationTypeDTO(identificationTypeId));
            userDTO.setFirstName(firstName);
            userDTO.setSecondName(secondName);
            userDTO.setFirstLastname(firstLastName);
            userDTO.setSecondLastname(secondLastName);
            userDTO.setEmail(email);
            userDTO.setPhoneNumber(phoneNumber);
            userDTO.setCity(new CityDTO(cityId));

            var facade = new UserFacadeImpl();
			responseObjectData.setData(facade.findUsersByFilter(userDTO));
			responseObjectData.addMessage(MessagesEnum.USER_SUCCESS_USERS_FILTERED.getContent());
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserDTO>> findSpecificUser( @PathVariable UUID id){
        
        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
            List<UserDTO> user = new ArrayList<>();
            user.add(facade.findSpecificUser(id));
			responseObjectData.setData(user);
			responseObjectData.addMessage(MessagesEnum.USER_SUCCESS_USER_FOUND.getContent());
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

		return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

	@PostMapping
	public ResponseEntity<Response<UserDTO>> registerNewUserInformation(@RequestBody UserDTO user) {

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.CREATED;

		try {
			var facade = new UserFacadeImpl();
            facade.registerNewUser(user);
			responseObjectData.addMessage(MessagesEnum.USER_SUCCESS_USER_REGISTERED.getContent());
            
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> updateUserInformation(@PathVariable UUID id, @RequestBody UserDTO user) {

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
            facade.updateUserInformation(id, user);
			responseObjectData.addMessage(MessagesEnum.USER_SUCCESS_USER_UPDATED.getContent());
            
		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
        } catch (final Exception exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent());
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<UserDTO>> dropUserInformation(@PathVariable UUID id) {

        Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		HttpStatusCode responseStatusCode = HttpStatus.OK;

		try {
			var facade = new UserFacadeImpl();
            facade.dropUser(id);
			responseObjectData.addMessage(MessagesEnum.USER_SUCCESS_USER_DELETED.getContent());  

		} catch (final NoseException exception) {
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(exception.getUserMessage());
			responseStatusCode = HttpStatus.BAD_REQUEST;
			exception.printStackTrace();
		} catch (final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_PERSISTENCE_UNEXPECTED.getContent();
			responseObjectData = Response.createFailedResponse();
			responseObjectData.addMessage(userMessage);
			responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			exception.printStackTrace();
		}

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
	}

}
