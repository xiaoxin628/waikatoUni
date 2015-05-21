package org.stevej.android.propertyfinder.trademe;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.stevej.android.propertyfinder.model.Property;

import android.util.Log;

public class JSONParser {
	private static final String TAG = "JSONParser";

	public static ArrayList<Property> parsePropertyListJSON(JSONObject json) {
		ArrayList<Property> properties = new ArrayList<Property>();

		try {
			JSONArray property_list = json.getJSONArray("List");

			for (int i = 0; i < property_list.length(); i++) {

				JSONObject property = property_list.getJSONObject(i);

				Property p = new Property();
				p.ListingID = property.optInt("ListingId");
				p.Title = property.optString("Title");
				p.PriceDisplay = property.optString("PriceDisplay");
				p.PriceNumeric = 0;
				String tmp = p.PriceDisplay.replaceAll("[^0-9]", "");
				try {
					p.PriceNumeric = Integer.parseInt(tmp);
				} catch (NumberFormatException e) {
					Log.d("TradeMeRequestFragment", "Could not parse to integer");
				}

				p.ThumbURL = property.optString("PictureHref");

				p.HasGallery = property.optBoolean("HasGallery");
				p.Address = property.optString("Address");
				p.Category = property.optString("Category");
				p.PropertyType = property.optString("PropertyType");

				JSONObject location = property.optJSONObject("GeographicLocation");
				if (location != null) {
					p.HasLocation = true;
					p.Latitude = location.optDouble("Latitude");
					p.Longitude = location.optDouble("Longitude");
				} else {
					p.HasLocation = false;
				}
				JSONObject agency = property.optJSONObject("Agency");
				if (agency != null) {
					p.HasAgency = true;
					p.Agency_name = agency.optString("Name");
					p.Agency_phone = agency.optString("PhoneNumber");
				} else {
					p.HasAgency = false;
				}
				properties.add(p);
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException");
			e.printStackTrace();
			return properties;
		}
		return properties;
	}

}
