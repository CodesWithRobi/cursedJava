public class ContainerWithMostWater {
  public static void main(String[] args) {
    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    System.out.println(maxArea(height));
    
  }
  public static int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;

    int maxArea = 0;

    while(left < right) {

      maxArea = Math.max(maxArea, (right-left)*Math.min(height[right], height[left]));

      if(height[left] < height[right]) left++;
      else right--;
    }

    return maxArea;
  }
}
