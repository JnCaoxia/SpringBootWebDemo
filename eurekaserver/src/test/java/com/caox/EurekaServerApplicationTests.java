package com.caox;

import com.caox.model.MerchantCompanyReqDTO;
import com.caox.model.ShareholdersReqDTO;
import com.caox.model.UserInfo;
import com.caox.utils.CipherTextUtils;
import com.caox.utils.ConvertUtils;
import com.caox.utils.ListObjectNoRepeatUtils;
import com.caox.utils.ListSortUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EurekaServerApplicationTests {

	@Value("${cs.agent.config}")
	private String csAgentConfig;

	@Test
	public void contextLoads() throws Exception{
        // 读取apollo配置  key :cs.agent.config  : { "120838": "terminalId_120838" , "100000178" : "terminal_100000178"}
		JSONObject obj = new JSONObject(csAgentConfig);
		Stack<JSONObject> stObj = new Stack<JSONObject>();
		stObj.push(obj);
		Map<String, Object> resultMap =new HashMap<String, Object>();
		JsonToMap(stObj,resultMap);
		Set<String> keys = resultMap.keySet();
		for (String key:keys){
			System.out.println(key+"："+resultMap.get(key));
		}
	}

	@Test
	public void testArrayList(){
		List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());

		List<Powder> snow2 = Arrays.asList(new Light(), new Heavy());
		for(Powder snowOne : snow2){
			if(snowOne instanceof  Powder){
				log.info("is Snow!");
			}else{
				log.info("is not Snow !");
			}
		}

		List<Snow> snow3 = new ArrayList<Snow>();
		Collections.addAll(snow3, new Light(), new Heavy());

		List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());

		Pet[] petArray = new Pet[10];
		for (int i = 0; i < petArray.length; i++) {
			petArray[i] = new Pet(i + "");
		}
		//List<Pet> petList = Arrays.asList(petArray);
		List<Pet> petList = new ArrayList<Pet>(Arrays.asList(petArray));
		System.out.println("petList:" + petList);
		List<Pet> subList = petList.subList(petList.size() / 4, petList.size() / 2);
		System.out.println("subList:" + subList);
		//如果支持列表在结构上修改，子列表的语义就会被取消。
//		petList.removeAll(subList);
		subList.clear();
		System.out.println("petList:" + petList);
		System.out.println("subList:" + subList);

		List<Pet> pets = new ArrayList<Pet>();
		pets.addAll(Arrays.asList(new Pet("Rat"), new Pet("Manx"), new Pet("Mutt"), new Pet("Pug")));

		/**
		 * ListIterator只能用于各种List类的访问，ListIterator可以双向移动，并且可以产生相对于迭代器在列表中指向的当前位置的前一个和
		 * 后一个元素的索引，并且可以使用set()替换它访问过的最后一个元素，而且还可以通过调用listIterator(n)方法创建一个一开始就指向列表
		 * 索引为n的元素处的ListIterator。
		 */
		ListIterator<Pet> lt = pets.listIterator();
		while(lt.hasNext()){
			System.out.print(lt.next() + ", " + lt.nextIndex() + ", " + lt.previousIndex()
					+ "; ");
		}
		System.out.println();

		while(lt.hasPrevious()){
			System.out.print(lt.previous() + " ");
		}
		System.out.println();

		System.out.println(pets);
		lt = pets.listIterator(1);
		while(lt.hasNext()){
			lt.next();
			lt.set(new Pet("Hamster"));
		}
		System.out.println(pets);

	}

	@Test
	public void testPriorityQueue(){
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		Random rand = new Random(47);
		for (int i = 0; i < 10; i++) {
			priorityQueue.offer(rand.nextInt(i + 10));
		}
		/**
		 * 在PriorityQueue中，重复是允许的，最小的值拥有最高的优先级（如果是String，空格也可以算作值，并且比字母的优先级高）。
		 */
		printQueue(priorityQueue);

		List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1,
				2, 3, 9, 14, 18, 21, 23, 25);
		priorityQueue = new PriorityQueue<Integer>(ints);
		printQueue(priorityQueue);

		/**
		 * 通过传入Collections.reverseOrder()使得PriorityQueue产生反序的效果
		 */
		priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.reverseOrder());
		priorityQueue.addAll(ints);
		printQueue(priorityQueue);


		PriorityQueue<Dummy> queue = new PriorityQueue<Dummy>(idComparator);
		System.out.println("Adding 1st instance...");
		queue.offer(new Dummy("name1",1));
		System.out.println("Adding 2nd instance...");
		/**
		 *  会报java.lang.ClassCastException: com.huangfei.thinkinginjava.holding.Dummy
		 *                                      cannot be cast to java.lang.Comparable
		 *  Integer、String已经内建了自然排序，如果想在PriorityQueue中使用自己的类，就必须包含额外的功能以产生自然排序，
		 *  或者必须提供自己的Comparator。
		 */
		queue.offer(new Dummy("name2",2));
		pollDataFromQueue(queue);
	}



	@Test
	public void testStringEmpty(){

	}

	@Test
	public void testPropertiesSort() throws Exception{
		List<UserInfo> list = new ArrayList<UserInfo>();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//		UserInfo user1 = new UserInfo(3, "bbb", formater.parse("1980-12-01"), 1, 1.2f, 'a');
//		UserInfo user2 = new UserInfo(0, "126", formater.parse("1900-10-11"), 03, -3.6f, 'c');
//		UserInfo user3 = new UserInfo(12, "5", formater.parse("1973-08-21"), 15, 9.32f, 'f');
//		UserInfo user4 = new UserInfo(465, "1567", formater.parse("2012-01-26"), 20, 12.56f, '0');
//		UserInfo user5 = new UserInfo(2006, "&4m", formater.parse("2010-05-08"), 100, 165.32f, '5');
//		UserInfo user6 = new UserInfo(5487, "曹操", formater.parse("2016-12-30"), 103, 56.32f, 'm');
//		UserInfo user7 = new UserInfo(5487,"王二", formater.parse("2000-10-16"), 103, 56.32f, 'm');
//		UserInfo user8 = new UserInfo(5487, "张三", formater.parse("1987-07-25"), 103, 56.32f, 'm');
//		UserInfo user9 = new UserInfo(0, "曹*", formater.parse("1987-07-21"), 109, 56.00f, '0');
//		list.add(user1);
//		list.add(user2);
//		list.add(user3);
//		list.add(user4);
//		list.add(user5);
//		list.add(user6);
//		list.add(user7);
//		list.add(user8);
//		list.add(user9);

		System.out.println(",-------原来序列-------------------");
		this.printfUserInfoList(list);

		// 按userId升序、username降序、birthDate升序排序  
		String [] sortNameArr = {"userId","username","birthDate"};
		boolean [] isAscArr = {true,false,true};
		ListSortUtils.sort(list,sortNameArr,isAscArr);
		System.out.println(",--------按按userId升序、username降序、birthDate升序排序（如果userId相同，则按照username降序,如果username相同，则按照birthDate升序）------------------");
		this.printfUserInfoList(list);

		// 按userId、username、birthDate都升序排序  
		ListSortUtils.sort(list, true, "userId", "username","birthDate");
		System.out.println(",--------按userId、username、birthDate排序（如果userId相同，则按照username升序,如果username相同，则按照birthDate升序）------------------");
		this.printfUserInfoList(list);

		// 按userId、username都倒序排序  
		ListSortUtils.sort(list, false, "userId", "username");
		System.out.println(",--------按userId和username倒序（如果userId相同，则按照username倒序）------------------");
		this.printfUserInfoList(list);

		// 按username、birthDate都升序排序  
		ListSortUtils.sort(list, true, "username", "birthDate");
		System.out.println(",---------按username、birthDate升序（如果username相同，则按照birthDate升序）-----------------");
		this.printfUserInfoList(list);

		// 按birthDate倒序排序  
		ListSortUtils.sort(list, false, "birthDate");
		System.out.println(",---------按birthDate倒序-----------------");
		this.printfUserInfoList(list);

		// 按fRate升序排序  
		ListSortUtils.sort(list, true, "fRate");
		System.out.println(",---------按fRate升序-----------------");
		this.printfUserInfoList(list);

		// 按ch倒序排序  
		ListSortUtils.sort(list, false, "ch");
		System.out.println(",---------按ch倒序-----------------");
		this.printfUserInfoList(list);

   // 按userId、username、birthDate都升序排序
		ListSortUtils.sort(list, true, "username", "userId","birthDate", "age", "fRate", "ch");
		System.out.println(",--------按全部属性排序（如果userId相同，则按照username升序,如果username相同，则按照birthDate升序...）------------------");
		this.printfUserInfoList(list);
	}

	private void printfUserInfoList(List<UserInfo> list) {
		for (UserInfo user : list) {
			System.out.println(user.toString());
		}
	}

	@Test
	public void testABD(){
		String errorMsg = "错误码:MAQ_MERCHANT_ACCOUNT_NAME_REPEAT_ERROR, 描述:企业商户账户名重复, 异常信息:企业商户账户名重复";
		errorMsg = errorMsg.replace("账户名", "登录邮箱");
		String errorMsgEnd =  errorMsg.substring(errorMsg.indexOf("异常信息") + 5,errorMsg.length());
		log.info("call testABD :{}",errorMsgEnd + ",请确认后重新填写");
	}


	private static void pollDataFromQueue(Queue<Dummy> customerPriorityQueue) {
           while(true) {
               Dummy dummy = customerPriorityQueue.poll();
               if(dummy==null) break ;
               System.out.println("Processing Dummy with ID=" + dummy.getId());
         }
    }



	private static void printQueue(Queue queue){
		while(queue.peek() != null)
			System.out.print(queue.remove() + " ");
		System.out.println();
	}

	class Dummy {
		private String name;
        private int id;
        public Dummy(String name, int id) {
         super();
         this.name = name;
         this.id = id;
       }

       public String getName() {
        return name;
       }

       public int getId() {
	   	return id;
       }
	}

	public static Comparator<Dummy> idComparator = new Comparator<Dummy>() {
		@Override
		public int compare(Dummy c1, Dummy c2) {
			return (int)(c1.getId() - c2.getId());
		}
	};

	/**
	 * @Author：sks
	 * @Description：把json对象数据存储在map以键值对的形式存储，只存储叶节点
	 * @Date：
	 */
	private static void JsonToMap(Stack<JSONObject> stObj, Map<String, Object> resultMap)  throws Exception {

		if(stObj == null && stObj.pop() == null){
			return ;
		}
		JSONObject json = stObj.pop();
		Iterator it = json.keys();
		while(it.hasNext()){
			String key = (String) it.next();
			//得到value的值
			Object value = json.get(key);
			//System.out.println(value);
			if(value instanceof JSONObject)
			{
				stObj.push((JSONObject)value);
				//递归遍历
				JsonToMap(stObj,resultMap);
			}
			else {
				resultMap.put(key, value);
			}
		}
	}

	class Snow {}
	class Powder extends Snow {}
	class Light extends Powder {}
	class Heavy extends Powder {}
	class Crusty extends Snow {}
	class Slush extends Snow {}

	public class Pet {

		private String name;

		public Pet(String name){
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

	}

	// 短日期格式
	public static String DATE_FORMAT = "yyyy-MM-dd";

	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将日期格式的字符串转换为长整型
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static long convert2long(String date, String format) {
		try {
			if (StringUtils.isNotBlank(date)) {
				if (StringUtils.isBlank(format))
					format = EurekaServerApplicationTests.TIME_FORMAT;

				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(date).getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 将长整型数字转换为日期格式的字符串
	 *
	 * @param time
	 * @param format
	 * @return
	 */
	public static String convert2String(long time, String format) {
		if (time > 0l) {
			if (StringUtils.isBlank(format))
				format = EurekaServerApplicationTests.TIME_FORMAT;

			SimpleDateFormat sf = new SimpleDateFormat(format);
			Date date = new Date(time);

			return sf.format(date);
		}
		return "";
	}

	/**
	 * 获取当前系统的日期
	 *
	 * @return
	 */
	public static long curTimeMillis() {
		log.info("call Long型日期 ： {}",System.currentTimeMillis());
		return System.currentTimeMillis();
	}

	/**
	 * 示例函数
	 *
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(EurekaServerApplicationTests.convert2long("2000-01-01 01:01:01",
//				EurekaServerApplicationTests.TIME_FORMAT));
//
//		System.out.println(EurekaServerApplicationTests.convert2String(EurekaServerApplicationTests.convert2long("2000-01-01 01:01:01",
//				EurekaServerApplicationTests.TIME_FORMAT), EurekaServerApplicationTests.TIME_FORMAT));

		List<String> stringList = new ArrayList<>();
		stringList.add("abc");
		stringList.add("abc");
		stringList.add("bcd");
		log.info("call 去重前 list：{}",stringList);
		List<String> stringListNoRepeat = ListObjectNoRepeatUtils.removeStringListDupli(stringList);
		log.info("call 去重后 list：{}",stringListNoRepeat);
		List<String> unique = stringList.stream().distinct().collect(Collectors.toList());
		log.info("call 去重后 list：{}",unique);

	}

	@Test
	public void testListNoRepeat(){
		List<String> stringList = new ArrayList<>();
		stringList.add("abc");
		stringList.add("abc");
		stringList.add("bcd");
		log.info("call 去重前 list：{}",stringList);
		List<String> stringListNoRepeat = ListObjectNoRepeatUtils.removeStringListDupli(stringList);
		log.info("call 去重后 list：{}",stringListNoRepeat);
	}

	@Test
	public void testEqualsNoRep(){
//		UserInfo p1 = new UserInfo(11, "jack");
//		UserInfo p2 = new UserInfo(21, "tom");

//		List<UserInfo> persons = Arrays.asList(p1, p2, p2);

//		log.info("call 去重前 list:{}",persons);
//		List<UserInfo> personList = new ArrayList<>();
//		// 去重
//		persons.stream().forEach(
//				p -> {
//					if (!personList.contains(p)) {
//						personList.add(p);
//					}
//				}
//		);
//		log.info("call 去重后：{}",personList);
	}

	@Test
	public void testDupliById(){
//		UserInfo p1 = new UserInfo(11, "jack");
//		UserInfo p2 = new UserInfo(11, "tom");
//		List<UserInfo> persons = Arrays.asList(p1, p2, p2);
//		List<UserInfo> personList = ListObjectNoRepeatUtils.removeDupliById(persons);
//		log.info("call 去重前：{}", persons);
//		log.info("call 去重后：{}", personList);
	}

	@Test
	public void testDupliByMorePro(){
//		UserInfo p1 = new UserInfo(11, "jack",21);
//		UserInfo p2 = new UserInfo(11, "tom", 22);
//		UserInfo p3 = new UserInfo(11, "tom", 21);
//		List<UserInfo> persons = Arrays.asList(p1, p2, p2, p3);
//		List<UserInfo> personList = ListObjectNoRepeatUtils.removeDupliByMorePro(persons);
//		log.info("call 去重前：{}", persons);
//		log.info("call 去重后：{}", personList);
	}


	@Test
	public void testDupliByUserId(){
//		UserInfo p1 = new UserInfo(11, "jack");
//		UserInfo p2 = new UserInfo(22, "tom");
//		List<UserInfo> persons = Arrays.asList(p1, p2, p2);
//		List<UserInfo> personList = ListObjectNoRepeatUtils.removeDupliByUserId(persons);
//		log.info("call 去重前：{}", persons);
//		log.info("call 去重后：{}", personList);
	}

	@Test
	public void testDupliByUserIdNew(){
//		UserInfo p1 = new UserInfo(11, "jack");
//		UserInfo p2 = new UserInfo(22, "tom");
//		List<UserInfo> persons = Arrays.asList(p1, p2, p2);
//		List<UserInfo> personList = ListObjectNoRepeatUtils.removeDupliByUserIdNew(persons);
//		log.info("call 去重前：{}", persons);
//		log.info("call 去重后：{}", personList);
	}

	@Test
    public void testBitSet(){
            int [] array = new int [] {1,2,3,22,0,3,63};
            BitSet bitSet  = new BitSet(1);
            System.out.println(bitSet.size());   //64
            bitSet  = new BitSet(65);
            System.out.println(bitSet.size());   //128
            bitSet  = new BitSet(23);
            System.out.println(bitSet.size());   //64

            //将数组内容组bitmap
            for(int i=0;i<array.length;i++)
            {
                bitSet.set(array[i], true);
            }

            System.out.println(bitSet.get(22));
            System.out.println(bitSet.get(60));

            System.out.println("下面开始遍历BitSet：");
            for ( int i = 0; i < bitSet.size(); i++ ){
                System.out.println(bitSet.get(i));
        }
    }

    @Test
	public void testBeanToMap() throws Exception{
		MerchantCompanyReqDTO reqDTO = new MerchantCompanyReqDTO();
		reqDTO.setMemberName("测试商户名");
		reqDTO.setAccountName("测试账户名");
		reqDTO.setOutdateTime(new Date());
		List<ShareholdersReqDTO> shareholdersReqDTOList = new ArrayList<>();
		ShareholdersReqDTO reqDTO1  = new ShareholdersReqDTO();
		reqDTO1.setShareholdersBeneficiaryName("孔德胜");
		reqDTO1.setShareholdersBeneficiaryCertificatesType("01");
		reqDTO1.setShareholdersCertificatesNumber("110");
		reqDTO1.setShareholdersType("01");
		shareholdersReqDTOList.add(reqDTO1);
		reqDTO.setShareholdersReqDTOList(shareholdersReqDTOList);
		Map<String, String> map =  ConvertUtils.convertBean(reqDTO);
		log.info("call testBeanToMap :{}",map.toString());
	}

	@Test
	public void testStringtoSuccess(){
		String result = "{\"success\":false,\"result\":null,\"errorCode\":\"FC_SYSTEM_INNER_ERROR\",\"errorMsg\":\"系统内部异常\"}";
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);
		log.info("call testStringtoSuccess:{}" ,jsonObject.getString("success"));

		String accountNo = CipherTextUtils.aesDecryptBankCard("45e3fdf3f9f486a12f9b8d3f45b6e4ce");
		log.info("call accountNo :{}" , accountNo);
	}

	@Test
	public void printSql(){
		for(int i= 0; i < 46 ; i ++ ){
			System.out.println("insert into FUNDS_OPERATE.cms_role_func(role_id, func_id, create_user_id, create_time) " +
					"VALUES(1, " + i +" , 1, NOW() );");
		}
	}

	@Test
	public void test4555(){
		String memberID  ="1042450," +
				"1048671," +
				"1052616," +
				"1053170," +
				"1054118," +
				"1106504," +
				"1111863," +
				"1118867," +
				"1119198," +
				"1121963," +
				"1122128," +
				"1125335," +
				"1125866," +
				"1126853," +
				"1129261," +
				"1131852," +
				"1133329," +
				"1133586," +
				"1134888," +
				"1134924," +
				"1135384," +
				"1136114," +
				"1136136," +
				"1146723," +
				"1148726," +
				"1150794," +
				"1156305," +
				"1156523," +
				"1157110," +
				"1157351," +
				"1157828," +
				"1160973," +
				"1161016," +
				"1161156," +
				"1161244," +
				"1169357," +
				"1169917," +
				"1170939," +
				"1171097," +
				"1171326," +
				"1171334," +
				"1174898," +
				"1176390," +
				"1176680," +
				"1177287," +
				"1177579," +
				"1180415," +
				"1181200," +
				"1182145," +
				"1182162," +
				"1182170," +
				"1182642," +
				"1182875," +
				"1183209," +
				"1183487," +
				"1183732," +
				"1184025," +
				"1184100," +
				"1187708," +
				"1187800," +
				"1188069," +
				"1188767," +
				"1189197," +
				"1189206," +
				"1189285," +
				"1189310," +
				"1189473," +
				"1190238," +
				"1190336," +
				"1190409," +
				"1190424," +
				"1191050," +
				"1191406," +
				"1191556," +
				"1192439," +
				"1192746," +
				"1192842," +
				"1192898," +
				"1192911," +
				"1192919," +
				"1192929," +
				"1193197," +
				"1193616," +
				"1193720," +
				"1193723," +
				"1193736," +
				"1194048," +
				"1194192," +
				"1194296," +
				"1194318," +
				"1194496," +
				"1194665," +
				"1194754," +
				"1194898," +
				"1195543," +
				"1195544," +
				"1195663," +
				"1195873," +
				"1196162," +
				"1196277," +
				"1196324," +
				"1196864," +
				"1196898," +
				"1197172," +
				"1197431," +
				"1197694," +
				"1197895," +
				"1197925," +
				"1198092," +
				"1198184," +
				"1198312," +
				"1198491," +
				"1198710," +
				"1198730," +
				"1198873," +
				"1199325," +
				"1199441," +
				"1199549," +
				"1199578," +
				"1199937," +
				"1200010," +
				"1200011," +
				"1200413," +
				"1200758," +
				"1200814," +
				"1200862," +
				"1201003," +
				"1201141," +
				"1201425," +
				"1201894," +
				"1201922," +
				"1202273," +
				"1202293," +
				"1203185," +
				"1203433," +
				"1204349," +
				"1204371," +
				"1205171," +
				"1205780," +
				"1206142," +
				"1206881," +
				"1207048," +
				"1207233," +
				"1207286," +
				"1207690," +
				"1207692," +
				"1207859," +
				"1207882," +
				"1207988," +
				"1208103," +
				"1208156," +
				"1208164," +
				"1208166," +
				"1208307," +
				"1208459," +
				"1208515," +
				"1208527," +
				"1208662," +
				"1208870," +
				"1209221," +
				"1209272," +
				"1210014," +
				"1210213," +
				"1212782," +
				"1212899," +
				"1212960," +
				"1213158," +
				"1213404," +
				"1214693," +
				"1215216," +
				"1216197," +
				"1216587," +
				"1216633," +
				"1216797," +
				"1217974," +
				"1218027," +
				"1218038," +
				"1218549," +
				"1218562," +
				"1218919," +
				"1219312," +
				"1219378," +
				"1219582," +
				"1219670," +
				"1220001," +
				"1220145," +
				"1220169," +
				"1220173," +
				"1220183," +
				"1220200," +
				"1220208," +
				"1220354," +
				"1220359," +
				"1220371," +
				"1220388," +
				"1220400," +
				"1220416," +
				"1220444," +
				"1220446," +
				"1220639," +
				"1220644," +
				"1220676," +
				"1220716," +
				"1220755," +
				"1220860," +
				"1221032," +
				"1221039," +
				"1221043," +
				"1221049," +
				"1221069," +
				"1221181," +
				"1221326," +
				"1221473," +
				"1221512," +
				"1222296," +
				"1222318," +
				"1222412," +
				"1222472," +
				"1222481," +
				"1222514," +
				"1222626," +
				"1222868," +
				"1222876," +
				"1222883," +
				"1222886," +
				"1222888," +
				"1222890," +
				"1222891," +
				"1222988," +
				"1223035," +
				"1223222," +
				"1223572," +
				"1223713," +
				"1223838," +
				"1223891," +
				"1224020," +
				"1224034," +
				"1224053," +
				"1224059," +
				"1224071," +
				"1224117," +
				"1224138," +
				"1224217," +
				"1224224," +
				"1224351," +
				"1224383," +
				"1224386," +
				"1224400," +
				"1224404," +
				"1224684," +
				"1224792," +
				"1225136," +
				"1225969," +
				"1226004," +
				"1226008," +
				"1226011," +
				"1226152," +
				"1226640," +
				"1226646," +
				"1226760," +
				"1226979," +
				"1227664," +
				"1227905," +
				"1228180," +
				"1228304," +
				"1228455," +
				"1228550," +
				"1228585," +
				"1228666," +
				"1228706," +
				"1228728," +
				"1229175," +
				"1229197," +
				"1229441," +
				"1229549," +
				"1229588," +
				"1229699," +
				"1229700," +
				"1229709," +
				"1230087," +
				"1230089," +
				"1230150," +
				"1230220," +
				"1230453," +
				"1230531," +
				"1231254," +
				"1231261," +
				"1231304," +
				"1231440," +
				"1231615," +
				"1231693," +
				"1231817," +
				"1231841," +
				"1231845," +
				"1231966," +
				"1232112," +
				"1232363," +
				"1232431," +
				"1232432," +
				"1232437," +
				"1232634," +
				"1232659," +
				"1232663," +
				"1232734," +
				"1232743," +
				"1232768," +
				"1232835," +
				"1232840," +
				"1232844," +
				"1232955," +
				"1233079," +
				"1233495," +
				"1233600," +
				"1233664," +
				"1233787," +
				"1233799," +
				"1234189," +
				"1234207," +
				"1234210," +
				"1234212," +
				"1234540," +
				"1234855," +
				"1234873," +
				"1235079," +
				"1235096," +
				"1235139," +
				"1236118";

		String memberExclude = "";

		String[] memberStr = memberID.split(",");
		for(int i = 0 ; i < memberStr.length ; i ++){
			if(!memberExclude.contains(memberStr[i])){
				System.out.println("INSERT INTO BAOFOO_ADMIN.admin_company_member(member_id) VALUES ("+memberStr[i] + " );" );
			}
		}
	}

	@Test
	public void testMemberId(){
		String memberId1 = "memberId=1231";
		String[] memberIdArr = memberId1.split("memberId=");
		log.info("call memberIdArr: {}",memberIdArr);
	}
	
	@Test
	public void testInsert(){
		String userName = "'赵娟','夏悦','闫婷','任利利','王玉琢','陈曦','李国庆','彭露超','彭露超02','刘婷', '巩文杰,"+
				"'王玉琢'," +
				"'王倩'," +
				"'李义隆'," +
				"'唐健'," +
				"'彭冬伟'," +
				"'袁婷婷'," +
				"'向荣忠'," +
				"'朱春娇'";
		String[] userNames = userName.split(",");
		for(int i =0 ; i<userNames.length ; i ++){
			int y = i + 1;
			System.out.println("INSERT INTO BAOFOO_ADMIN.`admin_old_user`(id,user_name,create_time) VALUES ("+y+","+userNames[i]+",NOW());" );
		}
	}

	@Test
	public void test123(){
		String funcIds = "4981,4982,4983,4984,4985,4986,4987,4988,4989,4990,5000,5001";
		String roleIds ="3   , " +
				"8   , " +
				"9   , " +
				"16  , " +
				"17  , " +
				"28  , " +
				"29  , " +
				"35  , " +
				"49  , " +
				"51  , " +
				"53  , " +
				"54  , " +
				"63  , " +
				"70  , " +
				"82  , " +
				"91  , " +
				"98  , " +
				"99  , " +
				"103 , " +
				"104 , " +
				"106 , " +
				"107 , " +
				"115 , " +
				"131 , " +
				"133 , " +
				"136 , " +
				"137 , " +
				"143 , " +
				"147 , " +
				"148 , " +
				"149 , " +
				"150 , " +
				"151 , " +
				"152 , " +
				"154 , " +
				"157 , " +
				"161 , " +
				"162 , " +
				"164 , " +
				"165 , " +
				"172 , " +
				"173 , " +
				"178 , " +
				"179 , " +
				"182 , " +
				"183 , " +
				"189 , " +
				"191 , " +
				"195 , " +
				"196 , " +
				"197 , " +
				"198 , " +
				"199 , " +
				"200 , " +
				"201 , " +
				"203 , " +
				"205 , " +
				"207 , " +
				"208 , " +
				"209 , " +
				"210 , " +
				"212 , " +
				"232 , " +
				"236 , " +
				"237 , " +
				"238 , " +
				"239 , " +
				"240 , " +
				"252 , " +
				"253 , " +
				"255 , " +
				"258 , " +
				"261 , " +
				"262 , " +
				"264 , " +
				"266 , " +
				"269 , " +
				"270 , " +
				"271 , " +
				"273 , " +
				"274 , " +
				"275 , " +
				"276 , " +
				"277 , " +
				"278 , " +
				"279 , " +
				"283 , " +
				"284 , " +
				"285 , " +
				"286 ";

		String[] roleIsList = roleIds.split(",");
		String[] funcIdList = funcIds.split(",");
		for(int i = 0 ; i< funcIdList.length; i ++ ){
			for(int j =0; j< roleIsList.length ; j ++){
				System.out.println("INSERT INTO BAOFOO_ADMIN.admin_role_func(role_id, func_id, create_time, update_time) VALUES("+roleIsList[j]+","+funcIdList[i]+",NOW(),NOW());" );
			}
		}

	}

	@Test
	public void testPrintTime(){
	 log.info("call datetime: {}",  DateTime.now().plusSeconds(300).toDate());
	}

	@Test
	public void testJwT() throws Exception{
		this.jwtTest();
	}

	@Test
	public void testCheck(){
//		UserInfo userInfo = new UserInfo(110,"hello1",18);
		List<String> list =Collections.synchronizedList(new ArrayList<String>());
//		System.arraycopy()
		CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();

	}

	@Test
	public void testStreamSort(){
		List<UserInfo> userInfos = new ArrayList<>();
		List<UserInfo> userInfoSort = userInfos.stream().sorted().collect(Collectors.toList());
	}

	@Test
	public void testListSubList(){
		List<String> list = new ArrayList<>();
		list.add("110");
		list.add("112");
		list.add("801535");
		list.add("113");
		list.add("114");
//		List<String> list1 = list.subList(0,1);
//		List<String> list2 = list.subList(1,5);
//		log.info("call testListSubList list1 :{}",list1);
//		log.info("call testListSubList list2 :{}",list2);

		StringBuffer sb = new StringBuffer();
		for(String s : list){
			if("801535".equals(Long.valueOf(s))){
				continue;
			}
			sb.append(s);
			sb.append(",");
		}
		log.info("call testListSubList list1 :{}",sb.substring(0,sb.length() -1));

	}

	//加密的
	private static final String SECRET_KEY = "123456789";
	public void jwtTest() throws InterruptedException {
		// 设置3秒后过期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = System.currentTimeMillis() + 30*60*1000;
		String jwt = this.buildJwt(new Date(time));
		System.out.println("jwt = " + jwt);
		// 验证token是否可用
		boolean isOk = this.isJwtValid(jwt);
		System.out.println(isOk);
	}

	public String buildJwt(Date exp) {
		String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				//SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
				.setExpiration(exp)     // expTime是过期时间
				.claim("name","wangtingjun").claim("age","18").claim("key", "vaule")
				//该方法是在JWT中加入值为vaule的key字段
				.compact();
		return jwt;
	}

	public boolean isJwtValid(String jwt) {
		try {
			//解析JWT字符串中的数据，并进行最基础的验证
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
					//SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
					.parseClaimsJws(jwt)
					//jwt是JWT字符串
					.getBody();
			System.out.println(claims);
			String vaule = claims.get("key", String.class);
			//获取自定义字段key
			// 判断自定义字段是否正确
			if ("vaule".equals(vaule)) {
				return true;
			} else {
				return false;
			}
		}
		//在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
		// 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
		catch (SignatureException e) {
			return false;
		}catch (ExpiredJwtException e){
			return false;
		}
	}

//	/*树形化*/
//	final void treeifyBin(Node<K,V>[] tab, int hash) {
//		int n, index; Node<K,V> e;// 定义n:节点数组长度、index:hash对应的数组下标、e:用于循环的迭代变量,代表当前节点
//		if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
//			resize();// 若数组尚未初始化或者数组长度小于64,则直接扩容而不进行树形化
//		else if ((e = tab[index = (n - 1) & hash]) != null) {// 获取指定数组下标的头结点e
//			TreeNode<K,V> hd = null, tl = null;// 定义head节点hd、尾节点tl
//			do {// 循环,该循环主要是将原单向链表转化为双向链表
//				TreeNode<K,V> p = replacementTreeNode(e, null);// 以e的hash、key、value,以及以null为后继元创建树形节点p
//				if (tl == null)// 若尾节点为null表明首次循环,此时e为头结点、p为根节点,因此将p赋值给表示头结点的hd
//					hd = p;
//				else {// 负责根节点已经产生过了此时tl尾节点指向上次循环创建的树形节点
//					p.prev = tl;// 此时p为上次循环的的后继元在本次循环为当前节点,产生当前节点与前驱元的prev链
//					tl.next = p;// 产生前驱元与当前节点的next链
//				}
//				tl = p;// 将tl指向当前节点
//			} while ((e = e.next) != null);// e指向e的后继元
//			if ((tab[index] = hd) != null)// 若指定的位置头结点不为空则进行树形化
//				hd.treeify(tab);// 根据链表创建红黑树结构
//		}
//	}



//	@SuppressWarnings({"rawtypes","unchecked"})
	//将旧table中的元素放到扩容后的newTable中
//	Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
//	table = newTab;
//        if (oldTab != null) {
//		for (int j = 0; j < oldCap; ++j) {
//			Node<K,V> e;
//			if ((e = oldTab[j]) != null) {
//				oldTab[j] = null;
//				//如果数组对应下标位置只有一个元素，对hashCade取余并根据结果直接放到newTable相应的位置
//				if (e.next == null)
//					newTab[e.hash & (newCap - 1)] = e;
//					//如果数组对应下标位置的元素是一个红黑树,则拆分红黑树放到newTable中
//					// 如果拆分后的红黑树元素小于6，则转化为链表
//				else if (e instanceof TreeNode)
//					((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//				else { // preserve order
//					// 数组对应下标位置的元素是一个链表的情况
//					// 根据(e.hash & oldCap)条件对链表进行拆分并放到newTable
//					Node<K,V> loHead = null, loTail = null;
//					Node<K,V> hiHead = null, hiTail = null;
//					Node<K,V> next;
//					do {
//						next = e.next;
//						if ((e.hash & oldCap) == 0) {
//							if (loTail == null)
//								loHead = e;
//							else
//								loTail.next = e;
//							loTail = e;
//						}
//						else {
//							if (hiTail == null)
//								hiHead = e;
//							else
//								hiTail.next = e;
//							hiTail = e;
//						}
//					} while ((e = next) != null);
//					if (loTail != null) {
//						loTail.next = null;
//						newTab[j] = loHead;
//					}
//					if (hiTail != null) {
//						hiTail.next = null;
//						newTab[j + oldCap] = hiHead;
//					}
//				}
//			}
//		}
//	}
//        return newTab;





}
