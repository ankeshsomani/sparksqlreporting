package com.masteklabs.sparkreporting.bean;

import java.math.BigInteger;
import java.util.Date;

public class Tweet {
	
	private Long tweetid;
	private String createdat;
	private String tweet;
	private Integer sentiment;
	private String source;
	private String inreplytostatusid;
	private String inreplytouserid;
	private String inreplytousername;
	private String userid;
	private String userfullname;
	private String userscreenname;
	private String userlocation;
	private String userdescription;
	private String userurl;
	private String userprotected;
	private String userverified;
	private String userfollowercount  ;     
	private String userfollowingcount  ;    
	private String userstatuscount   ;      
	private String userfriendscount   ;     
	private String userlistedcount   ;      
	private String userfavouritecount ;     
	private String usercreatedat  ;
	private String usergeoenabled  ;        
	private String userlanguage   ;         
	private String userprofileimageurl;     
	private String userdefaultprofile  ;    
	private String userdefaultprofileimage ;
	private String tweetlongitude             ;                  
	private String tweetlatitude            ;            
	private String retweetcount  ;          
	private Integer favouritecount  ;        
	private String retweeted  ;             
	private String favourited  ;            
	private String filterlevel   ;          
	private String language  ;              
	private String placetype ;              
	private String placename  ;             
	private String placefullname  ;         
	private String placecountrycode ;       
	private String placecountry  ;          
	private String place_coordinate1_lng  ;                                                                                                                
	private String place_coordinate1_lat  ;                                                                                                               
	private String place_coordinate2_lng  ;                                                                                                                
	private String place_coordinate2_lat  ;                                                                                                               
	private String place_coordinate3_lng  ;                                                                                                                 
	private String place_coordinate3_lat ;                                                                                                               
	private String place_coordinate4_lng ;                                                                                                                  
	private String place_coordinate4_lat;                                                                                                                 
	private String hashtags    ;                                                                                                                       
	private String user_mentions  ;                                                                                                                      
	private String urls        ;                                                                                                                          
	private Integer category    ;                                                                                                                                  
	private String geolatitude  ;                                                                                                                          
	private String geolongitude  ;
	

	public Long getTweetid() {
		return tweetid;
	}
	public void setTweetid(Long tweetid) {
		this.tweetid = tweetid;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Integer getSentiment() {
		return sentiment;
	}
	public void setSentiment(Integer sentiment) {
		this.sentiment = sentiment;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInreplytostatusid() {
		return inreplytostatusid;
	}
	public void setInreplytostatusid(String inreplytostatusid) {
		this.inreplytostatusid = inreplytostatusid;
	}
	public String getInreplytouserid() {
		return inreplytouserid;
	}
	public void setInreplytouserid(String inreplytouserid) {
		this.inreplytouserid = inreplytouserid;
	}
	public String getInreplytousername() {
		return inreplytousername;
	}
	public void setInreplytousername(String inreplytousername) {
		this.inreplytousername = inreplytousername;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserfullname() {
		return userfullname;
	}
	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}
	public String getUserscreenname() {
		return userscreenname;
	}
	public void setUserscreenname(String userscreenname) {
		this.userscreenname = userscreenname;
	}
	public String getUserlocation() {
		return userlocation;
	}
	public void setUserlocation(String userlocation) {
		this.userlocation = userlocation;
	}
	public String getUserdescription() {
		return userdescription;
	}
	public void setUserdescription(String userdescription) {
		this.userdescription = userdescription;
	}
	public String getUserurl() {
		return userurl;
	}
	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}
	public String getUserprotected() {
		return userprotected;
	}
	public void setUserprotected(String userprotected) {
		this.userprotected = userprotected;
	}
	public String getUserverified() {
		return userverified;
	}
	public void setUserverified(String userverified) {
		this.userverified = userverified;
	}
	public String getUserfollowercount() {
		return userfollowercount;
	}
	public void setUserfollowercount(String userfollowercount) {
		this.userfollowercount = userfollowercount;
	}
	public String getUserfollowingcount() {
		return userfollowingcount;
	}
	public void setUserfollowingcount(String userfollowingcount) {
		this.userfollowingcount = userfollowingcount;
	}
	public String getUserstatuscount() {
		return userstatuscount;
	}
	public void setUserstatuscount(String userstatuscount) {
		this.userstatuscount = userstatuscount;
	}
	public String getUserfriendscount() {
		return userfriendscount;
	}
	public void setUserfriendscount(String userfriendscount) {
		this.userfriendscount = userfriendscount;
	}
	public String getUserlistedcount() {
		return userlistedcount;
	}
	public void setUserlistedcount(String userlistedcount) {
		this.userlistedcount = userlistedcount;
	}
	public String getUserfavouritecount() {
		return userfavouritecount;
	}
	public void setUserfavouritecount(String userfavouritecount) {
		this.userfavouritecount = userfavouritecount;
	}
	public String getUsercreatedat() {
		return usercreatedat;
	}
	public void setUsercreatedat(String usercreatedat) {
		this.usercreatedat = usercreatedat;
	}
	public String getUsergeoenabled() {
		return usergeoenabled;
	}
	public void setUsergeoenabled(String usergeoenabled) {
		this.usergeoenabled = usergeoenabled;
	}
	public String getUserlanguage() {
		return userlanguage;
	}
	public void setUserlanguage(String userlanguage) {
		this.userlanguage = userlanguage;
	}
	public String getUserprofileimageurl() {
		return userprofileimageurl;
	}
	public void setUserprofileimageurl(String userprofileimageurl) {
		this.userprofileimageurl = userprofileimageurl;
	}
	public String getUserdefaultprofile() {
		return userdefaultprofile;
	}
	public void setUserdefaultprofile(String userdefaultprofile) {
		this.userdefaultprofile = userdefaultprofile;
	}
	public String getUserdefaultprofileimage() {
		return userdefaultprofileimage;
	}
	public void setUserdefaultprofileimage(String userdefaultprofileimage) {
		this.userdefaultprofileimage = userdefaultprofileimage;
	}

	public String getTweetlongitude() {
		return tweetlongitude;
	}
	public void setTweetlongitude(String tweetlongitude) {
		this.tweetlongitude = tweetlongitude;
	}
	public String getTweetlatitude() {
		return tweetlatitude;
	}
	public void setTweetlatitude(String tweetlatitude) {
		this.tweetlatitude = tweetlatitude;
	}
	public String getRetweetcount() {
		return retweetcount;
	}
	public void setRetweetcount(String retweetcount) {
		this.retweetcount = retweetcount;
	}
	public Integer getFavouritecount() {
		return favouritecount;
	}
	public void setFavouritecount(Integer favouritecount) {
		this.favouritecount = favouritecount;
	}
	public String getRetweeted() {
		return retweeted;
	}
	public void setRetweeted(String retweeted) {
		this.retweeted = retweeted;
	}
	public String getFavourited() {
		return favourited;
	}
	public void setFavourited(String favourited) {
		this.favourited = favourited;
	}
	public String getFilterlevel() {
		return filterlevel;
	}
	public void setFilterlevel(String filterlevel) {
		this.filterlevel = filterlevel;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPlacetype() {
		return placetype;
	}
	public void setPlacetype(String placetype) {
		this.placetype = placetype;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public String getPlacefullname() {
		return placefullname;
	}
	public void setPlacefullname(String placefullname) {
		this.placefullname = placefullname;
	}
	public String getPlacecountrycode() {
		return placecountrycode;
	}
	public void setPlacecountrycode(String placecountrycode) {
		this.placecountrycode = placecountrycode;
	}
	public String getPlacecountry() {
		return placecountry;
	}
	public void setPlacecountry(String placecountry) {
		this.placecountry = placecountry;
	}
	public String getPlace_coordinate1_lng() {
		return place_coordinate1_lng;
	}
	public void setPlace_coordinate1_lng(String place_coordinate1_lng) {
		this.place_coordinate1_lng = place_coordinate1_lng;
	}
	public String getPlace_coordinate1_lat() {
		return place_coordinate1_lat;
	}
	public void setPlace_coordinate1_lat(String place_coordinate1_lat) {
		this.place_coordinate1_lat = place_coordinate1_lat;
	}
	public String getPlace_coordinate2_lng() {
		return place_coordinate2_lng;
	}
	public void setPlace_coordinate2_lng(String place_coordinate2_lng) {
		this.place_coordinate2_lng = place_coordinate2_lng;
	}
	public String getPlace_coordinate2_lat() {
		return place_coordinate2_lat;
	}
	public void setPlace_coordinate2_lat(String place_coordinate2_lat) {
		this.place_coordinate2_lat = place_coordinate2_lat;
	}
	public String getPlace_coordinate3_lng() {
		return place_coordinate3_lng;
	}
	public void setPlace_coordinate3_lng(String place_coordinate3_lng) {
		this.place_coordinate3_lng = place_coordinate3_lng;
	}
	public String getPlace_coordinate3_lat() {
		return place_coordinate3_lat;
	}
	public void setPlace_coordinate3_lat(String place_coordinate3_lat) {
		this.place_coordinate3_lat = place_coordinate3_lat;
	}
	public String getPlace_coordinate4_lng() {
		return place_coordinate4_lng;
	}
	public void setPlace_coordinate4_lng(String place_coordinate4_lng) {
		this.place_coordinate4_lng = place_coordinate4_lng;
	}
	public String getPlace_coordinate4_lat() {
		return place_coordinate4_lat;
	}
	public void setPlace_coordinate4_lat(String place_coordinate4_lat) {
		this.place_coordinate4_lat = place_coordinate4_lat;
	}
	public String getHashtags() {
		return hashtags;
	}
	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}
	public String getUser_mentions() {
		return user_mentions;
	}
	public void setUser_mentions(String user_mentions) {
		this.user_mentions = user_mentions;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getGeolatitude() {
		return geolatitude;
	}
	public void setGeolatitude(String geolatitude) {
		this.geolatitude = geolatitude;
	}
	public String getGeolongitude() {
		return geolongitude;
	}
	public void setGeolongitude(String geolongitude) {
		this.geolongitude = geolongitude;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}          

	
	

}
