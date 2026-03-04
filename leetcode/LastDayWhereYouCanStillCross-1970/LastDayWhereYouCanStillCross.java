class UnionFind {
  int[] parent;
  int[] rank;
  UnionFind(int n) {
    parent = new int[n];
    rank = new int[n];
    Arrays.fill(parent, -1);
    parent[n-2] = n-2; //l
    parent[n-1] = n-1; //r
  }

  public int find(int x) {
    if(parent[x] == x) return x;
    return parent[x] = find(parent[x]);
  }

  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if(rootX != rootY) {
      if(rank[rootX] < rank[rootY])
        parent[rootX] = rootY;
      else if(rank[rootX] > rank[rootY])
        parent[rootY] = rootX;
      else {
        parent[rootY] = rootX;
        rank[rootX]++; //same length the rank increase
      }
    }
  }

  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }
}
class Solution {
  int col;
  int index(int[] pos) { return col*pos[0]+pos[1];}
  int index(int r, int c) { return col*r+c;}
  public int latestDayToCross(int row, int col, int[][] cells) {
    this.col = col;
    int n = row*col, left = n, right = left+1;
    UnionFind dsu = new UnionFind(row*col+2);
    int[][] dirs = {
      {-1, -1}, {-1, 0}, {-1, 1},
      {0, -1},          {0, 1},
      {1, -1}, {1, 0}, {1, 1}
    };
    int day = 0;
    for(int cell[] : cells) {
      cell[0]--;cell[1]--;
      int v = index(cell);
      dsu.parent[v] = v;
      for(int[] dir: dirs) {
        int r = cell[0]+dir[0];
        int c = cell[1]+dir[1];
        if(r == -1 || r == row) continue;
        else if(c == -1) dsu.union(left, v); 
        else if(c == col) dsu.union(right, v);
        else if(dsu.parent[index(r,c)] != -1) dsu.union(index(r,c), v);
      }
      if(dsu.connected(left, right)) return day;
      day++;
    }
    return day;
  }
}

void main() {
  int row = 5, col = 24;
  // int[][] cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
  // int[][] cells = {{1,1},{2,1},{1,2},{2,2}};
  int[][] cells = {{4,3},{2,4},{2,11},{2,22},{1,15},{2,9},{3,4},{1,2},{5,23},{5,3},{5,16},{2,2},{2,16},{2,3},{4,13},{3,18},{5,8},{5,10},{4,24},{4,9},{4,16},{2,17},{3,11},{4,12},{3,1},{2,10},{3,10},{3,3},{3,5},{4,1},{3,20},{3,23},{2,5},{4,22},{3,13},{5,11},{3,17},{5,20},{1,8},{3,9},{1,4},{2,23},{2,1},{4,21},{5,21},{2,21},{4,20},{1,10},{5,6},{4,18},{5,4},{2,12},{2,20},{1,12},{5,22},{3,22},{1,19},{5,1},{3,21},{2,6},{1,1},{4,6},{1,3},{3,8},{4,8},{5,14},{1,21},{3,6},{4,11},{2,24},{1,14},{5,2},{1,18},{2,14},{4,14},{2,13},{3,14},{3,7},{5,17},{3,15},{2,15},{4,23},{4,17},{5,19},{3,19},{5,5},{4,15},{5,15},{3,16},{5,18},{4,5},{4,7},{1,6},{1,11},{2,8},{5,13},{2,7},{1,23},{2,19},{3,24},{4,19},{5,24},{1,22},{1,24},{1,17},{4,2},{1,20},{3,12},{5,9},{1,5},{3,2},{5,7},{5,12},{1,13},{2,18},{1,9},{4,10},{1,16},{4,4},{1,7}};
  IO.println(new Solution().latestDayToCross(row, col, cells));
}
