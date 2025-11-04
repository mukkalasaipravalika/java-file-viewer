import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <file-path> [--page <n>] [--size <n>]");
            return;
        }

        String filePath = args[0];
        int page = 1;
        int size = Integer.MAX_VALUE;

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--page") && i + 1 < args.length) {
                page = Integer.parseInt(args[++i]);
            } else if (args[i].equals("--size") && i + 1 < args.length) {
                size = Integer.parseInt(args[++i]);
            }
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            int start = (page - 1) * size;
            int end = Math.min(start + size, lines.size());

            if (start >= lines.size()) {
                System.out.println("No lines on this page.");
                return;
            }

            for (int i = start; i < end; i++) {
                System.out.println(lines.get(i));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
