package kr.co.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.demo.repo.UserRepository;
import kr.co.demo.vo.UserEntity;


@Service
public class kakaoService {

	@Autowired
	private UserRepository repo;
	
	private static String APP_KEY = "";
	private static String REDIRECT_URI = "http://localhost:8080/auth/kakao/callback";
	
	//토큰값 가져오기
	public String getAccessToken(String code) {
		String access_Token = "";
		String refresh_Token = "";
		
		try {
			URL url = new URL("https://kauth.kakao.com/oauth/token");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//POST 요청을 위해 기본값이 false인 setDoOutPut을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			//POST 요청에 필요로 하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id="+APP_KEY); //REST_API KEY값
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
	
	public UserEntity getKakaoUserInfo(String token) {
		
		UserEntity vo = new UserEntity();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
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
			
			long id = element.getAsJsonObject().get("id").getAsLong();
			String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
			boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
			String email = "";
			if(hasEmail) {
				email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			}
			
			vo.setUid(id);
			vo.setNickname(nickname);
			vo.setEmail(email);
			
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		UserEntity result = repo.findByUid(vo.getUid());
		System.out.println(result);
		
		if(result == null) {
			repo.save(vo);
			System.out.println("새로운 아이디");
			return vo;
		}else {
			System.out.println("기존 아이디");
			return result;
		}
		
	}
	
	public void logout(String token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unlink(String token) {
		String reqURL = "https://kapi.kakao.com/v1/user/unlink";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
