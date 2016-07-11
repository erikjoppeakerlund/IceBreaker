package se.BaseUlterior.Context.JSON;

public class JSONTest {

	public static void main(String[] args) {
		JSONArray employees = JSONFile.parseFromResource("res/levels/example.txt").getArray("employees");
		for (int index = 0, len = employees.length(); index < len; index++) {
			JSONObject employee = employees.getObject(index);
			System.out.println(employee.getString("lastName") + ", " + employee.getString("firstName"));
		}
	}

}