package kr.co.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import kr.co.demo.vo.UserEntity;

@Service
public class NaverService {
	
	private static String APP_KEY = "";
	private static String REDIRECT_URI = "http://localhost:8080/auth/naver/callback";
	//절대 유출 금지
	private static String SECRET_KEY = "";
	
	//토큰값 가져오기
	public String getAccessToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		
		try {
			URL url = new URL("https://nid.naver.com/oauth2.0/token");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//POST 요청을 위해 기본값이 false인 setDoOutPut을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			//POST 요청에 필요로 하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id="+APP_KEY); //REST_API KEY값
			sb.append("&client_secret="+SECRET_KEY); //REST_API KEY값
			sb.append("&redirect_uri="+REDIRECT_URI); //인가코드를 받은 REDIRECT_URI
			sb.append("&code="+code);
			bw.write(sb.toString());
			bw.flush();
			
			//200 = 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			//요청을 통해 얻는 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " +result);
			
			//Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_Token : " + refresh_Token);
			
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return access_Token;
	}
	
	public void getNaverUserInfo(String token) {
		
		UserEntity vo = new UserEntity();
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		
		//토큰을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token);
			
			//결과가 200이면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			//요청을 통해 얻은 JSON타입의 Response 메세지 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			String id = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
			String name = element.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
			String email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
			String mobile = element.getAsJsonObject().get("response").getAsJsonObject().get("mobile").getAsString();
			System.out.println("id : " + id);
			System.out.println("name : " + name);
			System.out.println("email : " + email);
			System.out.println("mobile : " + mobile);
			
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
