package api.tests.users;

import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.validation.Assertions;
import com.shaft.validation.Assertions.AssertionComparisonType;
import com.shaft.validation.Assertions.AssertionType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserData {

    @Test
    public void validateUserEmail() {
	RestActions apiObject = new RestActions("https://jsonplaceholder.typicode.com");
	Response users = apiObject.performRequest("GET", "200", "/users", null, null, null, ContentType.ANY);

	Assertions.assertEquals("Leanne Graham", RestActions.getResponseBody(users), AssertionComparisonType.CONTAINS,
		AssertionType.POSITIVE);

	RestActions.getResponseJSONValueAsList(users, "$").forEach(user -> {
	    if (RestActions.getResponseJSONValue(user, "name").equals("Leanne Graham")) {
		Assertions.assertEquals("Sincere@april.biz", RestActions.getResponseJSONValue(user, "email"),
			AssertionComparisonType.EQUALS, AssertionType.POSITIVE);
	    }

	});
    }
}
