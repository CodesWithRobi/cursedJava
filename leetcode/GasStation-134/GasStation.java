public class GasStation {
  public static void main(String[] args) {
    int[] gas = {1,2,3,4,5};
    int cost[] = {3,4,5,1,2};
    System.out.println(Solution.canCompleteCircuit(gas, cost));
  }
}

class Solution {
  public static int canCompleteCircuit(int[] gas, int[] cost) {
    int sumGas=0;
    int sumCost=0;
    int tank=0;
    int sindex=0;
    for(int i=0;i<cost.length;i++) {
      sumGas+=gas[i];
      sumCost+=cost[i];
      tank+=(gas[i]-cost[i]);
      if(tank<0) {
        tank=0;
        sindex=i+1;
      }
    }
    if(sumGas>=sumCost)
    return sindex;
    else
    return -1;
  }
}
