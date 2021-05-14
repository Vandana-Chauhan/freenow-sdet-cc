package com.freenow.ste.cc.step.definations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.freenow.ste.cc.pojo.Comment;
import com.freenow.ste.cc.pojo.Post;
import com.freenow.ste.cc.pojo.UserDetail;
import com.freenow.ste.cc.resources.Log;
import com.freenow.ste.cc.resources.ResourcePath;
import com.freenow.ste.cc.resources.Utils;

public class UserBlogStep extends Utils {

	String userName;
	RequestSpecification resquestSpec;
	Response response;
	String value;
	UserDetail[] userDetails;
	Post[] posts;
	Comment[] comments;

	HashMap<String, Comment[]> allComments;

	@Given("GetUser payload is provided with {string}.")
	public void getuser_payload_is_provided_with(String username){
		Log.info("Starting the scenario");

		Log.info("Creating request with query param");
		userName = username;
		resquestSpec = getRequestSpec("username", userName);

	}

	@When("User calls {string} to search")
	public void user_calls_to_search(String resourcepath) {

		Log.info("Getting the resource path from enum ResourcePath");
		ResourcePath resource = ResourcePath.valueOf(resourcepath);

		Log.info("Extracting response using GET request");

		response = getResponse(resquestSpec, resource);

	}

	@When("I verify the {string} should be an alphabet")
	public void i_verify_the_should_be_an_alphabet(String string) {

		Boolean flag = true;
		Log.info("Verify user should not be null and should be  alphabets");
		if ((userName == null) && (userName.equals("")) && (!userName.matches("^[a-zA-Z]*$"))) {

			flag = false;
			Log.error("Username is null or not an alphabet ");
		}
		assertTrue("Username is null or not an alphabet", flag);

	}

	@Then("I should see response status code as {string}")
	public void i_should_see_response_status_code_as(String string) {

		Log.info("Verifying that the status code should be 200");
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("I vaildate  username  is null for invalid user")
	public void i_vaildate_username_is_null_for_invalid_user() {

		Log.info("Getting user details from  api response using pojo class ");
		userDetails = response.as(UserDetail[].class);

		Log.info("Verifying that for invalid user search, result should be an empty array ");
		assertEquals(0, userDetails.length);

	}

	@Given("User payload is provided with {string}.")
	public void user_payload_is_provided_with(String username){

		Log.info("Creating  payload for  Api using getuser_payload method ");
		getuser_payload_is_provided_with(username);

	}

	@Then("I validate that the  details of user are correctly matched.")
	public void i_validate_that_the_details_of_user_are_correctly_matched() {

		Log.info("Getting user details from  api response using pojo class ");
		userDetails = response.as(UserDetail[].class);

		value = userDetails[0].getId();

		assertEquals(userName, userDetails[0].getUsername());

	}

	@Then("I set the post request")
	public void i_set_the_post_request(){

		Log.info("Getting userId from userDetails  APIResponse using pojo class ");
		userDetails = response.as(UserDetail[].class);

		Log.info("Creating post resquest");
		resquestSpec = getRequestSpec("userId", userDetails[0].getId());

	}

	@When("User calls {string} to search post")
	public void user_calls_to_search_post(String resourcepath) throws IOException {

		Log.info("Getting the GetPost APIResponse");
		user_calls_to_search(resourcepath);

	}

	@Then("I validate that the  posts data of user are correctly matched with the details.")
	public void i_validate_that_the_posts_data_of_user_are_correctly_matched_with_the_details() {

		Log.info("Getting Posts for the given User using pojo class ");
		posts = response.as(Post[].class);

		Log.info("Verifying that all the  posts displayed are  written by the searched User ");
		for (int i = 0; i < posts.length; i++) {
			assertEquals(value, posts[i].getUserId());

		}

	}

	@When("User calls {string} to search comments")
	public void user_calls_to_search_comments(String resourcepath){

		Log.info("Creating a Hash Map to store the comments related to the Posts Id ");
		allComments = new HashMap<String, Comment[]>();

		Log.info("Getting Posts for the given User using pojo class ");
		posts = response.as(Post[].class);

		Log.info(
				"Adding comments to Map for all posts by a user through the Comment APT Request and response request ");
		for (int i = 0; i < posts.length; i++) {

			resquestSpec = getRequestSpec("postId", posts[i].getId());
			user_calls_to_search(resourcepath);
			comments = response.as(Comment[].class);
			allComments.put(posts[i].getId(), comments);
		}

	}

	@Then("I validate the comments belongs to the specific post\" .")
	public void i_validate_the_comments_belongs_to_the_specific_post() {

		String postId;

		Log.info("Validating that the PostId in comments response is equal to the PostId sent in request");

		for (Map.Entry<String, Comment[]> entry : allComments.entrySet()) {
			postId = entry.getKey();

			for (Comment keys : entry.getValue()) {
				assertEquals(postId, keys.getPostId());
				Log.info("Comments belongs to the requested Posts");

			}

		}
	}

	@Then("I get the emails from all the comments and validate the email format")
	public void i_get_the_emails_from_all_the_comments() {
		for (Comment[] keys : allComments.values()) {
			for (Comment value : keys) {
				String email = value.getEmail();
				if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
					Log.info("Email format is correct for  " + email);
				} else

					Log.info("Email format is incorrect for  " + email);
			}
		}
	}

	@Given("Post payload is provided with {string}.")
	public void post_payload_is_provided_with(String UserName) {
		Log.info("Creating post resquest");

		Log.info("Am sending hard coded  PostId as i do not have any user with Zero posts");
		resquestSpec = getRequestSpec("userId", "11");

	}

	@Then("I validate that the  posts data is empty for any new user.")
	public void i_validate_that_the_posts_data_is_empty_for_any_new_user() {
		Log.info("Getting user details from  api response using pojo class ");
		posts = response.as(Post[].class);

		Log.info("Verifying that for invalid user search, result should be an empty array ");
		assertEquals(0, posts.length);
	}

	@When("User calls {string} to search comments for new user")
	public void user_calls_to_search_comments_for_new_user(String resourcepath) {
		Log.info("Getting comments from  api response using pojo class ");
		resquestSpec = getRequestSpec("postId", "101");
		ResourcePath resource = ResourcePath.valueOf(resourcepath);
		response = getResponse(resquestSpec, resource);
	}

	@Then("I validate that the  comments  data is empty for very recent post.")
	public void i_validate_that_the_comments_data_is_empty_for_very_recent_post() {

		Log.info("Verifying that for invalid user search, result should be an empty array ");

		comments = response.as(Comment[].class);
		assertEquals(0, comments.length);
	}

}
