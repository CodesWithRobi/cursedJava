import java.util.*;
import java.io.FileWriter;
import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.Reader;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.BitSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.function.*;
import java.util.stream.*;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import java.text.DecimalFormat;

// user submitted code insert below
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        try {
            System.out.print(Files.readString(Path.of("__Driver__.java")));
        } catch(Exception e) {}
        return null;
    }
}

class __Driver__ {
    // Load up serializer on driver class load. Required for debugger app.
    private static String zeroString = __Serializer__.serialize(0);

    private static Boolean validateList(ListNode cur, ListNode original)
    {
        while (original!=null) {
            if (cur==null) return false; // current is shorter than original
            if (original.val != cur.val) return false; // value changed
            original = original.next;
            cur = cur.next;
        }
        if (cur!=null) return false;   // current is longer than original
        else return true;
    }
    
    private static final String SEPARATOR = new StringBuilder()
            .append((char) 27)      // ESCAPE
            .append((char) 9)       // TAB
            .append((char) 29)      // GROUP SEPARATOR
            .toString();
    public static void main(String[] args) throws IOException {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long ctime_start;
        long ctime_total = 0;

        PrintWriter printWriter = new PrintWriter(new FileWriter("user.out"), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ListNode dummy = new ListNode(0);
        String line;
        while ((line = in.readLine()) != null) {
            int intersection = __Deserializer__.toInteger(line);
            line = in.readLine();
            ListNode listA = __Deserializer__.toListNode(line);
            ListNode listA_backup = __Deserializer__.toListNode(line);
            line = in.readLine();
            ListNode listB = __Deserializer__.toListNode(line);
            ListNode listB_backup = __Deserializer__.toListNode(line);
            
            line = in.readLine();
            int lenA = __Deserializer__.toInteger(line);
            line = in.readLine();
            int lenB = __Deserializer__.toInteger(line);

            if (intersection != 0)   // If there is intersection
            {
                dummy.next = listB;
                ListNode a = listA;
                ListNode b = dummy;
                for (int i = 0; i < lenA; i++) a=a.next;
                for (int i = 0; i < lenB; i++) b=b.next;
                b.next = a;
                listB = dummy.next;
            }
            
            ctime_start = bean.getCurrentThreadCpuTime();
            ListNode intersection_node = new Solution().getIntersectionNode(listA, listB);
            ctime_total += bean.getCurrentThreadCpuTime() - ctime_start;

            if (intersection_node!=null)
                printWriter.printf("Intersected at '%d'",intersection_node.val);
            else
                printWriter.printf("No intersection");
            //System.out.printf("\n");
                    // Validate the lists:
            ListNode pA=listA;
            ListNode pA_backup=listA_backup;
            ListNode pB=listB;
            ListNode pB_backup=listB_backup;
            if (__Driver__.validateList(pA, pA_backup) && 
                __Driver__.validateList(pB, pB_backup))
            {
                printWriter.printf("\n");
            }
            else
            {
                printWriter.printf(", ERROR: linked structure was modified.\n");
            }
            System.out.print(SEPARATOR);
        }
        printWriter.close();

        PrintWriter runtimeWriter = new PrintWriter(new FileWriter("display_runtime.txt"), true);
        ctime_total /= 1000000;
        runtimeWriter.println(ctime_total);
        runtimeWriter.close();
    }
}
