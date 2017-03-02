package com.masteklabs.sparkreporting.util;

import com.masteklabs.sparkreporting.bean.Tweet;

public class ConvertorUtil {

	public static Tweet convertToTweet(String line){
		Tweet tweet=new Tweet();
		String[] parts = line.split("\\|");
	//	System.out.println(parts.length);
		tweet.setTweetid(Long.parseLong(parts[0]));
		tweet.setCreatedat(parts[1]);
		tweet.setTweet(parts[2]);
		tweet.setSentiment(Integer.parseInt(parts[3]));
		tweet.setSource(parts[4]);
		tweet.setInreplytostatusid(parts[5]);
		tweet.setInreplytouserid(parts[6]);
		tweet.setInreplytousername(parts[7]);
		tweet.setUserid(parts[8]);
		tweet.setUserfullname(parts[9]);
		tweet.setUserscreenname(parts[10]);
		tweet.setUserlocation(parts[11]);
		tweet.setUserdescription(parts[12]);
		tweet.setUserurl(parts[13]);
		tweet.setUserprotected(parts[14]);
		tweet.setUserverified(parts[15]);
		tweet.setUserfollowercount(parts[16]);
		tweet.setUserfollowingcount(parts[17]);
		tweet.setUserstatuscount(parts[18]);
		tweet.setUserfriendscount(parts[19]);
		tweet.setUserlistedcount(parts[20]);
		tweet.setUserfavouritecount(parts[21]);
		tweet.setUsercreatedat(parts[22]);
		tweet.setUsergeoenabled(parts[23]);
		tweet.setUserlanguage(parts[24]);
		tweet.setUserprofileimageurl(parts[25]);
		tweet.setUserdefaultprofile(parts[26]);
		tweet.setUserdefaultprofileimage(parts[27]);
		tweet.setTweetlongitude(parts[28]);
		tweet.setTweetlatitude(parts[29]);
		tweet.setRetweetcount(parts[30]);
		tweet.setFavouritecount(Integer.parseInt(parts[31]));
		tweet.setRetweeted(parts[32]);
		tweet.setFavourited(parts[33]);
		tweet.setFilterlevel(parts[34]);
		tweet.setLanguage(parts[35]);
		tweet.setPlacetype(parts[36]);
		tweet.setPlacename(parts[37]);
		tweet.setPlacefullname(parts[38]);
		tweet.setPlacecountrycode(parts[39]);
		tweet.setPlace_coordinate1_lng(parts[40]);
		tweet.setPlacecountry(parts[41]);
		tweet.setPlace_coordinate1_lat(parts[42]);
		tweet.setPlace_coordinate2_lng(parts[43]);
		tweet.setPlace_coordinate2_lat(parts[44]);
		tweet.setPlace_coordinate3_lng(parts[45]);
		tweet.setPlace_coordinate3_lat(parts[46]);
		tweet.setPlace_coordinate4_lng(parts[47]);
		tweet.setPlace_coordinate4_lat(parts[48]);
		tweet.setHashtags(parts[49]);
		tweet.setUser_mentions(parts[50]);
		tweet.setUrls(parts[51]);
		//System.out.println(parts[52]);
		tweet.setCategory(Integer.parseInt(parts[52]));
		tweet.setGeolatitude(parts[53]);
		tweet.setGeolongitude(parts[54]);
		return tweet;
	}
	

	
			
	
}
