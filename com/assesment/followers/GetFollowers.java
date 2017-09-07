package com.assesment.followers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;


@Path("/getFollowers")
public class GetFollowers {

	


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public String sayPlainTextHello(@PathParam("id") String id) {

		try {
			ArrayList<String> srcIdsList = new ArrayList<String>();
			srcIdsList.add(id);
			int maxLevel = 3;
			int level = 1;
			ArrayList<String> levelfollowers = new ArrayList<String>();
			ArrayList<User> displayAll = new ArrayList<User>();
			for (int userIdIndex = 0; userIdIndex < srcIdsList.size(); userIdIndex++) {

				ArrayList<String> followers = getData(srcIdsList
						.get(userIdIndex));

				for (int followerIndex = 0; followerIndex < followers.size(); followerIndex++) {
					displayAll.add(new User(followers.get(followerIndex),
							srcIdsList.get(userIdIndex), level));
				}
				levelfollowers.addAll(followers);
				if (srcIdsList.size() - 1 == userIdIndex && level != maxLevel) {
					level = level + 1;
					srcIdsList = levelfollowers;
					userIdIndex = -1;
					levelfollowers = new ArrayList<String>();

				}

			}
			return new Gson().toJson(displayAll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(new Error("Unable to display Results because of Exception:  "+e.getMessage()
					));
		}

		
	}

	public ArrayList<String> getData(String userId)throws Exception {
		ResponseObject[] name = null;
		ArrayList<String> followers = null;
		try {
			BufferedReader in;
			URL url = new URL("https://api.github.com/users/" + userId
					+ "/followers");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("User-Agent", "");
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {

				response.append(inputLine);
			}

			Gson gson = new Gson();
			name = gson.fromJson(response.toString(), ResponseObject[].class);

			followers = new ArrayList<String>();
			for (int i = 0; i < name.length; i++) {
				if (i > 4)
					break;
				else
					followers.add(name[i].getLogin());
			}

			in.close();
			return followers;

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
	
}
