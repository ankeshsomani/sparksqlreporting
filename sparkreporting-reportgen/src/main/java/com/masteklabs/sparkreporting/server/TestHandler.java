package com.masteklabs.sparkreporting.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteklabs.sparkreporting.bean.ValueHolder;

public class TestHandler extends AbstractHandler implements Serializable{
	
	 final  SparkSession sparkSession;
	 public static final  Logger log = Logger.getLogger(TestHandler.class.getName());
	 public TestHandler(SparkSession sparkSession){
		 this.sparkSession=sparkSession;
	 }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@POST
	 @Path("queryData")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.TEXT_HTML)
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ObjectMapper mapper=new ObjectMapper();
		//System.out.println(target);
		String body=getBody(request);
		//System.out.println("2:-"+getBody(baseRequest));
		System.out.println("in handle");
		ValueHolder holder=mapper.readValue(body, ValueHolder.class); 
		System.out.println(holder.getQuery());
		System.out.println("query starts now");
		long time1=System.currentTimeMillis();
		Dataset<Row> results = sparkSession.sql(holder.getQuery());
		long time2=System.currentTimeMillis();
		JavaRDD<String> jsonRDD = results.toJSON().toJavaRDD();   
		long time3=System.currentTimeMillis();
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		
		PrintWriter out = response.getWriter();
String out1=jsonRDD.collect().toString();
long time4=System.currentTimeMillis();
		out.println(out1);
		long time5=System.currentTimeMillis();
		System.out.println("times are as:-");
		System.out.println(time1);System.out.println(time2);System.out.println(time3);System.out.println(time4);
		System.out.println(time5);
		baseRequest.setHandled(true);
	}
	public static String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
}
