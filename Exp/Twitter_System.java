import java.util.*;

public class Twitter_System {
    static class t{
        int id,t;
        t(int id,int t){
            this.id=id;
            this.t=t;
        }
    }
    static int time=0;
    static Map<Integer,List<t>> tweets = new HashMap<>();
    static Map<Integer,Set<Integer>> follow = new HashMap<>();

    static void post(int u,int id){
        tweets.putIfAbsent(u,new ArrayList<>());
        tweets.get(u).add(new t(id,time++));
    }

    static void follow(int a,int b){
        if(a==b)return;
        follow.putIfAbsent(a, new HashSet<>());
        follow.get(a).add(b);
    }

    static void unfollow(int a,int b){
        if(follow.containsKey(a)){
            follow.get(a).remove(b);
        }
    }

    static List<Integer> get(int u){
        PriorityQueue<t>pq=new PriorityQueue<>((a,b)->b.t-a.t);

        if(tweets.containsKey(u)){
            pq.addAll(tweets.get(u));
        }

        if(follow.containsKey(u)){
            for(int f:follow.get(u)){
                if(tweets.containsKey(f)){
                    pq.addAll(tweets.get(f));
                }
            }
        }

        List<Integer> res=new ArrayList<>();
        while(!pq.isEmpty() && res.size()<10){
            res.add(pq.poll().id);
        }
        return res;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        while(n-->0){
            String c=sc.next();
            if(c.equals("post")){
                post(sc.nextInt(),sc.nextInt());
            }else if(c.equals("get")){
                List<Integer> ans=get(sc.nextInt());
                for(int x:ans)System.out.print(x+" ");
                System.out.println();
            }else if(c.equals("follow")){
                follow(sc.nextInt(),sc.nextInt());
            }else{
                unfollow(sc.nextInt(),sc.nextInt());
            }
        }
        sc.close();
    }
}
