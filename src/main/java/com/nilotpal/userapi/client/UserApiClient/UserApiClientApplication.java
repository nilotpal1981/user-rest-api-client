package com.nilotpal.userapi.client.UserApiClient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApiClientApplication {
	private static final String DELETE_USER = "http://localhost:8080/users/1";
	private static final String UPDATE_USER = "http://localhost:8080/users/10007";
	private static final String CREATE_USER = "http://localhost:8080/users";
	private static final String ALL_USERS = "http://localhost:8080/users";
	private static final String ALL_USERS_PAGINATION = "http://localhost:8080/users?page=1&size=3";
	private static final String USER_BY_ID = "http://localhost:8080/users/10001";
	private static final String INVALID_USER_BY_ID = "http://localhost:8080/users/100012";
	private HttpHeaders headers = new HttpHeaders();

	public void getAllUsers_WithoutPaginationDetails() {
		try {
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			System.out.println("Get All Users Request URL: " + ALL_USERS);
			ResponseEntity<String> response = getRestTemplate().exchange(ALL_USERS, 
				HttpMethod.GET, entity, String.class);
			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void getAllUsers_WithPaginationDetails() {
		try {
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			System.out.println("Get All Users Pagination Request URL: " + ALL_USERS_PAGINATION);
			ResponseEntity<String> response = getRestTemplate().exchange(
				ALL_USERS_PAGINATION, HttpMethod.GET, entity, String.class);
			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void getUserById_ExistingUser() {
		try {
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			System.out.println("Get User Request URL: " + USER_BY_ID);
			ResponseEntity<String> response = getRestTemplate().exchange(
					USER_BY_ID, HttpMethod.GET, entity, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void getUserById_NonExistentUser() {
		try {
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);

			System.out.println("Get User Request URL: " + INVALID_USER_BY_ID);
			ResponseEntity<String> response = getRestTemplate().exchange(
				INVALID_USER_BY_ID, HttpMethod.GET, entity, String.class);
			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void createUser() {
		try {
			String createUserJson = "{\"email\":\"create@test.com\",\"name\":\"CreatedUser1\",\"password\":\"password\",\"username\":\"created1\"}";
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(createUserJson, headers);

			System.out.println("Create User Request URL: " + CREATE_USER);
			ResponseEntity<String> response = getRestTemplate().exchange(
				CREATE_USER, HttpMethod.PUT, entity, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void deleteUser() {
		try {
			HttpEntity<String> entityToDelete = new HttpEntity<String>(null, headers);

			System.out.println("Delete User Request URL: " + DELETE_USER);
			ResponseEntity<String> response = getRestTemplate().exchange(
				DELETE_USER, HttpMethod.DELETE, entityToDelete, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void deleteNonExistentUser() {
		try {
			HttpEntity<String> entityToDelete = new HttpEntity<String>(null, headers);

			System.out.println("Delete Invalid User Request URL: " + INVALID_USER_BY_ID);
			ResponseEntity<String> response = getRestTemplate().exchange(
				INVALID_USER_BY_ID, HttpMethod.DELETE, entityToDelete, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void updateUser_ExistingUser() {
		try {
			String updateUserJson = "{\"email\":\"update@test.com\",\"name\":\"UpdatedUser1\",\"password\":\"password\",\"username\":\"updated1\"}";
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(updateUserJson, headers);

			System.out.println("Update User Request URL: " + UPDATE_USER);
			ResponseEntity<String> response = getRestTemplate().exchange(
				UPDATE_USER, HttpMethod.POST, entity, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public void updateUser_NonExistentUser() {
		try {
			String updateUserJson = "{\"email\":\"update@test.com\",\"name\":\"UpdatedUser1\",\"password\":\"password\",\"username\":\"updated1\"}";
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> entity = new HttpEntity<String>(updateUserJson, headers);

			System.out.println("Update Invalid User Request URL: " + INVALID_USER_BY_ID);
			ResponseEntity<String> response = getRestTemplate().exchange(
				INVALID_USER_BY_ID, HttpMethod.POST, entity, String.class);

			System.out.println("Response Body: " + response.getBody());
		} catch(Exception ex) {
			System.out.println("Exception thrown: " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		UserApiClientApplication application = new UserApiClientApplication();
		application.getAllUsers_WithoutPaginationDetails();
		application.getAllUsers_WithPaginationDetails();
		application.getUserById_ExistingUser();
		application.getUserById_NonExistentUser();
		application.createUser();
		application.updateUser_ExistingUser();
		application.updateUser_NonExistentUser();
		application.deleteUser();
		application.deleteNonExistentUser();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
