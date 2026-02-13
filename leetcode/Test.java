public class Test {
  public static void main(String[] args) {
    int[] nums = {1, 2, 2, 0, 0 , 1, 2};
    int[] cnt = {0, 0, 0};
    for(int i = 0; i < nums.length; i++) {
      cnt[nums[i]]++;
    }
    for(int i = 0; i < nums.length; i++) {
      if(cnt[0] != 0) nums[i] = 0;
      else if(cnt[1] != 0) nums[i] = 1;
      else nums[i] = 2;
    }
  }
}
