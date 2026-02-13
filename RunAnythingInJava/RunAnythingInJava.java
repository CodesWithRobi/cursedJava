import java.io.*;
import java.nio.file.*;

public class NativeSolver {
    public static void main(String[] args) throws Exception {
        // 1. WRITE THE C SOURCE CODE
        // This C program reads input, parses the binary strings, and prints the result.
        // It uses simple char array loops to avoid overhead.
        String cCode = 
            "#include <stdio.h>\n" +
            "#include <string.h>\n" +
            "#include <stdlib.h>\n" +
            "\n" +
            "// Helper to count 1s in a string\n" +
            "int count_ones(char* s) {\n" +
            "    int c = 0;\n" +
            "    for (int i = 0; s[i]; i++) if (s[i] == '1') c++;\n" +
            "    return c;\n" +
            "}\n" +
            "\n" +
            "int main() {\n" +
            "    // Allocate buffers (20KB safe limit per string)\n" +
            "    char s1[20005], s2[20005];\n" +
            "    int type;\n" +
            "    \n" +
            "    // Read Input\n" +
            "    if (scanf(\"%s %s %d\", s1, s2, &type) != 3) return 0;\n" +
            "    \n" +
            "    int len1 = strlen(s1);\n" +
            "    int len2 = strlen(s2);\n" +
            "    int max_len = (len1 > len2) ? len1 : len2;\n" +
            "    \n" +
            "    int intersection = 0;\n" +
            "    int hamming = 0;\n" +
            "    int diffA_B = 0;\n" +
            "    int diffB_A = 0;\n" +
            "    \n" +
            "    for (int i = 0; i < max_len; i++) {\n" +
            "        char c1 = (i < len1) ? s1[i] : '0';\n" +
            "        char c2 = (i < len2) ? s2[i] : '0';\n" +
            "        \n" +
            "        if (c1 == '1' && c2 == '1') intersection++;\n" +
            "        if (c1 != c2) hamming++;\n" +
            "        if (c1 == '1' && c2 == '0') diffA_B++;\n" +
            "        if (c1 == '0' && c2 == '1') diffB_A++;\n" +
            "    }\n" +
            "    \n" +
            "    if (type == 1) printf(\"%d\\n\", intersection);\n" +
            "    else if (type == 2) printf(\"%d\\n\", hamming);\n" +
            "    else if (type == 3) printf(\"%d\\n\", diffA_B);\n" +
            "    else if (type == 4) {\n" +
            "        if (count_ones(s1) != count_ones(s2)) printf(\"-1\\n\");\n" +
            "        else printf(\"%d\\n\", diffA_B);\n" +
            "    }\n" +
            "    else printf(\"%d\\n\", intersection + hamming);\n" +
            "    \n" +
            "    return 0;\n" +
            "}";

        // Write solver.c
        Files.write(Paths.get("solver.c"), cCode.getBytes());

        // 2. COMPILE WITH GCC
        // "gcc solver.c -o solver"
        ProcessBuilder build = new ProcessBuilder("gcc", "solver.c", "-o", "solver");
        build.redirectErrorStream(true);
        Process pBuild = build.start();
        printOutput(pBuild); // Print compiler errors if any
        pBuild.waitFor();

        // 3. EXECUTE THE BINARY
        ProcessBuilder run = new ProcessBuilder("./solver");
        
        // Handle Input piping (File or Stdin)
        File inFile = new File("prog.in");
        if (inFile.exists()) {
            run.redirectInput(inFile);
        } else {
            // Local fallback: pipe Java System.in to C process
            run.redirectInput(ProcessBuilder.Redirect.INHERIT);
        }
        
        Process pRun = run.start();
        
        // Capture Output
        printOutput(pRun);
        pRun.waitFor();
    }

    private static void printOutput(Process p) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
