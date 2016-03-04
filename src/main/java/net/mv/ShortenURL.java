package net.mv;

public class ShortenURL {

	public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int BASE = ALPHABET.length();
//	private String originalURL;
//	private String newURL;
//	
//	public ShortenURL(String s){
//		this.originalURL=s;
//	}
	
	public static String encode(int num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }
        while(str.length()!=5){
        	str.insert(0, 0);
        }
        return str.toString();
    }

//    public static int decode(String str) {
//        int num = 0;
//        for (int i = 0; i < str.length(); i++) {
//            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
//        }
//        return num;
//    }
    public static void main(String[] args){
//    	System.out.println(decode("https://github.com/delight-im/ShortURL/blob/master/Java/ShortURL.java"));
//    	System.out.println(encode(454329667));
//    	System.out.println(decode("https://docs.mongodb.org/manual/reference/method/db.collection.find/"));
//    	System.out.println(encode(2073370149));
//    	System.out.println(decode("www.google.com"));
//    	System.out.println(encode(802492003));
//    	System.out.println(encode(123456789));
    	System.out.println(encode(100));
    	
    }
}
