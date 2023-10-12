package test.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//.json파일을  JSONArray이나 JSONObject로 변환
public class JSONParserTest {
	public static void main(String[] args) 
			throws FileNotFoundException, IOException, ParseException {
		//1. JSON parser생성
		//  ==> json문자열을 분석해서 문자열로 변환할 수 있는 객체
		JSONParser parser = new JSONParser();
		//2. json파일의 파싱
		JSONObject root = (JSONObject)parser.parse(new FileReader("src/main/java/test/json/myjson.json"));
		
		//3. json object에서 데이터읽기
		String name = (String)root.get("name");
		String age = (String)root.get("age");
		System.out.println("name="+name);
		System.out.println("age="+age);
		
		//4. JSONArray에서 데이터읽기
		JSONArray subjectlist = (JSONArray)root.get("subject");
		for(int i=0;i<subjectlist.size();i++) {
			String subject = (String)subjectlist.get(i);
			System.out.println("subject:"+subject);
		}
		System.out.println("===========================================");
		JSONArray commentlist = (JSONArray )root.get("comment");
		for(int i=0;i<commentlist.size();i++) {
			JSONObject comment = (JSONObject)commentlist.get(i);
			System.out.println("no:"+comment.get("no"));
			System.out.println("id:"+comment.get("id"));
			System.out.println("content:"+comment.get("content"));
			System.out.println("=========================================");
		}
	}
}
















