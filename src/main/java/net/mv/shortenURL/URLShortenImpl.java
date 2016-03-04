package net.mv.shortenURL;

public class URLShortenImpl implements URLShorten{

	public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int BASE = ALPHABET.length();

	@Override
	public String shortenURL(int num) {
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

}
