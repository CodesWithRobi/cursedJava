import java.util.LinkedList;
import java.util.Queue;


public int[][] updateMatrix(int[][] mat) {

  class Pos {
    int x;
    int y;
    Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  Queue<Pos> q = new LinkedList<>();
  int[][] ans = new int[mat.length][mat[0].length];
  for(int i = 0; i < mat.length; i++) {
    for(int j = 0; j < mat[0].length; j++) {
      if(mat[i][j] == 0) q.offer(new Pos(i, j));
      else ans[i][j] = -1;
    }
  }
  while(!q.isEmpty()) {
    Pos coords = q.poll();
    for(int n = -2; n < 2; n++) {
      int x = coords.x+n%2, y = coords.y+(n+1)%2;
      if(x == -1 || x == mat.length || y == -1 || y == mat[0].length)
        continue;
      if(ans[x][y] == -1) {
        ans[x][y] = ans[coords.x][coords.y] + 1;
        q.offer(new Pos(x, y));
      }
    }
  }
  return ans;
}

  // [0, 1]
  // [1, 0]
  // [0, -1]
  // [-1, 0]

void main() {
  int[][] case1 = {
  {0, 0, 0},
  {0, 1, 0},
  {1, 1, 1}
  };
  // Expected: [[0,0,0],[0,1,0],[1,2,1]]

  int[][] case2 = {
  {0, 0, 0},
  {0, 1, 0},
  {1, 1, 1},
  {1, 1, 1},
  {0, 0, 0}
  };
  // Expected: [[0,0,0],[0,1,0],[1,2,1],[2,1,2],[0,0,0]]

  int[][] case3 = {
  {0, 1, 1},
  {1, 1, 1},
  {1, 1, 0}
  };
  // Expected: [[0,1,2],[1,2,1],[2,1,0]]

  int[][] case4 = {
  {1, 1, 1},
  {1, 0, 1},
  {1, 1, 1}
  };
  // Expected: [[2,1,2],[1,0,1],[2,1,2]]
  Arrays.stream(updateMatrix(case3)).map(i -> Arrays.toString(i)).forEach(IO::println);
}
