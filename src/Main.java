import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static AtomicInteger count3 = new AtomicInteger(0);
    public static AtomicInteger count4 = new AtomicInteger(0);
    public static AtomicInteger count5 = new AtomicInteger(0);

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        for (int i = 0; i < texts.length - 1; i++) {
            String str = texts[i];
            new Thread(() -> {
                if (iSPolindrom(str)) {
                    if (str.length() == 3) {
                        count3.incrementAndGet();
                    } else {
                        if (str.length() == 4) {
                            count4.incrementAndGet();
                        } else {
                            if (str.length() == 5) {
                                count5.incrementAndGet();
                            }
                        }
                    }
                }
            }
            ).start();

            new Thread(() -> {
                if (isRepeat(str)) {
                    if (str.length() == 3) {
                        count3.incrementAndGet();
                    } else {
                        if (str.length() == 4) {
                            count4.incrementAndGet();
                        } else {
                            if (str.length() == 5) {
                                count5.incrementAndGet();
                            }
                        }
                    }
                }
            }
            ).start();

            new Thread(() -> {
                if (isAscending(str)) {
                    if (str.length() == 3) {
                        count3.incrementAndGet();
                    } else {
                        if (str.length() == 4) {
                            count4.incrementAndGet();
                        } else {
                            if (str.length() == 5) {
                                count5.incrementAndGet();
                            }
                        }
                    }
                }
            }
            ).start();
        }

        System.out.println("Красивых слов с длиной 3: " + count3 + " шт.");
        System.out.println("Красивых слов с длиной 4: " + count4 + " шт.");
        System.out.println("Красивых слов с длиной 5: " + count5 + " шт.");

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean iSPolindrom(String text) {
        boolean isP = true;
        char[] chars = text.toCharArray();
        for (int i = 0; i < (chars.length / 2); i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                isP = false;
                break;
            }
        }
        return isP;
    }

    public static boolean isRepeat(String text) {
        boolean isR = true;
        char[] chars = text.toCharArray();
        for (int i = 1; i < (chars.length - 1); i++) {
            if (chars[0] != chars[i]) {
                isR = false;
                break;
            }
        }
        return isR;
    }

    public static boolean isAscending(String text) {
        boolean isA = true;
        char[] chars = text.toCharArray();
        Arrays.sort(chars);
        String sort = new String(chars);
        if (!(text.equals(sort))) {
            isA = false;
        }
        ;
        return isA;
    }


}
