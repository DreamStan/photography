package com.photograph;

import com.alibaba.fastjson.JSON;
import com.photograph.controller.UploaderController;
import com.photograph.dao.*;
import com.photograph.pojo.*;
import com.photograph.service.DetailsService;
import com.photograph.service.PageViewService;
import com.photograph.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotographApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {

		User user = new User();
		user.setPassword("123");
		user.setUsername("李四");

		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		operations.set("pageView_35",String.valueOf(0));
//		operations.set("name",user);
//		System.out.println("name:"+operations.get("name").getUsername());
	}

	@Test
	public void md(){
		System.out.println(MD5("123"));
	}

	private String MD5(String s){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes){
		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	@Test
	public void encryptPassword(){
		String pwd = "123";
		pwd = new BCryptPasswordEncoder().encode(pwd);
		System.out.println(pwd);
	}

	@Autowired
	private UserReleaseDao userReleaseDao;

	@Autowired
	private ImgsUriDao imgsUriDao;

	@Test
	public void release(){

		ImgsUri imgsUri1 = null;


		UserRelease userRelease = new UserRelease();
		userRelease.setTitle("标题");
		userRelease.setContents("介绍");
		userRelease.setWatt("属性");
		userRelease.setProtection("close");

		userReleaseDao.addRelease(userRelease);

		List<ImgsUri> imgsUris = new ArrayList<ImgsUri>();
		for (int i = 0; i < 9; i++) {
			new ImgsUri();
			imgsUri1.setUrid(userRelease.getId());
			imgsUri1.setUname("lianglei");
			imgsUri1.setImgName("1");
			imgsUri1.setImgUrl("1");
			imgsUris.add(i,imgsUri1);
		}
		imgsUriDao.addImgs(imgsUris);

	}

	@Test
	public void mf(){
		System.out.println(MessageFormat.format("(#'{'list[0].urid}," +
				"#'{'list[0].imgUrl},#'{'list[0].imgName})",new Object[]{0}));

	}

	@Autowired
	private UploaderController uploaderController;

	private MockMvc mockMvc;

//	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.standaloneSetup(uploaderController).build();
	}

	@Test
	public void controller() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
				.post("http://localhost:8081/webUploader").param("title","标题"));
		MvcResult mvcResult = resultActions.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		System.out.println("==服务器数据==:"+result);


	}

	@Autowired
	private UserTagDao userTagDao;

	@Test
	public void tag(){
		List<UserTag> userTagList = new ArrayList<UserTag>();

		for (int i = 0; i < 9; i++) {
			UserTag userTag = new UserTag();
			userTag.setUname("lianglei");
			userTag.setTagname("a"+i);
			userTagList.add(userTag);
		}
		userTagDao.addTag(userTagList);
	}

	@Autowired
	private PageViewService pageViewService;

	@Autowired
	private UserService userService;

	@Test
	public void pageView(){
		Integer count = pageViewService.pageView(35);
		System.out.println(count);
	}

	@Autowired
	private DetailsService detailsService;


	@Test
	public void userRelease(){
	 	List<UserRelease> userReleases = detailsService.findByWorks(38);
		userReleases.forEach(userRelease -> {

		});
	}

	@Autowired
	private CommentDao commentDao;

	@Test
	public void comment(){
		List<Comment> comments = commentDao.findByUrid(35);
		comments.forEach(comment -> {
			System.out.println(comment.getUname());
			System.out.println(comment.getContent());
			System.out.println(comment.getTime());
		});
	}

	@Autowired
	private NewWorksDao newWorksDao;

	@Test
	public void NewWorks(){
		List<UserRelease> userReleases = newWorksDao.newWorkd();
		userReleases.forEach(userRelease -> {
			System.out.println("urid:"+userRelease.getId());
			System.out.println("封面："+userRelease.getImgsUris().get(0).getImgUrl());
//			userRelease.getImgsUris().forEach(imgsUri -> System.out.println("封面："+imgsUri.getImgUrl()));
			userRelease.getUserTags().forEach(userTag -> System.out.println(userTag.getTagname()));
		});
	}
}