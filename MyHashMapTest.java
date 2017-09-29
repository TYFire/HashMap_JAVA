public class MyHashMapTest{
	public static void main(String args[]){
		MyHashMap<String, String> mm = new MyHashMap<>();
		//System.out.println(dct.put(0, 1));
		//System.out.println(dct.get(0));
		//System.out.println(dct.get(1));

		Long aBeginTime=System.currentTimeMillis();//记录BeginTime  
        for(int i=0;i<1000000;i++){  
        mm.put(""+i, ""+i*100);  
        }  
        Long aEndTime=System.currentTimeMillis();//记录EndTime  
        System.out.println("insert time-->"+(aEndTime-aBeginTime));  
          
        Long lBeginTime=System.currentTimeMillis();//记录BeginTime  
        mm.get(""+100000);  
        Long lEndTime=System.currentTimeMillis();//记录EndTime  
        System.out.println("seach time--->"+(lEndTime-lBeginTime)); 

	}

}
