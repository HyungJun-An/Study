class Solution {
    public int solution(String s) {
        int answer = 0;
	    int count =0,  other = 0;
	    char c = s.charAt(0);
	    
	    for (int i=0; i<s.length(); i++) {
	    	if(count == other) {
	    		answer++;
	    		c = s.charAt(i);
	    	}
	    	if(s.charAt(i) == c) {
	    		count++;
	    	}
	    	else {
	    		other++;
	    	}
	    }
	    
	   return answer;
    }
}